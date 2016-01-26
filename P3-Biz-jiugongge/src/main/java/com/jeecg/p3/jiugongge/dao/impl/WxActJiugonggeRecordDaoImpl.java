package com.jeecg.p3.jiugongge.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRecordDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRecord;

/**
 * 描述：</b>WxActJiugonggeRecordDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年11月17日 12时14分14秒 星期二 
 * @version:1.0
 */
@Repository("wxActJiugonggeRecordDao")
public class WxActJiugonggeRecordDaoImpl extends GenericDaoDefault<WxActJiugonggeRecord> implements WxActJiugonggeRecordDao{

	@Override
	public Integer count(PageQuery<WxActJiugonggeRecord> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActJiugonggeRecord> queryPageList(
			PageQuery<WxActJiugonggeRecord> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActJiugonggeRecord> wrapper = new PageQueryWrapper<WxActJiugonggeRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActJiugonggeRecord>)super.query("queryPageList",wrapper);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<WxActJiugonggeRecord> queryPageListForJoin(
			PageQuery<WxActJiugonggeRecord> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActJiugonggeRecord> wrapper = new PageQueryWrapper<WxActJiugonggeRecord>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActJiugonggeRecord>)super.query("queryPageListForJoin",wrapper);
	}

	@Override
	public List<WxActJiugonggeRecord> queryBargainRecordListByOpenidAndActidAndJwid(
			String openid, String actId, String jwid, Date currDate) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("openid", openid);
		param.put("actId", actId);
		param.put("jwid", jwid);
         if(currDate==null){
        	 return (List<WxActJiugonggeRecord>)super.query("queryBargainRecordListByOpenidAndActidAndJwid",param);
         }
         param.put("currDate", DateUtil.date2Str(currDate,"yyyy-MM-dd"));
		return (List<WxActJiugonggeRecord>)super.query("queryBargainRecordListByOpenidAndActidAndJwidAndCurdate",param);
	}
	@Override
	public List<WxActJiugonggeRecord> queryMyAwardsByOpenidAndActidAndJwid(
			String openid, String actId, String jwid) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("openid", openid);
		param.put("actId", actId);
		param.put("jwid", jwid);
		return (List<WxActJiugonggeRecord>)super.query("queryMyAwardsByOpenidAndActidAndJwid",param);
	}

	@Override
	public List<WxActJiugonggeRecord> queryBargainRecordListByActidAndJwid( String actId, String jwid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		param.put("jwid", jwid);
        return (List<WxActJiugonggeRecord>)super.query("queryBargainRecordListByActidAndJwid",param);
	}
	@Override
	public List<WxActJiugonggeRecord> exportRecordListByActidAndJwid( String actId, String jwid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		param.put("jwid", jwid);
		return (List<WxActJiugonggeRecord>)super.query("exportRecordListByActidAndJwid",param);
	}

	@Override
	public Integer getMaxAwardsSeq(String actid) {
		// TODO Auto-generated method stub
		Integer maxAwardsSeq = (Integer) super.queryOne("getMaxSeq",actid);
		return maxAwardsSeq==null?0:maxAwardsSeq;
	}


}

