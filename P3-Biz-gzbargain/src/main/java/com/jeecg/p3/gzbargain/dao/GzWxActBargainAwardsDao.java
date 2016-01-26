package com.jeecg.p3.gzbargain.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.gzbargain.entity.GzWxActBargainAwards;

/**
 * 描述：</b>WxActBargainAwardsDao<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分34秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainAwardsDao extends GenericDao<GzWxActBargainAwards>{
	
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
	
    public Integer count(PageQuery<GzWxActBargainAwards> pageQuery);
	
	public List<GzWxActBargainAwards> queryPageList(PageQuery<GzWxActBargainAwards> pageQuery,Integer itemCount);
	
	
}

