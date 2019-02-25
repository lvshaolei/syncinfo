package syncinfo.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName：Constants
 * @Description：TODO
 * @author xuchy
 * @date： 2013-4-10 
 * @version 6.1.001.1
 */
public class Constants implements Serializable {
	
	public static final String SYSADMIN = "00000000000000000000000000000001";//超级管理员ID
	public static final String COMMAND = "command";
	public static final String prefix="%";
	public static final String suffix="%";
	public static final String DESCRIPTION="";

	
	/**
	 * 证书扩展
	 * 
	 * @author vincent
	 * @date 2015-9-16
	 */
	public static class Extend {
		
		public final static String POST = "1.3.6.1.4.1.27971.2.1.1"; // 扩展项职务
		public final static String USERNO = "1.3.6.1.4.1.27971.2.1.2"; // 扩展项用户编号
	}
	
	/**
	 * 加密
	 * @author vincent
	 * @date 2015-9-16
	 */
	public static class Crypto {

		public static final String DEFAULT_PASSWORD = "123456";
		public static final int SALT_SIZE = 8;	
	}

	/**
	 * 
	 * @ClassName：Page
	 * @Description：分页
	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class Page {
		// 每页数量
		public static final int DEFAULT_PAGE_SIZE = 15;
	}
	/**
	 * 
	 * @ClassName：ReturnType
	 * @Description：操作结果

	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class ReturnType {

		public final static int SUCCESS = 0; // 操作成功
		public final static int FAIL = -1; // 操作失败
		public final static String CHECKREJECT = "1"; // 审核拒绝   

	}
	
	public static class CertSort {
		public static final int RSA_SIGNCERT = 1;
		public static final int RSA_DOUBLECERT = 2;
		public static final int SM2_SIGNCERT = 3;
		public static final int SM2_DOUBLECERT = 4;
		public static final int ERRORNUM = 10;
		
//		public static int getCertSort(String caid, String templateid){
//			CAmessage caInfo = RaConfig.getCAlist(caid);
//			String alg = caInfo.getSort();
//			List<Template> templateList = RaConfig.getUserTemplate_List(caid);	
//			for (int i = 0; i < templateList.size(); i++) {
//				Template template = (Template) templateList.get(i);
//				if (template.getTemplateid().equals(templateid)) {
//					String isdual = template.getIsdual();
//					if(alg.equalsIgnoreCase("RSA") && isdual.equalsIgnoreCase("1")){
//						return RSA_DOUBLECERT;
//					}else if(alg.equalsIgnoreCase("RSA") && isdual.equalsIgnoreCase("0")){
//						return RSA_SIGNCERT;
//					}else if(alg.equalsIgnoreCase("SM2") && isdual.equalsIgnoreCase("1")){
//						return SM2_DOUBLECERT;
//					}else if(alg.equalsIgnoreCase("SM2") && isdual.equalsIgnoreCase("0")){
//						return SM2_SIGNCERT;
//					}
//				}
//				
//			}	
//			
//			return ERRORNUM;
//		}
	}
	/**
	 * 
	 * @ClassName：LogType
	 * @Description：日志类型 
	 * 1:login validation
	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class LogType {

		public static final int LOGIN = 1;
		public static final int CERT = 2;
		public static final int VALID = 3;
        public static final int CHECK = 4;
		public static final int ROLE = 5;
        public static final int DBERROR = 6;
		public static final int UNKNOW = 9;
		public static final int SENDBSTRDATA = 7;//发送备案系统
		public static final int CONFIG = 8;
	    public static final int WebService = 10;

	}
	/**
	 * 记录用户角色名称
	 * @ClassName：UserRoleName
	 * @Description：TODO
	 * @author xuchy
	 * @date： 2013-5-22 
	 * @version 6.1.001.1
	 */
	public static class UserRoleName{
		public static final String SA = "SA";
		public static final String AA = "AA";
		public static final String BO = "BO";
		public static final String AO = "AO";
	}
	/**
	 * 
	 * @ClassName：CertStatusRes
	 * @Description：证书状态

	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class CertStatusRes {

		public static final String NOSTATE = "0";
		public static final String NOTDOWNLOAD = "1";
		public static final String NORMAL = "2";
		public static final String LOCKED = "3";
		public static final String REVOKED = "4";
		
		//证书操作中状态
		public static final String APPLYING = "11";
		public static final String UPDATING = "12";
		public static final String REVOKING = "13";

	}
	/**
	 * 
	 * @ClassName：CertStatusRes
	 * @Description：证书状态

	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class CheckStatus {
		//初始状态
		public static final String NOSTATE = "0";
		//一级审核通过
		public static final String PASS = "1";
		//未选择最终审核人
		public static final String NOCHECK = "2";
	}
	/**
	 * 
	 * @ClassName：CertSymAlg
	 * @Description：加密加密密钥的对称加密算法

	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class CertSymAlg {
		
		public static final String RSA = "RSA";
		public static final String SM2 = "SM2";
		
	}
	/**
	 * 
	 * @ClassName：StatusRes
	 * @Description：用户、企业、企业员工状态

	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class StatusRes {

		public static final String NORMAL = "0";
		public static final String LOGOFF = "1";
		public static final String INUSE = "2";

	}

	/**
	 * 
	 * @ClassName：RequestType
	 * @Description：申请类型

	 * @author xuchy
	 * @date： 2013-4-28 
	 * @version 6.1.001.1
	 */
	public static class RequestType {
		
		public static final int APPLYUSER = 1;
		public static final int UPDATEUSER = 2;
		public static final int DELETEUSER = 9;

		public static final int REQUESTCERT = 3;
		public static final int UPDATECERT = 4;
		public static final int REVOKECERT = 5;
		public static final int REGET2CODE = 6;
		public static final int DOWNCERT = 7;
		public static final int OPTlOG = 10;
		
		public static final int LOCKCERT = 8;
		public static final int UNLOCKCERT = 11;
		public static final int EXPORTCERT = 12;
	}
	
	/**
	 * 备案系统的opeid
	 * @author daiyi
	 *
	 */
	public static class BSTR_OpeType {
		
		public static final int MANAGER_APPLYCERT = 11;//申请管理员证书
		public static final int MANAGER_DOWNCERT = 12;//下载管理员证书
		public static final int MANAGER_UPDATECERT = 13;//更新管理员证书
		public static final int MANAGER_FREEZECERT = 14;//冻结管理员证书
		public static final int MANAGER_UNFREEZECERT = 15;//解冻管理员证书
		public static final int MANAGER_REVOKECERT = 16;//作废管理员证书
		
		public static final int OPERATOR_APPLYCERT = 21;//申请操作员证书
		public static final int OPERATOR_DOWNCERT = 22;//下载操作员证书
		public static final int OPERATOR_UPDATECERT = 23;//更新操作员证书
		public static final int OPERATOR_FREEZECERT = 24;//冻结操作员证书
		public static final int OPERATOR_UNFREEZECERT = 25;//解冻操作员证书
		public static final int OPERATOR_REVOKECERT = 26;//作废操作员证书
		
		public static final int USER_APPLYCERT = 41;//申请用户证书
		public static final int USER_DOWNCERT = 42;//下载用户证书
		public static final int USER_UPDATECERT = 43;//更新用户证书
		public static final int USER_FREEZECERT = 44;//冻结用户证书
		public static final int USER_UNFREEZECERT = 45;//解冻用户证书
		public static final int USER_REVOKECERT = 46;//作废用户证书
		
		public static final int USER_REGET2CODE = 47;//证书重取两码
	}
}
