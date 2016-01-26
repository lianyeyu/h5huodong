package com.jeecg.p3.gzbargain.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainRecordDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRecord;

/**
 * 描述：</b>WxActBargainRecordDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
@Repository("gzWxActBargainRecordDao")
public class GzWxActBargainRecordDaoImpl extends GenericDaoDefault<GzWxActBargainRecord> implements GzWxActBargainRecordDao{


	@SuppressWarnings("unchecked")
	@Override
	public List<GzWxActBargainRecord> queryBargainRecordListByRegistrationId(String registrationId) {
		return (List<GzWxActBargainRecord>)super.query("queryBargainRecordListByRegistrationId",registrationId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GzWxActBargainRecord> queryBargainRecordListByRegistrationIdAndOpenid(
			String registrationId, String openid) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("registrationId", registrationId);
		param.put("openid", openid);
		return (List<GzWxActBargainRecord>)super.query("queryBargainRecordListByRegistrationIdAndOpenid",param);
	}


}

