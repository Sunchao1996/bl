package com.bl.book.condition.child;

import com.bl.book.condition.BaseCondition;
/**
 * @功能描述 图书借阅员等除了读者之外的用户查询条件
 * @author chao
 *
 */
public class UserCond extends BaseCondition{
	private String userId;
	private String userCategory;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCategory() {
		return userCategory;
	}
	public void setUserCategory(String userCategory) {
		this.userCategory = userCategory;
	}
	
	
	
}
