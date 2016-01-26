package com.jeecg.p3.commonftb.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.commonftb.dao.WxActCommonftbCouponDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;
import com.jeecg.p3.commonftb.exception.CommonftbException;
import com.jeecg.p3.commonftb.exception.CommonftbExceptionEnum;
import com.jeecg.p3.commonftb.service.WxActCommonftbCouponService;
import com.jeecg.p3.commonftb.util.ExcelUtil;

@Service("wxActCommonftbCouponService")
public class WxActCommonftbCouponServiceImpl implements
		WxActCommonftbCouponService {
	@Resource
	private WxActCommonftbCouponDao wxActCommonftbCouponDao;

	@Override
	public void doAdd(WxActCommonftbCoupon wxActCommonftbCoupon) {
		wxActCommonftbCouponDao.add(wxActCommonftbCoupon);
	}

	@Override
	public void doEdit(WxActCommonftbCoupon wxActCommonftbCoupon) {
		wxActCommonftbCouponDao.update(wxActCommonftbCoupon);
	}

	@Override
	public void doDelete(String id) {
		wxActCommonftbCouponDao.delete(id);
	}

	@Override
	public WxActCommonftbCoupon queryById(String id) {
		WxActCommonftbCoupon wxActCommonftbCoupon = wxActCommonftbCouponDao
				.get(id);
		return wxActCommonftbCoupon;
	}

	@Override
	public PageList<WxActCommonftbCoupon> queryPageList(
			PageQuery<WxActCommonftbCoupon> pageQuery) {
		PageList<WxActCommonftbCoupon> result = new PageList<WxActCommonftbCoupon>();
		Integer itemCount = wxActCommonftbCouponDao.count(pageQuery);
		List<WxActCommonftbCoupon> list = wxActCommonftbCouponDao.queryPageList(
				pageQuery, itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(),
				itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	/**
	 * 兑奖金额向上取整，根据卡券等级随机取得卡券ID
	 * 
	 * @param mainActId
	 * @param couponLevel
	 * @param cutPrice
	 * @return
	 */
	@Override
	public WxActCommonftbCoupon routeCardId(String mainActId,WxActCommonftb commonftb
			, BigDecimal cutPrice) {
		// TODO Auto-generated method stub
		String couponLevel=commonftb.getCouponLevel();
		String roundType=commonftb.getRoundType();
		String actId=commonftb.getId();
		WxActCommonftbCoupon commonftbCoupon = null;
		if (!couponLevel.isEmpty()) {
			String[] couponLevels = couponLevel.split(",");
			BigDecimal levelPrice = new BigDecimal(0);
			if ("0".equals(roundType)) {// 向下取整
				// 卡券档位必须按照从大到小顺序排列
				for (int i = couponLevels.length-1; i >= 0; i--) {
					levelPrice = new BigDecimal(Float.valueOf(couponLevels[i]));
					if (cutPrice.compareTo(levelPrice) >= 0) {
						// 根据主活动ID和面额获取现金券列表
						List<WxActCommonftbCoupon> commonftbCouponsList = wxActCommonftbCouponDao
								.queryBargainCouponListByActIdAndCost(actId, levelPrice);
						if (commonftbCouponsList.size() > 0) {
							// 从列表中随机取得卡券ID
							Random random = new Random();
							int randomIndex = random
									.nextInt(commonftbCouponsList.size() - 1)
									% (commonftbCouponsList.size());
							commonftbCoupon = commonftbCouponsList
									.get(randomIndex);
						}
						break;
					}
				}

			} else {// 向上取整
					// 卡券档位必须按照从小到大顺序排列
				for (int i = 0; i < couponLevels.length; i++) {
					levelPrice = new BigDecimal(Float.valueOf(couponLevels[i]));
					if ("0".equals(roundType)) {// 向下取整
					} else {// 向上取整
						if (cutPrice.compareTo(levelPrice) <= 0) {
							// 根据主活动ID和面额获取现金券列表
							List<WxActCommonftbCoupon> commonftbCouponsList = wxActCommonftbCouponDao
									.queryBargainCouponListByActIdAndCost(actId, levelPrice);
							if (commonftbCouponsList.size() > 0) {
								// 从列表中随机取得卡券ID
								Random random = new Random();
								int randomIndex = random
										.nextInt(commonftbCouponsList
												.size() - 1)
										% (commonftbCouponsList.size());
								commonftbCoupon = commonftbCouponsList
										.get(randomIndex);
							}
							break;
						}
					}
				}
			}
		}
		if (commonftbCoupon == null) {
			throw new CommonftbException(
					CommonftbExceptionEnum.ACT_BARGAIN_CARD_NO_FIND, "没有可领取的现金券");
		}
		return commonftbCoupon;
	}
	  /**  
	    * 导入excel 
	    *   
	    * @param fileName 文件名称 
	    * @param excelFile 文件 
	    * @return  
	    */        
	public void importExcel(String fileName,File excelFile,String actId){  
	    List<WxActCommonftbCoupon> listUsers = (List<WxActCommonftbCoupon>)ExcelUtil.importExcel(excelFile, WxActCommonftbCoupon.class);  
	    for(WxActCommonftbCoupon coupon : listUsers){ 
	    	coupon.setActId(actId);
	    	coupon.setStatus("0");
	        wxActCommonftbCouponDao.add(coupon);  
	    }  
	}  
}
