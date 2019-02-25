package syncinfo.mapper;

import java.util.List;

import syncinfo.service.GenericDao;
import syncinfo.model.Permission;
import syncinfo.model.PermissionExample;

public interface PermissionMapper extends GenericDao<Permission, String> {

    List<Permission> selectByExample(PermissionExample example);

    /**
     * 通过用户id 查询角色 拥有的权限 
     * @param id
     * @return
     */
    List<Permission> selectPermissionsByUserId(String id);

	void initPer(List<Permission> perList);
}