package com.jeecg.p3.commonftb.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;

/**
 * 描述：</b>WxActCommonsjCouponDao<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分47秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbCouponDao extends GenericDao<WxActCommonftbCoupon>{
	
	public Integer count(PageQuery<WxActCommonftbCoupon> pageQuery);
	
	public List<WxActCommonftbCoupon> queryPageList(PageQuery<WxActCommonftbCoupon> pageQuery,Integer itemCount);
	/**
	 * 通过主活动id和面额查询相关的卡券信息
	 * @param mainActId
	 * @return
	 */
	public List<WxActCommonftbCoupon> queryBargainCouponListByActIdAndCost(String actId,BigDecimal cost);
}

