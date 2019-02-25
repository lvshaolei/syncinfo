package syncinfo.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import syncinfo.service.GenericDao;
import syncinfo.model.SysLog;
import syncinfo.model.SysLogExample;

public interface SysLogMapper extends GenericDao<SysLog, BigDecimal> {

    int countByExample(SysLogExample example);

    /**
   	 * 按属性过滤条件列表分页查找对象.
   	 */
    List<SysLog> selectByExampleAndPage(SysLogExample example);
   
    /**
   	 * 批量删除
   	 */
    void deleteBatchByPrimaryKey_foreach_list(List<BigDecimal> classIdList);  
    
    List<SysLog> getListLog(@Param("operationcertsn") String operationcertsn, @Param("operationtype")String operationtype,
    		@Param("createdate") Date createdate, @Param("createdate2") Date createdate2);
}