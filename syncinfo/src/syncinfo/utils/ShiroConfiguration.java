package syncinfo.utils;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import syncinfo.service.SecurityRealm;

/**
 * 配置shiro,设置URL，资源访问的权限
 * @author lvsl
 *
 */
@Configuration
public class ShiroConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(
			@Qualifier("securityManager") DefaultWebSecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		Properties prop = new Properties();


		String logurlString = "";
		log.info("============ShiroConfiguration logurlString========== {}", logurlString);
		
		bean.setSecurityManager(manager);
		// 配置登录的url和登录成功的url
		bean.setUnauthorizedUrl("/main/error/401");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		filterChainDefinitionMap.put("/staff/cert/userself*", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/static/**", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/*Service/**", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/*Service.wsdl", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/main/userself", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/page/login", "authc"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/init", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/init/system", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/init/createDept", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/init/createAdmin", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/druid", "anon"); // 表示可以匿名访问
//		filterChainDefinitionMap.put("/druid/**", "anon");
//		filterChainDefinitionMap.put("/NetSignGM.cab", "anon");
//		filterChainDefinitionMap.put("/main", "authc");
//		filterChainDefinitionMap.put("/logout", "logout");
//		filterChainDefinitionMap.put("/**", "authc");// 表示需要认证才可以访问
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}
    //配置核心安全事务管理器
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("securityRealm")SecurityRealm securityRealm,@Qualifier("shiroEhcacheManager")EhCacheManager ecc) {
       // System.err.println("--------------shiro已经加载----------------");
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(securityRealm);
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 --> 
        manager.setCacheManager(ecc);
        return manager;
    }
    
    @Bean(name="shiroEhcacheManager")
    public EhCacheManager getEhCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:cache/ehcache-shiro.xml");  
        return em;  
    }  
  

    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean(name="sessionDAO")
    public MemorySessionDAO memorySessionDAO(){
    	return new MemorySessionDAO();
    }
    @Bean(name="sessionManager")
    public DefaultWebSessionManager defaultMemorySessionDAO(@Qualifier("sessionDAO")MemorySessionDAO sessionDao){
    	DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
    	sessionManager.setSessionDAO(sessionDao);
    	return sessionManager;
    }
    
	public static String getRootPath(){ 

    	String result = ShiroConfiguration.class.getResource("ShiroConfiguration.class").toString(); 
    	int index = result.indexOf("WEB-INF"); 
    	if(index == -1){ 
    		index = result.indexOf("bin"); 
    	} 
    	result = result.substring(0,index); 
    	if(result.startsWith("file")){ 
    	// 当class文件在class文件中时，返回"file:/F:/ ..."样的路径 
    		if(result.substring(7).startsWith(":")){
    			result = result.substring(6); 
    		}else {
    			result = result.substring(5); 
    		}
    	} 
    	if(result.endsWith("/"))
    		result = result.substring(0,result.length()-1);//不包含最后的"/" 
    	return result; 

    	} 

    /**  
     * @Title: getPath  
     * @Description: 获取项目的相对路径下文件的绝对路径
     * @author vincent
     *
     * @param parentDir
     * @param fileName
     * @return 一个绝对路径
     * @throws  
     */
    public static String getPath(String parentDir, String fileName) {

        String path = null;

        String userdir = System.getProperty("user.dir");

        String userdirName = new File(userdir).getName();

        if (userdirName.equalsIgnoreCase("lib")

                || userdirName.equalsIgnoreCase("bin")) {

            File newf = new File(userdir);

            File newp = new File(newf.getParent());

            if (fileName.trim().equals("")) {

                path = newp.getPath() + File.separator + parentDir;
            } else {

                path = newp.getPath() + File.separator + parentDir
                        + File.separator + fileName;
            }
        } else {

            if (fileName.trim().equals("")) {
                path = userdir + File.separator + parentDir;

            } else {
                path = userdir + File.separator + parentDir + File.separator
                        + fileName;
            }
        }
        return path;

    }
	
}