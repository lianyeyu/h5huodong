package com.jeecg.p3.shaketicket.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.shaketicket.dao.WxActShaketicketHomeDao;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketHome;

/**
 * 描述：</b>WxActShaketicketHomeDaoImpl<br>
 * @author：pituo
 * @since：2015年12月22日 19时03分50秒 星期二 
 * @version:1.0
 */
@Repository("wxActShaketicketHomeDao")
public class WxActShaketicketHomeDaoImpl extends GenericDaoDefault<WxActShaketicketHome> implements WxActShaketicketHomeDao{

	@Override
	public Integer count(PageQuery<WxActShaketicketHome> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActShaketicketHome> queryPageList(
			PageQuery<WxActShaketicketHome> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActShaketicketHome> wrapper = new PageQueryWrapper<WxActShaketicketHome>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActShaketicketHome>)super.query("queryPageList",wrapper);
	}


}

