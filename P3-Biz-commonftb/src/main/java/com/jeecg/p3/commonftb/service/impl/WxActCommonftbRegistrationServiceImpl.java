package com.jeecg.p3.commonftb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.commonftb.dao.WxActCommonftbRegistrationDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;
import com.jeecg.p3.commonftb.service.WxActCommonftbRegistrationService;

@Service("wxActCommonftbRegistrationService")
public class WxActCommonftbRegistrationServiceImpl implements WxActCommonftbRegistrationService {
	@Resource
	private WxActCommonftbRegistrationDao wxActCommonftbRegistrationDao;

	@Override
	public void doAdd(WxActCommonftbRegistration wxActCommonftbRegistration) {
		wxActCommonftbRegistrationDao.add(wxActCommonftbRegistration);
	}

	@Override
	public void doEdit(WxActCommonftbRegistration wxActCommonftbRegistration) {
		wxActCommonftbRegistrationDao.update(wxActCommonftbRegistration);
	}

	@Override
	public void doDelete(String id) {
		wxActCommonftbRegistrationDao.delete(id);
	}

	@Override
	public WxActCommonftbRegistration queryById(String id) {
		WxActCommonftbRegistration wxActCommonftbRegistration  = wxActCommonftbRegistrationDao.get(id);
		return wxActCommonftbRegistration;
	}

	@Override
	public PageList<WxActCommonftbRegistration> queryPageList(
		PageQuery<WxActCommonftbRegistration> pageQuery) {
		PageList<WxActCommonftbRegistration> result = new PageList<WxActCommonftbRegistration>();
		Integer itemCount = wxActCommonftbRegistrationDao.count(pageQuery);
		List<WxActCommonftbRegistration> list = wxActCommonftbRegistrationDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public WxActCommonftbRegistration queryRegistrationByOpenidAndActId(
			String openid, String actId) {
		// TODO Auto-generated method stub
		return this.wxActCommonftbRegistrationDao.queryRegistrationByOpenidAndActId(openid, actId);
	}
	
}
