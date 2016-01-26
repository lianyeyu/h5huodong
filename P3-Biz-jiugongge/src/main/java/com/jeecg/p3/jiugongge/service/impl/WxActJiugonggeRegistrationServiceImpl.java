package com.jeecg.p3.jiugongge.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRegistrationDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRegistration;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRegistrationService;

@Service("wxActJiugonggeRegistrationService")
public class WxActJiugonggeRegistrationServiceImpl implements WxActJiugonggeRegistrationService {
	@Resource
	private WxActJiugonggeRegistrationDao wxActJiugonggeRegistrationDao;

	@Override
	public void doAdd(WxActJiugonggeRegistration wxActJiugonggeRegistration) {
		wxActJiugonggeRegistrationDao.add(wxActJiugonggeRegistration);
	}

	@Override
	public void doEdit(WxActJiugonggeRegistration wxActJiugonggeRegistration) {
		wxActJiugonggeRegistrationDao.update(wxActJiugonggeRegistration);
	}

	@Override
	public void doDelete(String id) {
		wxActJiugonggeRegistrationDao.delete(id);
	}

	@Override
	public WxActJiugonggeRegistration queryById(String id) {
		WxActJiugonggeRegistration wxActJiugonggeRegistration  = wxActJiugonggeRegistrationDao.get(id);
		return wxActJiugonggeRegistration;
	}

	@Override
	public PageList<WxActJiugonggeRegistration> queryPageList(
		PageQuery<WxActJiugonggeRegistration> pageQuery) {
		PageList<WxActJiugonggeRegistration> result = new PageList<WxActJiugonggeRegistration>();
		Integer itemCount = wxActJiugonggeRegistrationDao.count(pageQuery);
		List<WxActJiugonggeRegistration> list = wxActJiugonggeRegistrationDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActJiugonggeRegistration queryRegistrationByOpenidAndActIdAndJwid(
			String openid, String actId, String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggeRegistrationDao.queryRegistrationByOpenidAndActIdAndJwid(openid, actId, jwid);
	}

	@Override
	public void add(WxActJiugonggeRegistration wxActJiugonggeRegistration) {
		// TODO Auto-generated method stub
		wxActJiugonggeRegistrationDao.add(wxActJiugonggeRegistration);
	}

	
}
