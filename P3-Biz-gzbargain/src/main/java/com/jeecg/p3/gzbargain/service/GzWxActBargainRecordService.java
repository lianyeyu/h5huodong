package com.jeecg.p3.gzbargain.service;

import java.util.List;

import com.jeecg.p3.gzbargain.entity.GzWxActBargainRecord;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;

/**
 * 描述：砍价帮砍记录
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainRecordService {
	
	
	/**
	 * 根据用户活动报名id查询砍价记录
	 * @param registrationId
	 * @return
	 */
	public List<GzWxActBargainRecord> queryBargainRecordListByRegistrationId(String registrationId);
	
	/**
	 * 根据用户活动报名id和openid查询砍价记录
	 * @param registrationId
	 * @return
	 */
	public List<GzWxActBargainRecord> queryBargainRecordListByRegistrationIdAndOpenid(String registrationId,String openid);
	
	/**
	 * 砍价操作
	 * @param gzWxActBargainRecord
	 */
	public String bargain(GzWxActBargainRegistration bargainRegistration,GzWxActBargainRecord gzWxActBargainRecord);
	
}

