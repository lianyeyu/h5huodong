package com.jeecg.p3.commonftb.dao;

import java.math.BigDecimal;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;

/**
 * 描述：</b>WxActCommonftbRegistrationDao<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbRegistrationDao extends GenericDao<WxActCommonftbRegistration>{
	
	public Integer count(PageQuery<WxActCommonftbRegistration> pageQuery);
	
	public List<WxActCommonftbRegistration> queryPageList(PageQuery<WxActCommonftbRegistration> pageQuery,Integer itemCount);
	public WxActCommonftbRegistration queryRegistrationByOpenidAndActId(String openid,String actId);
	
	public void updateBargainPrice(String id,BigDecimal cutPrice);
}

