package com.jeecg.p3.jiugongge.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.jiugongge.dao.WxActJiugonggeAwardsDao;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRelationDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeAwardsService;

@Service("wxActJiugonggeAwardsService")
public class WxActJiugonggeAwardsServiceImpl implements WxActJiugonggeAwardsService {
	@Resource
	private WxActJiugonggeAwardsDao wxActJiugonggeAwardsDao;
	@Resource
	private WxActJiugonggeRelationDao wxActJiugonggeRelationDao;

	@Override
	public void doAdd(WxActJiugonggeAwards wxActJiugonggeAwards) {
		wxActJiugonggeAwardsDao.add(wxActJiugonggeAwards);
	}

	@Override
	public void doEdit(WxActJiugonggeAwards wxActJiugonggeAwards) {
		wxActJiugonggeAwardsDao.update(wxActJiugonggeAwards);
	}

	@Override
	public void doDelete(String id) {
		wxActJiugonggeAwardsDao.delete(id);
	}

	@Override
	public WxActJiugonggeAwards queryById(String id) {
		WxActJiugonggeAwards wxActJiugonggeAwards  = wxActJiugonggeAwardsDao.get(id);
		return wxActJiugonggeAwards;
	}

	@Override
	public PageList<WxActJiugonggeAwards> queryPageList(
		PageQuery<WxActJiugonggeAwards> pageQuery) {
		PageList<WxActJiugonggeAwards> result = new PageList<WxActJiugonggeAwards>();
		Integer itemCount = wxActJiugonggeAwardsDao.count(pageQuery);
		List<WxActJiugonggeAwards> list = wxActJiugonggeAwardsDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActJiugonggeAwards> queryAwards(String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggeAwardsDao.queryAwards(jwid);
	}

	@Override
	public Boolean validReat(int value, String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggeAwardsDao.validReat(value, jwid);
	}

	@Override
	public Boolean validReat(String id, int value, String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggeAwardsDao.validReat(id, value, jwid);
	}

	@Override
	public Boolean validUsed(String id) {
		// TODO Auto-generated method stub
		return wxActJiugonggeRelationDao.validUsed(id,null);
	}
	
}
