package com.jeecg.p3.commonftb.service;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;

/**
 * 描述：</b>WxActCommonftbCouponService<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分47秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbCouponService {
	
	
	public void doAdd(WxActCommonftbCoupon wxActCommonftbCoupon);
	
	public void doEdit(WxActCommonftbCoupon wxActCommonftbCoupon);
	
	public void doDelete(String id);
	
	public WxActCommonftbCoupon queryById(String id);
	
	public PageList<WxActCommonftbCoupon> queryPageList(PageQuery<WxActCommonftbCoupon> pageQuery);
	
	/**
	 * 兑奖金额向上取整，根据卡券等级随机取得卡券ID
	 * @param mainActId
	 * @param couponLevel
	 * @param cutPrice
	 * @return
	 */
	public WxActCommonftbCoupon routeCardId(String mainActId,WxActCommonftb commonftb,BigDecimal cutPrice);
	
	  /**  
	    * 导入excel 
	    *   
	    * @param fileName 文件名称 
	    * @param excelFile 文件 
	    * @return  
	    */        
	public void importExcel(String fileName,File excelFile,String actId);
}

