package com.jeecg.p3.jiugongge.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggePrizesDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggePrizes;

/**
 * 描述：</b>WxActJiugonggePrizesDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
@Repository("wxActJiugonggePrizesDao")
public class WxActJiugonggePrizesDaoImpl extends GenericDaoDefault<WxActJiugonggePrizes> implements WxActJiugonggePrizesDao{

	@Override
	public Integer count(PageQuery<WxActJiugonggePrizes> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActJiugonggePrizes> queryPageList(
			PageQuery<WxActJiugonggePrizes> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActJiugonggePrizes> wrapper = new PageQueryWrapper<WxActJiugonggePrizes>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActJiugonggePrizes>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActJiugonggePrizes> queryByActId(String actid) {
		// TODO Auto-generated method stub
		return (List)super.query("queryByActId",actid);
	}

	@Override
	public List<WxActJiugonggePrizes> queryByAwardIdAndActId(String awardid,String actId) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("awardId", awardid);
		param.put("actId", actId);
		return (List)super.query("queryByAwardIdAndActId",param);
	}

	@Override
	public List<WxActJiugonggePrizes> queryPrizes(String jwid) {
		// TODO Auto-generated method stub
		return (List)super.query("queryPrizes",jwid);
	}

	@Override
	public List<WxActJiugonggePrizes> queryRemainAwardsByActId(String actid) {
		// TODO Auto-generated method stub
		return (List)super.query("queryRemainAwardsByActId",actid);
	}


}

