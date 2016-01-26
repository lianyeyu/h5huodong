package com.jeecg.p3.jiugongge.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.jiugongge.entity.WxActJiugongge;

/**
 * 描述：</b>WxActJiugonggeService<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分11秒 星期一 
 * @version:1.0
 */
public interface WxActJiugonggeService {
	
	
	public void doAdd(WxActJiugongge wxActJiugongge);
	
	public void doEdit(WxActJiugongge wxActJiugongge);
	
	public void doDelete(String id);
	
	public WxActJiugongge queryById(String id);
	
	public PageList<WxActJiugongge> queryPageList(PageQuery<WxActJiugongge> pageQuery);
	
	public List<WxActJiugongge> queryActs(String jwid);
}

