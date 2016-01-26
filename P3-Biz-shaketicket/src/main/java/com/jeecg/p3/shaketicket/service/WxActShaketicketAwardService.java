package com.jeecg.p3.shaketicket.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketAward;

/**
 * 描述：</b>WxActShaketicketAwardService<br>
 * @author：pituo
 * @since：2015年12月24日 11时08分30秒 星期四 
 * @version:1.0
 */
public interface WxActShaketicketAwardService {
	
	
	public void doAdd(WxActShaketicketAward wxActShaketicketAward);
	
	public void doEdit(WxActShaketicketAward wxActShaketicketAward);
	
	public void doDelete(String id);
	
	public WxActShaketicketAward queryById(String id);
	
	public PageList<WxActShaketicketAward> queryPageList(PageQuery<WxActShaketicketAward> pageQuery);
	
	public List<WxActShaketicketAward> queryRemainAwardsByActId(String actId);
	
	public List<WxActShaketicketAward> queryAwards(String jwid);
}

