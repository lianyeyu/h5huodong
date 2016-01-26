package com.jeecg.p3.shaketicket.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.shaketicket.entity.WxActShaketicketAward;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketRecord;

/**
 * 描述：</b>WxActShaketicketRecordService<br>
 * @author：pituo
 * @since：2015年12月22日 19时03分50秒 星期二 
 * @version:1.0
 */
public interface WxActShaketicketRecordService {
	
	
	public void doAdd(WxActShaketicketRecord wxActShaketicketRecord);
	
	public void doEdit(WxActShaketicketRecord wxActShaketicketRecord);
	
	public void doDelete(String id);
	
	public WxActShaketicketRecord queryById(String id);
	public Map<String, Integer> getRecordCountByActIdAndOpenid(String actId,String openid,Date currDate);
	
	public PageList<WxActShaketicketRecord> queryPageList(PageQuery<WxActShaketicketRecord> pageQuery);
	
	public WxActShaketicketAward creatRecordAndReturnAward(WxActShaketicketRecord shaketicketRecord,WeixinDto weixinDto);

	public List<WxActShaketicketRecord> queryMyAwardsRecordByOpenidAndActid(String openid,String actId);
	
	public InputStream  exportExcel(String actId)throws FileNotFoundException;
	public InputStream  exportExcelWin(String actId)throws FileNotFoundException;
}

