package com.jeecg.p3.gzbargain.service;

import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;

/**
 * 描述：</b>WxActBargainRegistrationService<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分36秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainRegistrationService {
	
	/**
	 * 根据openid和活动id查询
	 * @param openid
	 * @param actId
	 * @return
	 */
	public GzWxActBargainRegistration queryRegistrationByOpenidAndActId(String openid,String actId);
	
	/**
	 * 根据id查询报名信息
	 * @param id
	 * @return
	 */
	public GzWxActBargainRegistration queryBargainRegistrationById(String id);
	
	public void add(GzWxActBargainRegistration gzWxActBargainRegistration);
	
	public void update(GzWxActBargainRegistration gzWxActBargainRegistration);
	
	public void deleteByPriKey(String id);
	
	
	
}

