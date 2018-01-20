package com.bl.book.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.book.beans.vo.ShowCharts;
import com.bl.book.dao.ShowDao;
import com.bl.book.service.ShowService;
/**
 * @功能描述 展示报表的service接口实现类
 * @author chao
 *
 */
@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private ShowDao dao;
	@Override
	public List<ShowCharts> queryList(Map<String,Object> condMap) {		
		return dao.queryList(condMap);
	}

}
