package com.jeecg.p3.commonftb.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.commonftb.dao.WxActCommonftbDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftb;

/**
 * 描述：</b>WxActCommonftbDaoImpl<br>
 * @author：pituo
 * @since：2016年01月05日 11时07分05秒 星期二 
 * @version:1.0
 */
@Repository("wxActCommonftbDao")
public class WxActCommonftbDaoImpl extends GenericDaoDefault<WxActCommonftb> implements WxActCommonftbDao{

	@Override
	public Integer count(PageQuery<WxActCommonftb> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActCommonftb> queryPageList(
			PageQuery<WxActCommonftb> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActCommonftb> wrapper = new PageQueryWrapper<WxActCommonftb>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActCommonftb>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActCommonftb> queryCommonftbByMainActId(String mainActId) {
		// TODO Auto-generated method stub
		return (List<WxActCommonftb>)super.query("queryCommonftbByMainActId",mainActId);
	}

	@Override
	public void updateProductRemainNum(String id, Integer cutNum,
			Date lastUpdateTime) {
		// TODO Auto-generated method stub
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("id", id);
		param.put("cutNum", cutNum);
		param.put("updateTime", lastUpdateTime);
		super.update("updateProductRemainNum", param);
	}

	@Override
	public WxActCommonftb queryWxActCommonftbByMainActIdAndOpenid(
			String mainActId, String openid) {
		// TODO Auto-generated method stub
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("mainActId", mainActId);
		param.put("openid", openid);
		return (WxActCommonftb)super.queryOne("queryWxActCommonftbByMainActIdAndOpenid",param);
	}

	@Override
	public List<WxActCommonftb> queryCommonftb(String jwid) {
		// TODO Auto-generated method stub
		return (List<WxActCommonftb>)super.query("queryCommonftb",jwid);
	}


}

