package com.jeecg.p3.gzbargain.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainRegistrationDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;

/**
 * 描述：</b>WxActBargainRegistrationDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分36秒 星期四 
 * @version:1.0
 */
@Repository("gzWxActBargainRegistrationDao")
public class GzWxActBargainRegistrationDaoImpl extends GenericDaoDefault<GzWxActBargainRegistration> implements GzWxActBargainRegistrationDao{

	@Override
	public GzWxActBargainRegistration queryRegistrationByOpenidAndActId(
			String openid, String actId) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("openid", openid);
		param.put("actId", actId);
		return (GzWxActBargainRegistration)super.queryOne("queryRegistrationByOpenidAndActId",param);
	}

	@Override
	public void updateBargainPrice(String id, BigDecimal cutPrice) {
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("id", id);
		param.put("cutPrice", cutPrice);
		param.put("updateTime", new Date());
		super.update("updateBargainPrice", param);
	}


}

