package xyz.lvsl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import xyz.lvsl.jdbc.JdbcPool;

public class InitDataListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent e) {  
        System.out.println("系统初始化开始...");
        System.out.println("初始化连接池---");
        JdbcPool pool = JdbcPool.getInstance();
        pool.initPool();
        System.out.println("系统初始化结束。");
    }
}
