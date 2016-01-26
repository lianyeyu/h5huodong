package com.jeecg.p3.gzbargain.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.gzbargain.entity.GzWxActBargainRecord;

/**
 * 描述：</b>WxActBargainRecordDao<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainRecordDao extends GenericDao<GzWxActBargainRecord>{
	
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
	
}

