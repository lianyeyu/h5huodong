package com.jeecg.p3.jiugongge.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugongge;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;

/**
 * 描述：</b>WxActJiugonggeDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分11秒 星期一 
 * @version:1.0
 */
@Repository("wxActJiugonggeDao")
public class WxActJiugonggeDaoImpl extends GenericDaoDefault<WxActJiugongge> implements WxActJiugonggeDao{

	@Override
	public Integer count(PageQuery<WxActJiugongge> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActJiugongge> queryPageList(
			PageQuery<WxActJiugongge> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActJiugongge> wrapper = new PageQueryWrapper<WxActJiugongge>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActJiugongge>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActJiugongge> queryActs(String jwid) {
		// TODO Auto-generated method stub
		return (List<WxActJiugongge>)super.query("queryActs",jwid);
	}


}

