package com.jeecg.p3.jiugongge.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRegistrationDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRegistration;

/**
 * 描述：</b>WxActJiugonggeRegistrationDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年11月17日 12时14分13秒 星期二 
 * @version:1.0
 */
@Repository("wxActJiugonggeRegistrationDao")
public class WxActJiugonggeRegistrationDaoImpl extends GenericDaoDefault<WxActJiugonggeRegistration> implements WxActJiugonggeRegistrationDao{

	@Override
	public Integer count(PageQuery<WxActJiugonggeRegistration> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActJiugonggeRegistration> queryPageList(
			PageQuery<WxActJiugonggeRegistration> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActJiugonggeRegistration> wrapper = new PageQueryWrapper<WxActJiugonggeRegistration>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActJiugonggeRegistration>)super.query("queryPageList",wrapper);
	}

	@Override
	public WxActJiugonggeRegistration queryRegistrationByOpenidAndActIdAndJwid(
			String openid, String actId, String jwid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("openid", openid);
		param.put("actId", actId);
		param.put("jwid", jwid);
		return (WxActJiugonggeRegistration)super.queryOne("queryRegistrationByOpenidAndActIdAndJwid",param);
	}


}

