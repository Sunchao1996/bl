package com.bl.book.beans.bo;
/**
 * @功能描述 所有用户信息实体类
 * @author chao
 *
 */
public class User {
	private String userId;
	private String userPassword;
	private Integer userCategory;// '0超级管理员\r\n            1普通借阅员\r\n            2读者',
	private String newPassword;
	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(Integer userCategory) {
		this.userCategory = userCategory;
	}
	
}
