package com.jeecg.p3.commonftb.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.commonftb.dao.WxActCommonftbAwardsDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbAwards;

/**
 * 描述：</b>WxActCommonsjAwardsDaoImpl<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分47秒 星期一 
 * @version:1.0
 */
@Repository("wxActCommonftbAwardsDao")
public class WxActCommonftbAwardsDaoImpl extends GenericDaoDefault<WxActCommonftbAwards> implements WxActCommonftbAwardsDao{

	@Override
	public Integer count(PageQuery<WxActCommonftbAwards> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActCommonftbAwards> queryPageList(
			PageQuery<WxActCommonftbAwards> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActCommonftbAwards> wrapper = new PageQueryWrapper<WxActCommonftbAwards>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActCommonftbAwards>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActCommonftbAwards> queryBargainAwardsByActIdAndOpenid(
			String actId, String openid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		param.put("openid", openid);
		return (List<WxActCommonftbAwards>)super.query("queryBargainAwardsByActIdAndOpenid",param);
	}
	@Override
	public List<WxActCommonftbAwards> queryBargainAwardsByActId(
			String actId) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actId);
		return (List<WxActCommonftbAwards>)super.query("queryBargainAwardsByActId",param);
	}

	@Override
	public Integer getMaxAwardsSeq(String actId) {
		// TODO Auto-generated method stub
		Integer maxAwardsSeq = (Integer) super.queryOne("getMaxAwardsSeq",actId);
		return maxAwardsSeq==null?0:maxAwardsSeq;
	}

	@Override
	public List<WxActCommonftbAwards> queryBargainAwardsByMainActIdOpenid(String mainActId,String openid) {
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("mainActId", mainActId);
		param.put("openid", openid);
		return (List<WxActCommonftbAwards>)super.query("queryBargainAwardsByMainActIdOpenid",param);
	}


}

