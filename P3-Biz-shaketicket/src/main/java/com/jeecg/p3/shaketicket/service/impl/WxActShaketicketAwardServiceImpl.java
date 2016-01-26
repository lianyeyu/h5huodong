package com.jeecg.p3.shaketicket.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.shaketicket.service.WxActShaketicketAwardService;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketAward;
import com.jeecg.p3.shaketicket.dao.WxActShaketicketAwardDao;

@Service("wxActShaketicketAwardService")
public class WxActShaketicketAwardServiceImpl implements WxActShaketicketAwardService {
	@Resource
	private WxActShaketicketAwardDao wxActShaketicketAwardDao;

	@Override
	public void doAdd(WxActShaketicketAward wxActShaketicketAward) {
		wxActShaketicketAwardDao.add(wxActShaketicketAward);
	}

	@Override
	public void doEdit(WxActShaketicketAward wxActShaketicketAward) {
		wxActShaketicketAwardDao.update(wxActShaketicketAward);
	}

	@Override
	public void doDelete(String id) {
		wxActShaketicketAwardDao.delete(id);
	}

	@Override
	public WxActShaketicketAward queryById(String id) {
		WxActShaketicketAward wxActShaketicketAward  = wxActShaketicketAwardDao.get(id);
		return wxActShaketicketAward;
	}

	@Override
	public PageList<WxActShaketicketAward> queryPageList(
		PageQuery<WxActShaketicketAward> pageQuery) {
		PageList<WxActShaketicketAward> result = new PageList<WxActShaketicketAward>();
		Integer itemCount = wxActShaketicketAwardDao.count(pageQuery);
		List<WxActShaketicketAward> list = wxActShaketicketAwardDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActShaketicketAward> queryRemainAwardsByActId(String actId) {
		// TODO Auto-generated method stub
		return wxActShaketicketAwardDao.queryRemainAwardsByActId(actId);
	}

	@Override
	public List<WxActShaketicketAward> queryAwards(String jwid) {
		// TODO Auto-generated method stub
		return wxActShaketicketAwardDao.queryAwards(jwid);
	}
	
}
