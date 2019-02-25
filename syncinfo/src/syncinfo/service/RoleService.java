package syncinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import syncinfo.persistence.Page;
import syncinfo.persistence.PropertyFilter;
import syncinfo.service.GenericDao;
import syncinfo.service.impl.GenericServiceImpl;
import syncinfo.mapper.RoleMapper;
import syncinfo.model.Role;
import syncinfo.model.RoleExample;

@Service
@Transactional(readOnly=true)
public class RoleService extends GenericServiceImpl<Role, String>{    

	@Autowired
    private RoleMapper roleMapper;  

	@Override
	public GenericDao<Role, String> getDao() {
		return roleMapper;
	}
	
    public List<Role> selectRolesByUserId(String userId) {
        return roleMapper.selectRolesByUserId(userId);  
    }
    
    /**
     * 分页条件查询
     * 
     * @param page
     * @param example
     * @return
     */
    public Page<Role> search (Page<Role> page, List<PropertyFilter> filters){
    	
    	RoleExample example = new RoleExample();
    	
    	Assert.notNull(page, "page is not null.");
		if (page.isAutoCount()) {
			int totalCount = roleMapper.countByExample(example);
			page.setTotalCount(totalCount);
		}

		List<Role> result = roleMapper.selectByExampleAndPage(example);  
		page.setResult(result);
		return page;
    }

    @Transactional(readOnly = false)
	public void initRole(List<Role> roleList) {
		// TODO Auto-generated method stub
		roleMapper.initRole(roleList);
	}
}

