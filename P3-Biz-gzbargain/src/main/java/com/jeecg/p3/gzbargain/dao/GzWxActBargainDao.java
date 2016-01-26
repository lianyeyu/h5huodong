package com.jeecg.p3.gzbargain.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.gzbargain.entity.GzWxActBargain;

/**
 * 描述：</b>WxActBargainDao<br>
 * @author：junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
public interface GzWxActBargainDao extends GenericDao<GzWxActBargain>{
	
	/**
	 * 奖品剩余数量更新
	 * @param id 
	 * @param cutNum 扣除的数量
	 */
	public void updateProductRemainNum(String id,Integer cutNum);

	public Integer count(PageQuery<GzWxActBargain> pageQuery);
	
	public List<GzWxActBargain> queryPageList(PageQuery<GzWxActBargain> pageQuery,Integer itemCount);
	
}

