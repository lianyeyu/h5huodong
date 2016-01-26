package com.jeecg.p3.commonftb.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.commonftb.dao.WxActCommonftbCouponDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;

/**
 * 描述：</b>WxActCommonsjCouponDaoImpl<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分47秒 星期一 
 * @version:1.0
 */
@Repository("wxActCommonftbCouponDao")
public class WxActCommonftbCouponDaoImpl extends GenericDaoDefault<WxActCommonftbCoupon> implements WxActCommonftbCouponDao{

	@Override
	public Integer count(PageQuery<WxActCommonftbCoupon> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActCommonftbCoupon> queryPageList(
			PageQuery<WxActCommonftbCoupon> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActCommonftbCoupon> wrapper = new PageQueryWrapper<WxActCommonftbCoupon>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActCommonftbCoupon>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActCommonftbCoupon> queryBargainCouponListByActIdAndCost(String actId, BigDecimal cost) {
		// TODO Auto-generated method stub
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		param.put("cost", cost);
		return (List<WxActCommonftbCoupon>)super.query("queryBargainCouponListByActIdAndCost",param);

	}


}

