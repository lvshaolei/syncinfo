package syncinfo.model;

import java.io.Serializable;

public class Permission implements Serializable{
	private static final long serialVersionUID = 1L;

	public Permission(String id, String pid, String name, String type, Short sort, String url, String permissioncode,
			String icon, String state, String description) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.type = type;
		this.sort = sort;
		this.url = url;
		this.permissioncode = permissioncode;
		this.icon = icon;
		this.state = state;
		this.description = description;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.ID
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.PID
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.NAME
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.TYPE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.SORT
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private Short sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.URL
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.PERMISSIONCODE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String permissioncode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.ICON
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.STATE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column DB2ADMIN.TC_PERMISSION.DESCRIPTION
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.ID
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.ID
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.ID
     *
     * @param id the value for DB2ADMIN.TC_PERMISSION.ID
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.PID
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.PID
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.PID
     *
     * @param pid the value for DB2ADMIN.TC_PERMISSION.PID
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.NAME
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.NAME
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.NAME
     *
     * @param name the value for DB2ADMIN.TC_PERMISSION.NAME
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.TYPE
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.TYPE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.TYPE
     *
     * @param type the value for DB2ADMIN.TC_PERMISSION.TYPE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.SORT
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.SORT
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public Short getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.SORT
     *
     * @param sort the value for DB2ADMIN.TC_PERMISSION.SORT
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setSort(Short sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.URL
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.URL
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.URL
     *
     * @param url the value for DB2ADMIN.TC_PERMISSION.URL
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.PERMISSIONCODE
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.PERMISSIONCODE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getPermissioncode() {
        return permissioncode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.PERMISSIONCODE
     *
     * @param permissioncode the value for DB2ADMIN.TC_PERMISSION.PERMISSIONCODE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setPermissioncode(String permissioncode) {
        this.permissioncode = permissioncode == null ? null : permissioncode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.ICON
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.ICON
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.ICON
     *
     * @param icon the value for DB2ADMIN.TC_PERMISSION.ICON
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.STATE
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.STATE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.STATE
     *
     * @param state the value for DB2ADMIN.TC_PERMISSION.STATE
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column DB2ADMIN.TC_PERMISSION.DESCRIPTION
     *
     * @return the value of DB2ADMIN.TC_PERMISSION.DESCRIPTION
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column DB2ADMIN.TC_PERMISSION.DESCRIPTION
     *
     * @param description the value for DB2ADMIN.TC_PERMISSION.DESCRIPTION
     *
     * @mbggenerated Mon Aug 17 01:32:05 CST 2015
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}