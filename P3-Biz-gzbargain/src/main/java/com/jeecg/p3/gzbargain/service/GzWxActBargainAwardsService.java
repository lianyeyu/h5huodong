package com.jeecg.p3.gzbargain.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.gzbargain.entity.GzWxActBargainAwards;

/**
 * 描述：</b>WxActBargainAwardsService<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分34秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainAwardsService {
	
	
	/**
	 * 创建奖品
	 * @param gzWxActBargainAwards
	 */
	public void createAwards(GzWxActBargainAwards gzWxActBargainAwards);
	
	/**
	 * 完善兑奖信息
	 * @param gzWxActBargainAwards
	 */
	public void updateAwards(GzWxActBargainAwards gzWxActBargainAwards);
	
	/**
	 * 根据活动id获取奖品最大的序号
	 * @param actId
	 * @return
	 */
	public Integer getMaxAwardsSeq(String actId);
	
	/**
	 * 根据活动id和openid查询获奖信息
	 * @param actId
	 * @param openid
	 * @return
	 */
	public List<GzWxActBargainAwards> queryBargainAwardsByActIdAndOpenid(String actId,String openid);
	
	
	/**
	 * 更新获奖信息
	 * @param gzWxActBargainAwards
	 */
	public void update(GzWxActBargainAwards gzWxActBargainAwards);
	
	public void deleteByPriKey(String id);
	
	public GzWxActBargainAwards queryByPriKey(String id);
	
	public void doAdd(GzWxActBargainAwards wxActBargainAwards);
	
	public void doEdit(GzWxActBargainAwards wxActBargainAwards);
	
	public void doDelete(String id);
	
	public GzWxActBargainAwards queryById(String id);
	
	public PageList<GzWxActBargainAwards> queryPageList(PageQuery<GzWxActBargainAwards> pageQuery);
	
}

