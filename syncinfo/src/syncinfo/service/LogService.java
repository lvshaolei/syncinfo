package syncinfo.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import syncinfo.service.impl.GenericServiceImpl;
import syncinfo.persistence.Page;
import syncinfo.persistence.PropertyFilter;
import syncinfo.service.GenericDao;
import syncinfo.utils.StringUtils;
import syncinfo.model.SysLog;
import syncinfo.model.SysLogExample;
import syncinfo.model.SysLogExample.Criteria;
import syncinfo.utils.Constants;
import syncinfo.mapper.SysLogMapper;


/**
 * 日志service
 * @author lvsl
 */
@Service
@Transactional(readOnly=true)
public class LogService extends GenericServiceImpl<SysLog, BigDecimal> {
	
	@Autowired
	private SysLogMapper logMapper;
	
	@Override
	public GenericDao<SysLog, BigDecimal> getDao() {  
		return logMapper;
	}
	
	  /**
     * 分页条件查询
     * 
     * @param page
     * @param example
     * @return
	 * @throws ParseException 
     */
    public Page<SysLog> search (Page<SysLog> page, List<PropertyFilter> filters) throws ParseException{
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar1=Calendar.getInstance();  
		Calendar calendar2=Calendar.getInstance(); 
    	SysLogExample example = new SysLogExample();
    	 Criteria criteria = example.createCriteria();
	    	for (PropertyFilter filter : filters) {
				if (!filter.hasMultiProperties()) { 
					Assert.hasText(filter.getPropertyName(), "propertyName is not null.");
				    if("operationcertsn".equalsIgnoreCase(filter.getPropertyName())){
						criteria.andOperationcertsnEqualTo((String)filter.getMatchValue());
					}else if("operationtype".equalsIgnoreCase(filter.getPropertyName())){
						String operationtype=(String)filter.getMatchValue();
						
						criteria.andOperationtypeLike(Constants.prefix + operationtype + Constants.suffix);
					}else if("createdate".equalsIgnoreCase(filter.getPropertyName())){
			    		  calendar1.setTime((Date) filter.getMatchValue());
				    	  Timestamp createdate= new Timestamp(calendar1.getTimeInMillis());
				    	  criteria.andCeatedateGreaterThanOrEqualTo(createdate);
			    	}else if("createdate2".equalsIgnoreCase(filter.getPropertyName())){
			    		 String date2=sdf.format(filter.getMatchValue())+" 23:59:59";
			    		 calendar2.setTime(format.parse(date2));
				    	 Timestamp createdate2= new Timestamp(calendar2.getTimeInMillis());
				    	 criteria.andCeatedateLessThanOrEqualTo(createdate2);
			    	}
			    	
				} else {
					for (String param : filter.getPropertyNames()) {
						System.out.println(param+ filter.getMatchValue()+ filter.getMatchType());
					}
				}
			}
	    	Assert.notNull(page, "page is not null.");
			if (page.isAutoCount()) {
				long totalCount = logMapper.countByExample(example);
				page.setTotalCount(totalCount);  
			}
			Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");
			example.setStartRow(page.getFirst());
			example.setEndRow(page.getLastIndex());
			if (page.isOrderBySetted()) {
				String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
				String[] orderArray = StringUtils.split(page.getOrder(), ',');

				Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
				String orderBy = ""; 
				for (int i = 0; i < orderByArray.length; i++) {
					orderBy = orderBy + orderByArray[i] +" "+ orderArray[i] +",";
				}
				example.setOrderByClause(orderBy.substring(0, orderBy.length()-1));
			}
			List<SysLog> result = logMapper.selectByExampleAndPage(example);
			page.setResult(result);
			return page;
		}
	
	/**
	 * 批量删除日志
	 * @param idList
	 */
	@Transactional(readOnly=false)
	public void deleteLog(List<BigDecimal> idList){
		logMapper.deleteBatchByPrimaryKey_foreach_list(idList);
	}

    /**
	 * 导出日志
	 * @param 
	 */
	public List<SysLog> getLoglist(String operationcertsn,String operationtype,Date createdate,Date createdate2){
		return logMapper.getListLog(operationcertsn,operationtype,createdate,createdate2);
	}
}
