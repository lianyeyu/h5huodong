package com.jeecg.p3.jiugongge.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggePrizes;

/**
 * 描述：</b>WxActJiugonggePrizesService<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
public interface WxActJiugonggePrizesService {
	
	
	public void doAdd(WxActJiugonggePrizes wxActJiugonggePrizes);
	
	public void doEdit(WxActJiugonggePrizes wxActJiugonggePrizes);
	
	public void doDelete(String id);
	
	public WxActJiugonggePrizes queryById(String id);
	public List<WxActJiugonggePrizes> queryByActId(String actid);
	public List<WxActJiugonggePrizes> queryRemainAwardsByActId(String actid);
	public List<WxActJiugonggePrizes> queryByAwardIdAndActId(String awardid,String actId);
	public PageList<WxActJiugonggePrizes> queryPageList(PageQuery<WxActJiugonggePrizes> pageQuery);
	
	public List<WxActJiugonggePrizes> queryPrizes(String jwid);
	public Boolean validUsed(String id);
}

