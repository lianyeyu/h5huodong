package com.jeecg.p3.shaketicket.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.shaketicket.entity.WxActShaketicketConfig;

/**
 * 描述：</b>WxActShaketicketConfigDao<br>
 * @author：pituo
 * @since：2015年12月24日 11时08分29秒 星期四 
 * @version:1.0
 */
public interface WxActShaketicketConfigDao extends GenericDao<WxActShaketicketConfig>{
	
	public Integer count(PageQuery<WxActShaketicketConfig> pageQuery);
	
	public List<WxActShaketicketConfig> queryPageList(PageQuery<WxActShaketicketConfig> pageQuery,Integer itemCount);
	public void updateRemainNum(String actid,String jwid,String awardid);
	
	public List<WxActShaketicketConfig> queryByActId(String actId);
	
	public void batchDeleteByActId( String actid) ;
	
	public void bactchDeleteOldAwards(List<String> ids,String actId) ;
}

