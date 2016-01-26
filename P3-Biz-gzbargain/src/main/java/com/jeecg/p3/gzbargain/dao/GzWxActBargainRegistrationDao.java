package com.jeecg.p3.gzbargain.dao;

import java.math.BigDecimal;

import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;

/**
 * 描述：</b>WxActBargainRegistrationDao<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分36秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainRegistrationDao extends GenericDao<GzWxActBargainRegistration>{
	
	/**
	 * 根据openid和活动id查询
	 * @param openid
	 * @param actId
	 * @return
	 */
	public GzWxActBargainRegistration queryRegistrationByOpenidAndActId(String openid,String actId);
	
	
	/**
	 * 砍价信息更新
	 * @param id
	 * @param cutPrice
	 */
	public void updateBargainPrice(String id,BigDecimal cutPrice);
	
}

