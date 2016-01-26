package com.jeecg.p3.shaketicket.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.shaketicket.service.WxActShaketicketConfigService;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketConfig;
import com.jeecg.p3.shaketicket.dao.WxActShaketicketConfigDao;

@Service("wxActShaketicketConfigService")
public class WxActShaketicketConfigServiceImpl implements WxActShaketicketConfigService {
	@Resource
	private WxActShaketicketConfigDao wxActShaketicketConfigDao;

	@Override
	public void doAdd(WxActShaketicketConfig wxActShaketicketConfig) {
		wxActShaketicketConfigDao.add(wxActShaketicketConfig);
	}

	@Override
	public void doEdit(WxActShaketicketConfig wxActShaketicketConfig) {
		wxActShaketicketConfigDao.update(wxActShaketicketConfig);
	}

	@Override
	public void doDelete(String id) {
		wxActShaketicketConfigDao.delete(id);
	}

	@Override
	public WxActShaketicketConfig queryById(String id) {
		WxActShaketicketConfig wxActShaketicketConfig  = wxActShaketicketConfigDao.get(id);
		return wxActShaketicketConfig;
	}

	@Override
	public PageList<WxActShaketicketConfig> queryPageList(
		PageQuery<WxActShaketicketConfig> pageQuery) {
		PageList<WxActShaketicketConfig> result = new PageList<WxActShaketicketConfig>();
		Integer itemCount = wxActShaketicketConfigDao.count(pageQuery);
		List<WxActShaketicketConfig> list = wxActShaketicketConfigDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActShaketicketConfig> queryByActId(String actId) {
		// TODO Auto-generated method stub
		return wxActShaketicketConfigDao.queryByActId(actId);
	}
	
}
