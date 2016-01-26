package com.jeecg.p3.commonftb.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.commonftb.dao.WxActCommonftbRecordDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRecord;

/**
 * 描述：</b>WxActCommonftbRecordDaoImpl<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
@Repository("wxActCommonftbRecordDao")
public class WxActCommonftbRecordDaoImpl extends GenericDaoDefault<WxActCommonftbRecord> implements WxActCommonftbRecordDao{

	@Override
	public Integer count(PageQuery<WxActCommonftbRecord> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActCommonftbRecord> queryPageList(
			PageQuery<WxActCommonftbRecord> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActCommonftbRecord> wrapper = new PageQueryWrapper<WxActCommonftbRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActCommonftbRecord>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationId(
			String registrationId) {
		// TODO Auto-generated method stub
		return (List<WxActCommonftbRecord>)super.query("queryBargainRecordListByRegistrationId",registrationId);
	}

	@Override
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationIdAndOpenid(
			String registrationId, String openid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("registrationId", registrationId);
		param.put("openid", openid);
		return (List<WxActCommonftbRecord>)super.query("queryBargainRecordListByRegistrationIdAndOpenid",param);
	}


}

