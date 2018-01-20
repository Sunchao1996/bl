package com.bl.book.service;

import java.util.List;
import java.util.Map;

import com.bl.book.beans.vo.ShowCharts;

/**
 * @功能描述 展示报表的service接口
 * @author chao
 *
 */
public interface ShowService {
	/**
	 * @功能描述 获取报表数据
	 */
	List<ShowCharts> queryList(Map<String, Object> condMap);
}
