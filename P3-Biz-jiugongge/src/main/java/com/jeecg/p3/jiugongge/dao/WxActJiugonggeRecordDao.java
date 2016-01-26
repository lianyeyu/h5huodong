package com.jeecg.p3.jiugongge.dao;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRecord;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRegistration;

/**
 * 描述：</b>WxActJiugonggeRecordDao<br>
 * @author：junfeng.zhou
 * @since：2015年11月17日 12时14分14秒 星期二 
 * @version:1.0
 */
public interface WxActJiugonggeRecordDao extends GenericDao<WxActJiugonggeRecord>{
	
	public Integer count(PageQuery<WxActJiugonggeRecord> pageQuery);
	
	public List<WxActJiugonggeRecord> queryPageList(PageQuery<WxActJiugonggeRecord> pageQuery,Integer itemCount);
	public List<WxActJiugonggeRecord> queryPageListForJoin(PageQuery<WxActJiugonggeRecord> pageQuery,Integer itemCount);
	public List<WxActJiugonggeRecord> queryBargainRecordListByOpenidAndActidAndJwid(String openid,String actId,String jwid,Date currDate);
	public List<WxActJiugonggeRecord> queryMyAwardsByOpenidAndActidAndJwid(String openid,String actId,String jwid);
	public List<WxActJiugonggeRecord> queryBargainRecordListByActidAndJwid(String actId,String jwid);
	public List<WxActJiugonggeRecord> exportRecordListByActidAndJwid(String actId,String jwid);
	
	public Integer getMaxAwardsSeq(String actid);
}

