package com.jeecg.p3.jiugongge.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRegistration;

/**
 * 描述：</b>WxActJiugonggeRegistrationService<br>
 * @author：junfeng.zhou
 * @since：2015年11月17日 12时18分43秒 星期二 
 * @version:1.0
 */
public interface WxActJiugonggeRegistrationService {
	
	
	public void doAdd(WxActJiugonggeRegistration wxActJiugonggeRegistration);
	
	public void doEdit(WxActJiugonggeRegistration wxActJiugonggeRegistration);
	
	public void doDelete(String id);
	
	public WxActJiugonggeRegistration queryById(String id);
	
	public PageList<WxActJiugonggeRegistration> queryPageList(PageQuery<WxActJiugonggeRegistration> pageQuery);
	
	public WxActJiugonggeRegistration queryRegistrationByOpenidAndActIdAndJwid(String openid,String actId,String jwid);
	public void add(WxActJiugonggeRegistration wxActJiugonggeRegistration);
}

