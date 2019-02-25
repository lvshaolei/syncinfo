package syncinfo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import syncinfo.persistence.Page;
import syncinfo.persistence.PropertyFilter;
import syncinfo.utils.Constants;
import syncinfo.utils.DateUtils;
import syncinfo.utils.StringUtils;
import syncinfo.utils.security.Digests;
import syncinfo.utils.security.Encodes;
import syncinfo.model.AdminExample;
import syncinfo.model.SysLog;
import syncinfo.model.AdminExample.Criteria;
import syncinfo.mapper.AdminMapper;
import syncinfo.mapper.SysLogMapper;
import syncinfo.model.Admin;
import syncinfo.service.impl.GenericServiceImpl;

@Service
@Transactional(readOnly=true)
public class AdminService extends GenericServiceImpl<Admin, String> {
	
	/**加密方法*/
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	@Autowired
    private AdminMapper adminMapper;
	 
	@Override
    public GenericDao<Admin, String> getDao() {  
        return adminMapper;
    }
	
	@Autowired
	private SysLogMapper sysLogMapper;
	
	/**
	 * 按登录名查询用户
	 * @param loginName
	 * @return 用户对象
	 */
	public Admin selectByUsername(String loginName) {
		return adminMapper.selectByLoginName(loginName);
	}
	/**
     * 分页条件查询
     * 
     * @param page
     * @param example
     * @return
     */
	public Page<Admin> search(Page<Admin> page, List<PropertyFilter> filters,
			Admin admin) {
		boolean isSendDept = false;

		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		for (PropertyFilter filter : filters) {
			if (!filter.hasMultiProperties()) { // 只有一个属性需要比较的情况.
				Assert.hasText(filter.getPropertyName(),
						"propertyName is not null.");
				if ("name".equalsIgnoreCase(filter.getPropertyName())) {
					String name = (String) filter.getMatchValue();

					criteria.andAdminnameLike(Constants.prefix + name
							+ Constants.suffix);

				} else if ("id".equalsIgnoreCase(filter.getPropertyName())) {

					criteria.andAdminidEqualTo((String) filter.getMatchValue());
					// criteria.andAdminidLike(Constants.prefix +
					// (String)filter.getMatchValue() + Constants.suffix);
				} else if ("deptid".equalsIgnoreCase(filter.getPropertyName())) {

					example.setId((BigDecimal) filter.getMatchValue());

					isSendDept = true;
				} else if ("status".equalsIgnoreCase(filter.getPropertyName())) {

					criteria.andStatusEqualTo(((Integer) filter.getMatchValue())
							.shortValue());
				}

			} else {// 包含多个属性需要比较的情况,进行or处理.
				for (String param : filter.getPropertyNames()) {
					System.out.println(param + filter.getMatchValue()
							+ filter.getMatchType());
				}
			}
		}

//		if (!isSendDept) {
//			example.setId(admin.getDepartment().getId());
//		}
//
//		// 去除超级管理员
//		criteria.andAdminidNotEqualTo(Constants.SYSADMIN);

		Assert.notNull(page, "page is not null.");
//		if (page.isAutoCount()) {
//			int totalCount = adminMapper.countByExample(example);
//			page.setTotalCount(totalCount);
//		}
		Assert.isTrue(page.getPageSize() > 0,
				"Page Size must larger than zero.");
		example.setStart(page.getFirst());
		example.setLimit(page.getLastIndex());
		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length,
					"Sorting fields are not equal.");
			String orderBy = "";
			for (int i = 0; i < orderByArray.length; i++) {
				orderBy = orderBy + orderByArray[i] + " " + orderArray[i] + ",";
			}
			example.setOrderByClause(orderBy.substring(0, orderBy.length() - 1));
		}
		List<Admin> result = adminMapper.selectByExampleAndPage(example);
		page.setResult(result);
		return page;
	}
	/**
	 * 判断是否超级管理员

	 * @param id
	 * @return boolean
	 */
	private boolean isSupervisor(String id) {
		return "1".equals(id);
	}
	/**
	 * 保存用户
	 * @param admin
	 */
	@Transactional(readOnly=false)
	public void save(Admin admin, SysLog syslog) {
		entryptPassword(admin);
		admin.setCreatedate(DateUtils.getSysTimestamp());   
		adminMapper.insertSelective(admin);
		sysLogMapper.insertSelective(syslog);
	}
	/**
	 * 修改用户
	 * @param admin
	 */
	@Transactional(readOnly=false)
	public void updateUser(Admin admin, SysLog syslog) {
		admin.setCreatedate(DateUtils.getSysTimestamp());   
		adminMapper.updateByPrimaryKeySelective(admin);
		sysLogMapper.insertSelective(syslog);
	}
	/**
	 * 修改密码
	 * @param admin
	 */
	@Transactional(readOnly=false)
	public void updatePwd(Admin admin) {
		entryptPassword(admin);
		adminMapper.insertSelective(admin);
	}
	/**
	 * 删除用户
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void deleteUser(String id){  
		if(!isSupervisor(id))
			adminMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 删除用户
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void deleteUser(String id,SysLog syslog){  
		if(!isSupervisor(id)){
			sysLogMapper.insertSelective(syslog);
			Admin admin = new Admin();
			admin.setAdminid(id);
			admin.setStatus(Short.valueOf(Constants.StatusRes.LOGOFF));
			adminMapper.updateByPrimaryKeySelective(admin);
		}
			
	}
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(Admin admin) {
		byte[] salt = Digests.generateSalt(Constants.Crypto.SALT_SIZE);
		admin.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(Constants.Crypto.DEFAULT_PASSWORD.getBytes(), salt, HASH_INTERATIONS);
		admin.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 验证原密码是否正确
	 * @param user
	 * @param oldPwd
	 * @return
	 */
	public boolean checkPassword(Admin admin, String oldPassword){
		byte[] salt =Encodes.decodeHex(admin.getSalt()) ;
		byte[] hashPassword = Digests.sha1(oldPassword.getBytes(),salt, HASH_INTERATIONS);
		if(admin.getPassword().equals(Encodes.encodeHex(hashPassword))){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 按条件查询
	 * @param example
	 * @return 用户对象
	 */
	public List<Admin> selectByExample(AdminExample example) {
		return adminMapper.selectByExample(example);
	}

	public List<Admin> searchCheck() {
		// TODO Auto-generated method stub
		return adminMapper.searchCheck();
	}
	
	
}
