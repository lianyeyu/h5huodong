package com.jeecg.p3.commonftb.service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbAwards;
import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;

/**
 * 描述：</b>WxActCommonftbAwardsService<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分47秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbAwardsService {
	
	
	public void doAdd(WxActCommonftbAwards wxActCommonftbAwards);
	
	public void doEdit(WxActCommonftbAwards wxActCommonftbAwards);
	
	public void doDelete(String id);
	
	public WxActCommonftbAwards queryById(String id);
	
	public PageList<WxActCommonftbAwards> queryPageList(PageQuery<WxActCommonftbAwards> pageQuery);
	
	/**
	 * 根据活动id和openid查询获奖信息
	 * @param actId
	 * @param openid
	 * @return
	 */
	public List<WxActCommonftbAwards> queryBargainAwardsByActIdAndOpenid(String actId,String openid);
	
	/**
	 * 根据活动id获取奖品最大的序号
	 * @param actId
	 * @return
	 */
	public Integer getMaxAwardsSeq(String actId);
	
	public void creatAwards(WxActCommonftbAwards wxActCommonftbAwards,WxActCommonftbCoupon commonftbCoupon,WxActCommonftbRegistration commonftbRegistration,WxActCommonftb commonftb);
	public List<WxActCommonftbAwards> queryBargainAwardsByMainActIdOpenid(String mainActId,String openid);
	
	public InputStream  exportExcel(String actId)throws FileNotFoundException;
}

