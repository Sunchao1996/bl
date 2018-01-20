package com.bl.book.dao;

import com.bl.book.beans.bo.BorrowInfo;

/**
 * @功能描述 借书登记dao接口
 * @author chao
 *
 */
public interface LoanDao {
	/**
	 * @功能描述 添加借书记录
	 * @author chao
	 *
	 */
	int insert(BorrowInfo bi);
	
}
