package com.jeecg.p3.commonftb.dao.impl;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import com.jeecg.p3.commonftb.dao.WxActCommonftbMainDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbMain;

/**
 * 描述：</b>WxActCommonftbMainDaoImpl<br>
 * @author：pituo
 * @since：2016年01月05日 10时52分01秒 星期二 
 * @version:1.0
 */
@Repository("wxActCommonftbMainDao")
public class WxActCommonftbMainDaoImpl extends GenericDaoDefault<WxActCommonftbMain> implements WxActCommonftbMainDao{

	@Override
	public Integer count(PageQuery<WxActCommonftbMain> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActCommonftbMain> queryPageList(
			PageQuery<WxActCommonftbMain> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActCommonftbMain> wrapper = new PageQueryWrapper<WxActCommonftbMain>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActCommonftbMain>)super.query("queryPageList",wrapper);
	}


}

