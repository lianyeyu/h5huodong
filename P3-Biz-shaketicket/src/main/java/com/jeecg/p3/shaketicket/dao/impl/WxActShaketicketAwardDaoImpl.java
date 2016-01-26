package com.jeecg.p3.shaketicket.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.shaketicket.dao.WxActShaketicketAwardDao;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketAward;

/**
 * 描述：</b>WxActShaketicketAwardDaoImpl<br>
 * @author：pituo
 * @since：2015年12月24日 11时08分30秒 星期四 
 * @version:1.0
 */
@Repository("wxActShaketicketAwardDao")
public class WxActShaketicketAwardDaoImpl extends GenericDaoDefault<WxActShaketicketAward> implements WxActShaketicketAwardDao{

	@Override
	public Integer count(PageQuery<WxActShaketicketAward> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActShaketicketAward> queryPageList(
			PageQuery<WxActShaketicketAward> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActShaketicketAward> wrapper = new PageQueryWrapper<WxActShaketicketAward>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActShaketicketAward>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActShaketicketAward> queryRemainAwardsByActId(String actId) {
		// TODO Auto-generated method stub
		return (List<WxActShaketicketAward>)super.query("queryRemainAwardsByActId",actId);
	}

	@Override
	public List<WxActShaketicketAward> queryAwards(String jwid) {
		// TODO Auto-generated method stub
		return (List<WxActShaketicketAward>)super.query("queryAwards",jwid);
	}


}

