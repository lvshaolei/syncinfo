package syncinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import syncinfo.service.GenericDao;
import syncinfo.service.impl.GenericServiceImpl;
import syncinfo.mapper.PermissionMapper;
import syncinfo.model.Permission;
import syncinfo.model.PermissionExample;

/**
 * 权限service
 * @author vincent
 * @date 2015-8-7
 */
@Service
@Transactional(readOnly=true)
public class PermissionService extends GenericServiceImpl<Permission, String>{
	
	@Autowired
	private PermissionMapper permissionMapper;
	@Override
	public GenericDao<Permission, String> getDao() {  
        return permissionMapper;    
    }
	
	/**
	 * 获取登录用户拥有的权限集合
	 * @param userId
	 * @return 结果集合
	 */
	public List<Permission> getPermissions(String userId){
		return permissionMapper.selectPermissionsByUserId(userId);    
	}

	/**
	 * 获取所有菜单
	 * @return 菜单集合
	 */
	public List<Permission> getMenus(PermissionExample example){
		return permissionMapper.selectByExample(example);
	}

	@Transactional(readOnly = false)
	public void initPer(List<Permission> perList) {
		// TODO Auto-generated method stub
		permissionMapper.initPer(perList);
	}

	public List<Permission> selectByExample(PermissionExample example) {
		// TODO Auto-generated method stub
		return permissionMapper.selectByExample(example);
	}

}
