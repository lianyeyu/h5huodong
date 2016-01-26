package com.jeecg.p3.shaketicket.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketHome;

/**
 * 描述：</b>WxActShaketicketHomeService<br>
 * @author：pituo
 * @since：2015年12月22日 19时03分50秒 星期二 
 * @version:1.0
 */
public interface WxActShaketicketHomeService {
	
	
	public void doAdd(WxActShaketicketHome wxActShaketicketHome);
	
	public void doEdit(WxActShaketicketHome wxActShaketicketHome);
	
	public void doDelete(String id);
	
	public WxActShaketicketHome queryById(String id);
	
	public PageList<WxActShaketicketHome> queryPageList(PageQuery<WxActShaketicketHome> pageQuery);
}

