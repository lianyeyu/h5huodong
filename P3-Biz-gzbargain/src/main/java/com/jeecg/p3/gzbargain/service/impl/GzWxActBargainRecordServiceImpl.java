package com.jeecg.p3.gzbargain.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.gzbargain.dao.GzWxActBargainRecordDao;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainRegistrationDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargain;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainAwards;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRecord;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;
import com.jeecg.p3.gzbargain.exception.GzbargainException;
import com.jeecg.p3.gzbargain.exception.GzbargainExceptionEnum;
import com.jeecg.p3.gzbargain.service.GzWxActBargainAwardsService;
import com.jeecg.p3.gzbargain.service.GzWxActBargainRecordService;
import com.jeecg.p3.gzbargain.service.GzWxActBargainService;

@Service("gzWxActBargainRecordService")
public class GzWxActBargainRecordServiceImpl implements GzWxActBargainRecordService {
	public final static Logger LOG = LoggerFactory.getLogger(GzWxActBargainRecordServiceImpl.class);
	@Resource
	private GzWxActBargainRecordDao gzWxActBargainRecordDao;
	
	@Resource
	private GzWxActBargainRegistrationDao gzWxActBargainRegistrationDao;
	
	@Autowired
	private GzWxActBargainService gzWxActBargainService;
	
	@Autowired
	private GzWxActBargainAwardsService gzWxActBargainAwardsService;
	
	
	@Override
	public List<GzWxActBargainRecord> queryBargainRecordListByRegistrationId(
			String registrationId) {
		return gzWxActBargainRecordDao.queryBargainRecordListByRegistrationId(registrationId);
	}
	
	@Override
	public List<GzWxActBargainRecord> queryBargainRecordListByRegistrationIdAndOpenid(
			String registrationId, String openid) {
		return gzWxActBargainRecordDao.queryBargainRecordListByRegistrationIdAndOpenid(registrationId, openid);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String bargain(GzWxActBargainRegistration bargainRegistration,GzWxActBargainRecord gzWxActBargainRecord) {
		String cutStatus = "first";
		GzWxActBargain gzWxActBargain = gzWxActBargainService.queryWxActBargain(bargainRegistration.getActId());
		if(bargainRegistration.getProductNewPrice().compareTo(gzWxActBargain.getCutMinPrice())<=0){
			cutStatus = "cutMin";
			return cutStatus;
		}
		
		BigDecimal cutPrice = getCutPrice(gzWxActBargain.getActRule());
		Date currDate = new Date();
		if(currDate.after(gzWxActBargain.getEndTime())){
			throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_END,"活动已结束");
		}
		BigDecimal lastprice=bargainRegistration.getProductNewPrice();//上一次的最新价格
		//更新报名砍价信息
		gzWxActBargainRegistrationDao.updateBargainPrice(gzWxActBargainRecord.getRegistrationId(), cutPrice);
		//获取报名信息
		bargainRegistration = gzWxActBargainRegistrationDao.get(gzWxActBargainRecord.getRegistrationId());
		if(bargainRegistration.getProductNewPrice().compareTo(gzWxActBargain.getCutMinPrice())<0){
			bargainRegistration.setProductNewPrice(gzWxActBargain.getCutMinPrice());
			gzWxActBargainRegistrationDao.update(bargainRegistration);
			cutPrice=lastprice.subtract(gzWxActBargain.getCutMinPrice());
			cutStatus = "last";
		}else if(bargainRegistration.getProductNewPrice().compareTo(gzWxActBargain.getCutMinPrice())==0){
			cutStatus = "last";
		}
		LOG.info("update gzWxActBargainRecord after:{}", bargainRegistration);
		
		//插入砍价记录
		gzWxActBargainRecord.setId(RandomUtils.generateID());
		gzWxActBargainRecord.setCutPrice(cutPrice);
		gzWxActBargainRecord.setCurrPrice(lastprice.subtract(cutPrice));
		gzWxActBargainRecord.setCreateTime(new Date());
		gzWxActBargainRecord.setJwid(bargainRegistration.getJwid());
		LOG.info("insert gzWxActBargainRecord:{}", gzWxActBargainRecord);
		gzWxActBargainRecordDao.add(gzWxActBargainRecord);
		return cutStatus;
	}
	
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
			    BigDecimal db = new BigDecimal(Math.random() * (max - min) + min);  
			    return db.setScale(2, BigDecimal.ROUND_HALF_UP);
			}else{
				throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_RULE_ERROR);
			}
		} catch (Exception e) {
			throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_RULE_ERROR);
		}
	}

	
}
