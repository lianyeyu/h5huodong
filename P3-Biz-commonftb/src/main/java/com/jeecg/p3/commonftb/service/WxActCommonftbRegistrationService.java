package com.jeecg.p3.commonftb.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;

/**
 * 描述：</b>WxActCommonftbRegistrationService<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbRegistrationService {
	
	
	public void doAdd(WxActCommonftbRegistration wxActCommonftbRegistration);
	
	public void doEdit(WxActCommonftbRegistration wxActCommonftbRegistration);
	
	public void doDelete(String id);
	
	public WxActCommonftbRegistration queryById(String id);
	
	public PageList<WxActCommonftbRegistration> queryPageList(PageQuery<WxActCommonftbRegistration> pageQuery);
		
	public WxActCommonftbRegistration queryRegistrationByOpenidAndActId(String openid,String actId);
}

