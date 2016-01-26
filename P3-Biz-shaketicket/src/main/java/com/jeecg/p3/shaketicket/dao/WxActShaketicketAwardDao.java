package com.jeecg.p3.shaketicket.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.shaketicket.entity.WxActShaketicketAward;

/**
 * 描述：</b>WxActShaketicketAwardDao<br>
 * @author：pituo
 * @since：2015年12月24日 11时08分30秒 星期四 
 * @version:1.0
 */
public interface WxActShaketicketAwardDao extends GenericDao<WxActShaketicketAward>{
	
	public Integer count(PageQuery<WxActShaketicketAward> pageQuery);
	
	public List<WxActShaketicketAward> queryPageList(PageQuery<WxActShaketicketAward> pageQuery,Integer itemCount);
	public List<WxActShaketicketAward> queryRemainAwardsByActId(String actId);
	public List<WxActShaketicketAward> queryAwards(String jwid);
}

