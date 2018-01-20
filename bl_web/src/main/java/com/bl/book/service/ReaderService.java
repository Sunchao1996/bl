package com.bl.book.service;

import java.util.List;

import com.bl.book.beans.bo.Reader;
import com.bl.book.condition.child.ReaderCond;

/**
 * @功能描述 读者管理的service接口
 * @author chao
 *
 */
public interface ReaderService {
	/**
	 * @功能描述 新增读者
	 */
	int insert(Reader reader);
	/**
	 * @功能描述 分页查询读者信息
	 */
	Object getByPage(ReaderCond cond);
	/**
	 * @功能描述 重置密码
	 */
	void repwd(String readerId);
	/**
	 * @功能描述 销户读者
	 */
	void delete(String[] ids);
	/**
	 * @功能描述 恢复读者
	 */
	void recover(String[] ids);
	/**
	 * @功能描述 根据身份证获取读者信息
	 */
	List<Reader> getByS(Reader reader);
}
