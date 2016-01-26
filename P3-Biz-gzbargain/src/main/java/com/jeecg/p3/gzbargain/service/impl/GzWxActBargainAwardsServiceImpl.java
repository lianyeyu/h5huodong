package com.jeecg.p3.gzbargain.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.common.utils.Constants;
import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.gzbargain.dao.GzWxActBargainAwardsDao;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainDao;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainRegistrationDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainAwards;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;
import com.jeecg.p3.gzbargain.exception.GzbargainException;
import com.jeecg.p3.gzbargain.exception.GzbargainExceptionEnum;
import com.jeecg.p3.gzbargain.service.GzWxActBargainAwardsService;

@Service("gzWxActBargainAwardsService")
public class GzWxActBargainAwardsServiceImpl implements GzWxActBargainAwardsService {
	@Resource
	private GzWxActBargainAwardsDao gzWxActBargainAwardsDao;
	
	@Resource
	private GzWxActBargainDao gzWxActBargainDao;
	
	@Resource
	private GzWxActBargainRegistrationDao gzWxActBargainRegistrationDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void createAwards(GzWxActBargainAwards gzWxActBargainAwards) {
		gzWxActBargainAwards.setId(RandomUtils.generateID());
		gzWxActBargainAwards.setAwardsCode(RandomUtils.generateID());
		gzWxActBargainAwards.setCreateTime(new Date());
		gzWxActBargainAwardsDao.add(gzWxActBargainAwards);
		gzWxActBargainDao.updateProductRemainNum(gzWxActBargainAwards.getActId(), 1);
		GzWxActBargainRegistration gzWxActBargainRegistration = gzWxActBargainRegistrationDao.queryRegistrationByOpenidAndActId(gzWxActBargainAwards.getOpenid(), gzWxActBargainAwards.getActId());
		gzWxActBargainRegistration.setAwardsStatus(Constants.PRIZE_RECEIVED);
		gzWxActBargainRegistrationDao.update(gzWxActBargainRegistration);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void updateAwards(GzWxActBargainAwards gzWxActBargainAwards) {
		GzWxActBargainAwards bargainAwards  = gzWxActBargainAwardsDao.get(gzWxActBargainAwards.getId());
		if(bargainAwards==null){
			throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_PRIZE_INVALID);
		}
		if(!bargainAwards.getOpenid().equals(gzWxActBargainAwards.getOpenid())){
			throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_PRIZE_INVALID);
		}
		//活动结束不能再修改申请信息
//		WxActBargain gzWxActBargain = gzWxActBargainDao.get(bargainAwards.getActId());
//		Date currDate = new Date();
//		if(currDate.after(gzWxActBargain.getEndTime())){
//			throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_END,"活动已结束，不能更新信息");
//		}
		gzWxActBargainAwardsDao.update(gzWxActBargainAwards);
	}
	
	@Override
	public Integer getMaxAwardsSeq(String actId) {
		return gzWxActBargainAwardsDao.getMaxAwardsSeq(actId);
	}
	
	
	@Override
	public void update(GzWxActBargainAwards gzWxActBargainAwards) {
		gzWxActBargainAwardsDao.update(gzWxActBargainAwards);
	}

	@Override
	public void deleteByPriKey(String id) {
		gzWxActBargainAwardsDao.delete(id);
	}

	@Override
	public GzWxActBargainAwards queryByPriKey(String id) {
		GzWxActBargainAwards gzWxActBargainAwards  = gzWxActBargainAwardsDao.get(id);
		return gzWxActBargainAwards;
	}

	@Override
	public List<GzWxActBargainAwards> queryBargainAwardsByActIdAndOpenid(String actId,
			String openid) {
		return gzWxActBargainAwardsDao.queryBargainAwardsByActIdAndOpenid(actId, openid);
	}

	@Override
	public void doAdd(GzWxActBargainAwards wxActBargainAwards) {
		gzWxActBargainAwardsDao.add(wxActBargainAwards);
	}

	@Override
	public void doEdit(GzWxActBargainAwards wxActBargainAwards) {
		gzWxActBargainAwardsDao.update(wxActBargainAwards);
	}

	@Override
	public void doDelete(String id) {
		gzWxActBargainAwardsDao.delete(id);
	}

	@Override
	public GzWxActBargainAwards queryById(String id) {
		GzWxActBargainAwards wxActBargainAwards  = gzWxActBargainAwardsDao.get(id);
		return wxActBargainAwards;
	}

	@Override
	public PageList<GzWxActBargainAwards> queryPageList(
		PageQuery<GzWxActBargainAwards> pageQuery) {
		PageList<GzWxActBargainAwards> result = new PageList<GzWxActBargainAwards>();
		Integer itemCount = gzWxActBargainAwardsDao.count(pageQuery);
		List<GzWxActBargainAwards> list = gzWxActBargainAwardsDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}




	
}
