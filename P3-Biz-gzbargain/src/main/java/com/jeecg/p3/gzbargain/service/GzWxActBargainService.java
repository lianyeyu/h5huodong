package com.jeecg.p3.gzbargain.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.gzbargain.entity.GzWxActBargain;

/**
 * 描述：</b>WxActBargainService<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainService {
	
	/**
	 * 根据活动id查询活动信息
	 * @param actId
	 * @return
	 */
	public GzWxActBargain queryWxActBargain(String actId);

	/**
	 * 添加一条活动记录（后台功能以back开头）
	 * @param gzWxActBargain.actName gzWxActBargain.productName
	 * @return
	 */
	public void doAdd(GzWxActBargain gzWxActBargain);
	/**
	 * 更新一条活动记录（后台功能以back开头）
	 * @param 
	 * @return
	 */
	public void doEdit(GzWxActBargain gzWxActBargain);
	/**
	 * 根据ID删除一条活动记录
	 * @param 
	 * @return
	 */
	public void doDelete(String id);
	
	/**
	 * 根据活动id查询活动信息
	 * @param actId
	 * @return
	 */
	public GzWxActBargain queryById(String id);
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	public PageList<GzWxActBargain> queryPageList(PageQuery<GzWxActBargain> pageQuery);
}

