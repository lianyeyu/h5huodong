package com.jeecg.p3.commonftb.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonftb.entity.WxActCommonftbMain;

/**
 * 描述：</b>WxActCommonftbMainDao<br>
 * @author：pituo
 * @since：2016年01月05日 10时52分01秒 星期二 
 * @version:1.0
 */
public interface WxActCommonftbMainDao extends GenericDao<WxActCommonftbMain>{
	
	public Integer count(PageQuery<WxActCommonftbMain> pageQuery);
	
	public List<WxActCommonftbMain> queryPageList(PageQuery<WxActCommonftbMain> pageQuery,Integer itemCount);
	
}

