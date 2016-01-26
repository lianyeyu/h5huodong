package com.jeecg.p3.gzbargain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jeecg.p3.gzbargain.dao.GzWxActBargainRegistrationDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;
import com.jeecg.p3.gzbargain.service.GzWxActBargainRegistrationService;

@Service("gzWxActBargainRegistrationService")
public class GzWxActBargainRegistrationServiceImpl implements GzWxActBargainRegistrationService {
	@Resource
	private GzWxActBargainRegistrationDao gzWxActBargainRegistrationDao;
	
	
	@Override
	public GzWxActBargainRegistration queryRegistrationByOpenidAndActId(
			String openid, String actId) {
		return this.gzWxActBargainRegistrationDao.queryRegistrationByOpenidAndActId(openid, actId);
	}
	
	@Override
	public GzWxActBargainRegistration queryBargainRegistrationById(String id) {
		GzWxActBargainRegistration gzWxActBargainRegistration  = gzWxActBargainRegistrationDao.get(id);
		return gzWxActBargainRegistration;
	}

	
	
	
	
	

	@Override
	public void add(GzWxActBargainRegistration gzWxActBargainRegistration) {
		gzWxActBargainRegistrationDao.add(gzWxActBargainRegistration);
	}

	@Override
	public void update(GzWxActBargainRegistration gzWxActBargainRegistration) {
		gzWxActBargainRegistrationDao.update(gzWxActBargainRegistration);
	}

	@Override
	public void deleteByPriKey(String id) {
		gzWxActBargainRegistrationDao.delete(id);
	}

	

	
	
}
