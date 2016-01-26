package com.jeecg.p3.commonftb.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRecord;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;

/**
 * 描述：</b>WxActCommonftbRecordService<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbRecordService {
	
	
	public void doAdd(WxActCommonftbRecord wxActCommonftbRecord);
	
	public void doEdit(WxActCommonftbRecord wxActCommonftbRecord);
	
	public void doDelete(String id);
	
	public WxActCommonftbRecord queryById(String id);
	
	public PageList<WxActCommonftbRecord> queryPageList(PageQuery<WxActCommonftbRecord> pageQuery);
	
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationId(String registrationId);
	
	/**
	 * 自砍一刀
	 */
	public void bargainMyself(WxActCommonftbRegistration wxActCommonftbRegistration,WxActCommonftb commonftb);
	
	/**
	 * 根据用户活动报名id和openid查询砍价记录
	 * @param registrationId
	 * @return
	 */
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationIdAndOpenid(String registrationId,String openid);
	/**
	 * 砍价操作
	 * @param wxActJsftbbargainRecord
	 */
	public String bargain(WxActCommonftbRecord wxActCommonftbRecord,WxActCommonftbRegistration wxActCommonftbRegistration);
}

