package com.bl.book.dao;

import java.util.List;

import com.bl.book.beans.bo.Reader;
import com.bl.book.beans.bo.ReaderStatus;
import com.bl.book.condition.child.ReaderCond;
/**
 * @功能描述 操作读者的dao接口
 * @author chao
 *
 */
public interface ReaderDao {
	/**
	 * @功能描述 新增用户
	 */
	int insert(Reader reader);
	/**
	 * @功能描述 获取读者状态
	 */
	List<ReaderStatus> queryAllStatus();
	/**
	 * @功能描述 获取读者分页记录数
	 */
	int queryCount(ReaderCond cond);
	/**
	 * @功能描述 分页查询
	 */
	List<Reader> getByPage(ReaderCond cond);
	/**
	 * @功能描述 根据读者id查询读者信息
	 */
	Reader findById(Reader reader);
	/**
	 * @功能描述 更新读者状态
	 */
	int updateStatus(Reader reader);
	/**
	 * @功能描述 验证读者和图书卡是否能用
	 */
	int validate(String string);
	/**
	 * @功能描述 根据身份证查询正常使用状态下的读者
	 */
	List<Reader> getByS(Reader reader);
}
