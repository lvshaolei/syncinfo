package syncinfo.mapper;

import syncinfo.service.GenericDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import syncinfo.model.AdminExample;
import syncinfo.model.Admin;

public interface AdminMapper extends GenericDao<Admin, String> {
	
	int countByExample(AdminExample example);

    List<Admin> selectByExample(AdminExample example);
	
	/**
     * 查询登录用户名称
     * 
     * @param record
     * @return
     */
    Admin selectByLoginName(@Param(value = "loginName") String loginName);
	
	
	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
    List<Admin> selectByExampleAndPage(AdminExample example);

	List<Admin> searchCheck();
}
