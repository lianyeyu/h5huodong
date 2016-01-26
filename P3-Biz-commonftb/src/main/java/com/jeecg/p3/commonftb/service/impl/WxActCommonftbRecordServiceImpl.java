package com.jeecg.p3.commonftb.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.commonftb.dao.WxActCommonftbDao;
import com.jeecg.p3.commonftb.dao.WxActCommonftbRecordDao;
import com.jeecg.p3.commonftb.dao.WxActCommonftbRegistrationDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRecord;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;
import com.jeecg.p3.commonftb.exception.CommonftbException;
import com.jeecg.p3.commonftb.exception.CommonftbExceptionEnum;
import com.jeecg.p3.commonftb.service.WxActCommonftbRecordService;

@Service("wxActCommonftbRecordService")
public class WxActCommonftbRecordServiceImpl implements WxActCommonftbRecordService {
	@Resource
	private WxActCommonftbRecordDao wxActCommonftbRecordDao;
	@Resource
	private WxActCommonftbRegistrationDao wxActCommonftbRegistrationDao;
	
	@Autowired
	private WxActCommonftbDao wxActCommonftbDao;
	@Override
	public void doAdd(WxActCommonftbRecord wxActCommonftbRecord) {
		wxActCommonftbRecordDao.add(wxActCommonftbRecord);
	}

	@Override
	public void doEdit(WxActCommonftbRecord wxActCommonftbRecord) {
		wxActCommonftbRecordDao.update(wxActCommonftbRecord);
	}

	@Override
	public void doDelete(String id) {
		wxActCommonftbRecordDao.delete(id);
	}

	@Override
	public WxActCommonftbRecord queryById(String id) {
		WxActCommonftbRecord wxActCommonftbRecord  = wxActCommonftbRecordDao.get(id);
		return wxActCommonftbRecord;
	}

	@Override
	public PageList<WxActCommonftbRecord> queryPageList(
		PageQuery<WxActCommonftbRecord> pageQuery) {
		PageList<WxActCommonftbRecord> result = new PageList<WxActCommonftbRecord>();
		Integer itemCount = wxActCommonftbRecordDao.count(pageQuery);
		List<WxActCommonftbRecord> list = wxActCommonftbRecordDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationId(
			String registrationId) {
		// TODO Auto-generated method stub
		return wxActCommonftbRecordDao.queryBargainRecordListByRegistrationId(registrationId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void bargainMyself(
			WxActCommonftbRegistration wxActCommonftbRegistration,WxActCommonftb commonftb) {
		// TODO Auto-generated method stub
		BigDecimal cutPrice = getCutPrice(commonftb.getActRule());
		wxActCommonftbRegistration.setProductNewPrice(wxActCommonftbRegistration.getProductPrice().subtract(cutPrice));
		wxActCommonftbRegistrationDao.add(wxActCommonftbRegistration);
		WxActCommonftbRecord commonftbRecord = new WxActCommonftbRecord();
		commonftbRecord.setId(RandomUtils.generateID());
		commonftbRecord.setCutPrice(cutPrice);
		commonftbRecord.setCurrPrice(wxActCommonftbRegistration.getProductNewPrice());
		commonftbRecord.setCreateTime(new Date());
		commonftbRecord.setOpenid(wxActCommonftbRegistration.getOpenid());
		commonftbRecord.setNickname(wxActCommonftbRegistration.getNickname());
		commonftbRecord.setRegistrationId(wxActCommonftbRegistration.getId());
		commonftbRecord.setJwid(wxActCommonftbRegistration.getJwid());
		wxActCommonftbRecordDao.add(commonftbRecord);
	}
	/**
	 * 获取随机价格
	 * @param actRule
	 * @return
	 */
	private BigDecimal getCutPrice(String actRule){
		try {
			String [] rules = actRule.split(",");
			if(rules.length==2){
				float min=Float.valueOf(rules[0]);
				float max=Float.valueOf(rules[1]);
			    BigDecimal db = new BigDecimal(Math.random() * (max - min) + min);  
			    return db.setScale(2, BigDecimal.ROUND_HALF_UP);
			}else if(rules.length==3){
				float min=Float.valueOf(rules[0]);
				float max=Float.valueOf(rules[1]);
				int scale=Integer.valueOf(rules[2]);
			    BigDecimal db = new BigDecimal(Math.random() * (max - min) + min);  
			    return db.setScale(scale, BigDecimal.ROUND_HALF_UP);
			}else{
				throw new CommonftbException(CommonftbExceptionEnum.ACT_BARGAIN_RULE_ERROR);
			}
		} catch (Exception e) {
			throw new CommonftbException(CommonftbExceptionEnum.ACT_BARGAIN_RULE_ERROR);
		}
	}

	@Override
	public List<WxActCommonftbRecord> queryBargainRecordListByRegistrationIdAndOpenid(
			String registrationId, String openid) {
		// TODO Auto-generated method stub
		return wxActCommonftbRecordDao.queryBargainRecordListByRegistrationIdAndOpenid(registrationId, openid);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String bargain(WxActCommonftbRecord commonftbRecord,WxActCommonftbRegistration bargainRegistration) {
		// TODO Auto-generated method stub
		String cutCount = "first";
		WxActCommonftb commonftb = wxActCommonftbDao.get(bargainRegistration.getActId());
		if(bargainRegistration.getProductNewPrice().compareTo(commonftb.getCutMinPrice())<=0){
			cutCount = "last";
			return cutCount;
		}
		//获取随机砍掉价格
		BigDecimal cutPrice = getCutPrice(commonftb.getActRule());
		Date currDate = new Date();
		if(currDate.after(commonftb.getEndTime())){
			throw new CommonftbException(CommonftbExceptionEnum.ACT_BARGAIN_END,"活动已结束");
		}
		//更新报名砍价信息
		BigDecimal lastprice=bargainRegistration.getProductNewPrice();//上一次的最新价格
		wxActCommonftbRegistrationDao.updateBargainPrice(commonftbRecord.getRegistrationId(), cutPrice);
		bargainRegistration = wxActCommonftbRegistrationDao.get(commonftbRecord.getRegistrationId());
		
		//判断是否为最后一刀
		if(bargainRegistration.getProductNewPrice().compareTo(commonftb.getCutMinPrice())<0){
			bargainRegistration.setProductNewPrice(commonftb.getCutMinPrice());
			wxActCommonftbRegistrationDao.update(bargainRegistration);
			cutPrice=lastprice.subtract(commonftb.getCutMinPrice());
			cutCount = "last";
		}else if(bargainRegistration.getProductNewPrice().compareTo(commonftb.getCutMinPrice())==0){
			cutCount = "last";
		}		
		//插入砍价记录
		commonftbRecord.setId(RandomUtils.generateID());
		commonftbRecord.setCutPrice(cutPrice);
		commonftbRecord.setCurrPrice(lastprice.subtract(cutPrice));
		commonftbRecord.setCreateTime(new Date());
		wxActCommonftbRecordDao.add(commonftbRecord);
		return cutCount;

	}
}
