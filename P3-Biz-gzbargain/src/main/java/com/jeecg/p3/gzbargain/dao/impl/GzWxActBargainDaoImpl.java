package com.jeecg.p3.gzbargain.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargain;

/**
 * 描述：</b>WxActBargainDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
@Repository("gzWxActBargainDao")
public class GzWxActBargainDaoImpl extends GenericDaoDefault<GzWxActBargain> implements GzWxActBargainDao{

	@Override
	public void updateProductRemainNum(String id, Integer cutNum) {
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("id", id);
		param.put("cutNum", cutNum);
		super.update("updateProductRemainNum", param);
	}

	@Override
	public Integer count(PageQuery<GzWxActBargain> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GzWxActBargain> queryPageList(
			PageQuery<GzWxActBargain> pageQuery,Integer itemCount) {
		PageQueryWrapper<GzWxActBargain> wrapper = new PageQueryWrapper<GzWxActBargain>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<GzWxActBargain>)super.query("queryPageList",wrapper);
	}
}

