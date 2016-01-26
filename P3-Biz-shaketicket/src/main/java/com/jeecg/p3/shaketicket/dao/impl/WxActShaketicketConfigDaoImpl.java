package com.jeecg.p3.shaketicket.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.shaketicket.dao.WxActShaketicketConfigDao;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketConfig;

/**
 * 描述：</b>WxActShaketicketConfigDaoImpl<br>
 * @author：pituo
 * @since：2015年12月24日 11时08分29秒 星期四 
 * @version:1.0
 */
@Repository("wxActShaketicketConfigDao")
public class WxActShaketicketConfigDaoImpl extends GenericDaoDefault<WxActShaketicketConfig> implements WxActShaketicketConfigDao{

	@Override
	public Integer count(PageQuery<WxActShaketicketConfig> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActShaketicketConfig> queryPageList(
			PageQuery<WxActShaketicketConfig> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActShaketicketConfig> wrapper = new PageQueryWrapper<WxActShaketicketConfig>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActShaketicketConfig>)super.query("queryPageList",wrapper);
	}

	@Override
	public void updateRemainNum(String actid, String jwid, String awardid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		param.put("jwid", jwid);
		param.put("awardid", awardid);
		super.update("updateRemainNum", param);
	}

	@Override
	public List<WxActShaketicketConfig> queryByActId(String actId) {
		// TODO Auto-generated method stub
		return (List<WxActShaketicketConfig>)super.query("queryByActId",actId);
	}

	@Override
	public void batchDeleteByActId(String actid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		super.delete("batchDeleteByActId", param);
	}

	@Override
	public void bactchDeleteOldAwards(List<String> ids, String actId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("ids", ids);
		param.put("actId", actId);
		super.delete("bactchDeleteOldAwards",param);
	}


}

