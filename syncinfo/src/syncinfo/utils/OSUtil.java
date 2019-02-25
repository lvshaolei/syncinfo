package syncinfo.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import syncinfo.utils.DateUtils;

/*  
 * 返回客户端信息工具类  
 * by netwild  
 */
public class OSUtil {

	private String info = "";
	private String ip = "";
	private String executeTime = "";
	
	private String explorerVer = "未知";
	private String OSVer = "未知";

	public OSUtil(String info) {
		this.info = info;
	}
	
	public OSUtil(HttpServletRequest request) {
		this.info = request.getHeader("user-agent");
		this.ip = getIpAddress(request);
		this.executeTime = executeTime(request);
		
	}
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	
	public String getExecuteTime(){
		return executeTime;
	}
	
	/**
	 * 获取执行时间
	 * @param request
	 * @return
	 */
	public String executeTime(HttpServletRequest request){
		
		return DateUtils.getExecuteTime((Long)request.getAttribute("__BEGINTIME"));
	}
	
	public String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}
	
	/*
	 * 获取核心浏览器名称
	 */
	public String getExplorerName() {
		String str = info;
		Pattern pattern = Pattern.compile("");
		Matcher matcher;
		if (info.indexOf("MSIE") != -1|| info.toLowerCase().indexOf("rv:11") != -1) {
			str = "MSIE"; // 微软IE
			pattern = Pattern.compile(str + "\\s([0-9.]+)");
		} else if (info.indexOf("Firefox") != -1) {
			str = "Firefox"; // 火狐
			pattern = Pattern.compile(str + "\\/([0-9.]+)");
		} else if (info.indexOf("Chrome") != -1) {
			str = "Chrome"; // Google
			pattern = Pattern.compile(str + "\\/([0-9.]+)");
		} else if (info.indexOf("Opera") != -1) {
			str = "Opera"; // Opera
			pattern = Pattern.compile("Version\\/([0-9.]+)");
		}
		matcher = pattern.matcher(info);
		if (matcher.find() && matcher.end()>matcher.start())
			explorerVer = matcher.group(1);
		return str;
	}

	/*
	 * 获取核心浏览器版本
	 */
	public String getExplorerVer() {
		return this.explorerVer;
	}

	/*
	 * 获取浏览器插件名称（例如：遨游、世界之窗等）
	 */
	public String getExplorerPlug() {
		String str = "无";
		if (info.indexOf("Maxthon") != -1)
			str = "Maxthon"; // 遨游
		return str;
	}

	/*
	 * 获取操作系统名称
	 */
	public String getOSName() {
		String str = info;
		Pattern pattern = Pattern.compile("");
		Matcher matcher;
		if (info.indexOf("Windows") != -1) {
			str = "Windows"; // Windows NT 6.1
			pattern = Pattern.compile(str + "\\s([a-zA-Z0-9]+\\s[0-9.]+)");
		}else{
			getClientOS();
		}
		matcher = pattern.matcher(info);
		if (matcher.find() && matcher.end()>matcher.start())
			OSVer = matcher.group(1);
		return str+" "+OSVer;
	}

	/*
	 * 获取操作系统版本
	 */
	public String getOSVer() {
		return this.OSVer;
	}
	/**
     * 获取客户端操作系统信息，目前只匹配Win 7、WinXP、Win2003、Win2000、MAC、WinNT、Linux、Mac68k、Win9x
     * @param userAgent request.getHeader("user-agent")的返回值
     * @return
     */
	public String getClientOS(){
		
		String userAgent = info;
        String cos = "unknow os";
        
        Pattern p = Pattern.compile(".*(Windows NT 6\\.1).*");
        Matcher m = p.matcher(userAgent);
//        if(m.find())
//        {
//            cos = "Win 7";
//            return cos;
//        }
//        
//        p = Pattern.compile(".*(Windows NT 5\\.1|Windows XP).*");
//        m = p.matcher(userAgent);
//        if(m.find())
//        {
//            cos = "WinXP";
//            return cos;
//        }
//        
//        p = Pattern.compile(".*(Windows NT 5\\.2).*");
//        m = p.matcher(userAgent);
//        if(m.find())
//        {
//            cos = "Win2003";
//            return cos;
//        }
//        
//        p = Pattern.compile(".*(Win2000|Windows 2000|Windows NT 5\\.0).*");
//        m = p.matcher(userAgent);
//        if(m.find())
//        {
//            cos = "Win2000";
//            return cos;
//        }
        
        p = Pattern.compile(".*(Mac|apple|MacOS8).*");
        m = p.matcher(userAgent);
        if(m.find())
        {
            cos = "MAC";
            return cos;
        }
        
        p = Pattern.compile(".*(WinNT|Windows NT).*");
        m = p.matcher(userAgent);
        if(m.find())
        {
            cos = "WinNT";
            return cos;
        }
        
        p = Pattern.compile(".*Linux.*");
        m = p.matcher(userAgent);
        if(m.find())
        {
            cos = "Linux";
            return cos;
        }
        
        p = Pattern.compile(".*(68k|68000).*");
        m = p.matcher(userAgent);
        if(m.find())
        {
            cos = "Mac68k";
            return cos;
        }
        
        p = Pattern.compile(".*(9x 4.90|Win9(5|8)|Windows 9(5|8)|95/NT|Win32|32bit).*");
        m = p.matcher(userAgent);
        if(m.find())
        {
            cos = "Win9x";
            return cos;
        }
        
        return cos;
    }
	

	/**
	* @param args
	 * @throws UnknownHostException 
	*/
	public static void main(String[] args) throws UnknownHostException {
System.out.println(InetAddress.getLocalHost().getHostAddress());

System.out.println(System.getProperty("os.name"));
System.out.println(System.getProperty("os.version"));
	}


}
