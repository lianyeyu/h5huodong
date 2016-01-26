package com.jeecg.p3.jiugongge.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;

/**
 * 描述：</b>WxActJiugonggeAwardsService<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
public interface WxActJiugonggeAwardsService {
	
	
	public void doAdd(WxActJiugonggeAwards wxActJiugonggeAwards);
	
	public void doEdit(WxActJiugonggeAwards wxActJiugonggeAwards);
	
	public void doDelete(String id);
	
	public WxActJiugonggeAwards queryById(String id);
	public Boolean validReat(int value,String jwid);
	public Boolean validReat(String id,int value,String jwid);
	
	public PageList<WxActJiugonggeAwards> queryPageList(PageQuery<WxActJiugonggeAwards> pageQuery);
	public List<WxActJiugonggeAwards> queryAwards(String jwid);
	
	public Boolean validUsed(String id);
}

