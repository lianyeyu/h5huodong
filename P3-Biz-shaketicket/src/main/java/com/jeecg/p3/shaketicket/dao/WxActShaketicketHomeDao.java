package com.jeecg.p3.shaketicket.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.shaketicket.entity.WxActShaketicketHome;

/**
 * 描述：</b>WxActShaketicketHomeDao<br>
 * @author：pituo
 * @since：2015年12月22日 19时03分50秒 星期二 
 * @version:1.0
 */
public interface WxActShaketicketHomeDao extends GenericDao<WxActShaketicketHome>{
	
	public Integer count(PageQuery<WxActShaketicketHome> pageQuery);
	
	public List<WxActShaketicketHome> queryPageList(PageQuery<WxActShaketicketHome> pageQuery,Integer itemCount);
	
}

