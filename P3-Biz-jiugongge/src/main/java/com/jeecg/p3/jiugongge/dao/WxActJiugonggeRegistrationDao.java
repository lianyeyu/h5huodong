package com.jeecg.p3.jiugongge.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRegistration;

/**
 * 描述：</b>WxActJiugonggeRegistrationDao<br>
 * @author：junfeng.zhou
 * @since：2015年11月17日 12时14分13秒 星期二 
 * @version:1.0
 */
public interface WxActJiugonggeRegistrationDao extends GenericDao<WxActJiugonggeRegistration>{
	
	public Integer count(PageQuery<WxActJiugonggeRegistration> pageQuery);
	
	public List<WxActJiugonggeRegistration> queryPageList(PageQuery<WxActJiugonggeRegistration> pageQuery,Integer itemCount);
	public WxActJiugonggeRegistration queryRegistrationByOpenidAndActIdAndJwid(String openid,String actId,String jwid);
}

