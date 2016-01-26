package com.jeecg.p3.commonftb.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.commonftb.dao.WxActCommonftbRegistrationDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;

/**
 * 描述：</b>WxActCommonftbRegistrationDaoImpl<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
@Repository("wxActCommonftbRegistrationDao")
public class WxActCommonftbRegistrationDaoImpl extends GenericDaoDefault<WxActCommonftbRegistration> implements WxActCommonftbRegistrationDao{

	@Override
	public Integer count(PageQuery<WxActCommonftbRegistration> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActCommonftbRegistration> queryPageList(
			PageQuery<WxActCommonftbRegistration> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActCommonftbRegistration> wrapper = new PageQueryWrapper<WxActCommonftbRegistration>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActCommonftbRegistration>)super.query("queryPageList",wrapper);
	}

	@Override
	public WxActCommonftbRegistration queryRegistrationByOpenidAndActId(
			String openid, String actId) {
		// TODO Auto-generated method stub
			Map<String,String> param = Maps.newConcurrentMap();
			param.put("openid", openid);
			param.put("actId", actId);
			return (WxActCommonftbRegistration)super.queryOne("queryRegistrationByOpenidAndActId",param);
	}

	@Override
	public void updateBargainPrice(String id, BigDecimal cutPrice) {
		// TODO Auto-generated method stub
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("id", id);
		param.put("cutPrice", cutPrice);
		param.put("updateTime", new Date());
		super.update("updateBargainPrice", param);
	}


}

