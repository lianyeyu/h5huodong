package com.jeecg.p3.commonftb.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonftb.entity.WxActCommonftbRecord;

/**
 * 描述：</b>WxActCommonftbRecordDao<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbRecordDao extends GenericDao<WxActCommonftbRecord>{
	
	public Integer count(PageQuery<WxActCommonftbRecord> pageQuery);
	
	public List<WxActCommonftbRecord> queryPageList(PageQuery<WxActCommonftbRecord> pageQuery,Integer itemCount);
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationId(String registrationId);
	
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationIdAndOpenid(String registrationId,String openid);

}

