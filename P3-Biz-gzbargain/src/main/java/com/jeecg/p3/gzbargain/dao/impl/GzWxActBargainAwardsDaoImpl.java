package com.jeecg.p3.gzbargain.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainAwardsDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainAwards;

/**
 * 描述：</b>WxActBargainAwardsDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分34秒 星期四 
 * @version:1.0
 */
@Repository("gzWxActBargainAwardsDao")
public class GzWxActBargainAwardsDaoImpl extends GenericDaoDefault<GzWxActBargainAwards> implements GzWxActBargainAwardsDao{

	@Override
	public Integer getMaxAwardsSeq(String actId) {
		Integer maxAwardsSeq = (Integer) super.queryOne("getMaxAwardsSeq",actId);
		return maxAwardsSeq==null?0:maxAwardsSeq;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GzWxActBargainAwards> queryBargainAwardsByActIdAndOpenid(
			String actId, String openid) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		param.put("openid", openid);
		return (List<GzWxActBargainAwards>)super.query("queryBargainAwardsByActIdAndOpenid",param);
	}

	@Override
	public Integer count(PageQuery<GzWxActBargainAwards> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GzWxActBargainAwards> queryPageList(
			PageQuery<GzWxActBargainAwards> pageQuery,Integer itemCount) {
		PageQueryWrapper<GzWxActBargainAwards> wrapper = new PageQueryWrapper<GzWxActBargainAwards>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<GzWxActBargainAwards>)super.query("queryPageList",wrapper);
	}
}

