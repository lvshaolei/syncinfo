package syncinfo.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import syncinfo.utils.security.Encodes;
import syncinfo.model.Admin;
import syncinfo.model.Permission;
import syncinfo.model.Role;

import com.google.common.base.Objects;

/**
 * 管理员登录授权service(shrioRealm)
 * @author vincent
 * @date 2015-8-7
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

	@Autowired
	private AdminService adminService;
	
	@Autowired
    private RoleService roleService;

	@Autowired
    private PermissionService permissionService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        String username = String.valueOf(authcToken.getPrincipal());
        String password = new String((char[]) authcToken.getCredentials());
		 // 通过数据库进行验证
//		final Admin admin = adminService.selectByCertSN(username.toUpperCase());  
//		byte[] salt = Encodes.decodeHex(admin.getSalt());
//		ShiroUser shiroUser= new ShiroUser(admin.getAdminid(), admin.getAdminCert().getSubjectdn(), admin.getAdminname());
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, admin.getPassword(),  ByteSource.Util.bytes(salt), getName());
//	    // 验证成功在Session中保存用户信息
//		Session session = SecurityUtils.getSubject().getSession();
//		session.setAttribute("admin", admin);
		return null;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		Admin admin = adminService.selectByUsername(shiroUser.name);
		//把principals放session中 key=userId value=principals
		SecurityUtils.getSubject().getSession().setAttribute(String.valueOf(admin.getAdminid()),SecurityUtils.getSubject().getPrincipals());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//赋予角色
		for(Role role: roleService.selectRolesByUserId(admin.getAdminid())){
			info.addRole(role.getRolename());
		}
		//赋予权限
		for(Permission permission: permissionService.getPermissions(admin.getAdminid())){
			if(StringUtils.isNotBlank(permission.getPermissioncode()))
				info.addStringPermission(permission.getPermissioncode());
		}
		return info;
	}
	
		

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@SuppressWarnings("static-access")
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(adminService.HASH_ALGORITHM);
		matcher.setHashIterations(adminService.HASH_INTERATIONS); 
		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public String id;
		public String loginName;
		public String name;

		public ShiroUser(String id, String loginName, String name) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
		}

		public String getId(){
			return id;
		}

		public String getName() {
			return name;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(loginName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ShiroUser other = (ShiroUser) obj;
			if (loginName == null) {
				if (other.loginName != null) {
					return false;
				}
			} else if (!loginName.equals(other.loginName)) {
				return false;
			}
			return true;
		}
	}
	
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
 
    
    public static void main(String[] args){
    	byte[] salt = Encodes.decodeHex("3d06a5c14d010804");
    	System.out.println(ByteSource.Util.bytes(salt));
    }
}
