package com.jeecg.p3.jiugongge.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.jiugongge.entity.WxActJiugongge;

/**
 * 描述：</b>WxActJiugonggeDao<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分11秒 星期一 
 * @version:1.0
 */
public interface WxActJiugonggeDao extends GenericDao<WxActJiugongge>{
	
	public Integer count(PageQuery<WxActJiugongge> pageQuery);
	
	public List<WxActJiugongge> queryPageList(PageQuery<WxActJiugongge> pageQuery,Integer itemCount);
	
	public List<WxActJiugongge> queryActs(String jwid);
	
}

