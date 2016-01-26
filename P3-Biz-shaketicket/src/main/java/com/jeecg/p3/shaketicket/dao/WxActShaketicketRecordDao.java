package com.jeecg.p3.shaketicket.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.shaketicket.entity.WxActShaketicketRecord;

/**
 * 描述：</b>WxActShaketicketRecordDao<br>
 * @author：pituo
 * @since：2015年12月22日 19时03分50秒 星期二 
 * @version:1.0
 */
public interface WxActShaketicketRecordDao extends GenericDao<WxActShaketicketRecord>{
	
	public Integer count(PageQuery<WxActShaketicketRecord> pageQuery);
	
	public List<WxActShaketicketRecord> queryPageList(PageQuery<WxActShaketicketRecord> pageQuery,Integer itemCount);
	public Map<String, Integer> getRecordCountByActIdAndOpenid(String actId,String openid,Date currDate);
	
	public Integer getMaxAwardsSeq(String actId);
	
	public List<WxActShaketicketRecord> queryMyAwardsRecordByOpenidAndActid(String openid,String actId);
	public List<WxActShaketicketRecord> queryBargainAwardsByActId(String actId);
	public List<WxActShaketicketRecord> queryBargainAwardsByActIdForWin(String actId);
}

