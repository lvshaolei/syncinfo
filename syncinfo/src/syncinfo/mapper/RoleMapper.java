package syncinfo.mapper;

import java.util.List;

import syncinfo.service.GenericDao;
import syncinfo.model.Role;
import syncinfo.model.RoleExample;

public interface RoleMapper extends GenericDao<Role, String> {  

    int countByExample(RoleExample example);

    /**
   	 * 按属性过滤条件列表分页查找对象.
   	 */
    List<Role> selectByExampleAndPage(RoleExample example);
    
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param id
     * @return
     */
    List<Role> selectRolesByUserId(String id);

	void initRole(List<Role> roleList);
}