package com.jeecg.p3.shaketicket.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketConfig;

/**
 * 描述：</b>WxActShaketicketConfigService<br>
 * @author：pituo
 * @since：2015年12月24日 11时08分29秒 星期四 
 * @version:1.0
 */
public interface WxActShaketicketConfigService {
	
	
	public void doAdd(WxActShaketicketConfig wxActShaketicketConfig);
	
	public void doEdit(WxActShaketicketConfig wxActShaketicketConfig);
	
	public void doDelete(String id);
	
	public WxActShaketicketConfig queryById(String id);
	
	public PageList<WxActShaketicketConfig> queryPageList(PageQuery<WxActShaketicketConfig> pageQuery);
	
	public List<WxActShaketicketConfig> queryByActId(String actId);
}

