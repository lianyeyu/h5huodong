package com.jeecg.p3.jiugongge.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.jiugongge.dao.WxActJiugonggePrizesDao;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRelationDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggePrizes;
import com.jeecg.p3.jiugongge.service.WxActJiugonggePrizesService;

@Service("wxActJiugonggePrizesService")
public class WxActJiugonggePrizesServiceImpl implements WxActJiugonggePrizesService {
	@Resource
	private WxActJiugonggePrizesDao wxActJiugonggePrizesDao;
	@Resource
	private WxActJiugonggeRelationDao wxActJiugonggeRelationDao;

	@Override
	public void doAdd(WxActJiugonggePrizes wxActJiugonggePrizes) {
		wxActJiugonggePrizesDao.add(wxActJiugonggePrizes);
	}

	@Override
	public void doEdit(WxActJiugonggePrizes wxActJiugonggePrizes) {
		wxActJiugonggePrizesDao.update(wxActJiugonggePrizes);
	}

	@Override
	public void doDelete(String id) {
		wxActJiugonggePrizesDao.delete(id);
	}

	@Override
	public WxActJiugonggePrizes queryById(String id) {
		WxActJiugonggePrizes wxActJiugonggePrizes  = wxActJiugonggePrizesDao.get(id);
		return wxActJiugonggePrizes;
	}

	@Override
	public PageList<WxActJiugonggePrizes> queryPageList(
		PageQuery<WxActJiugonggePrizes> pageQuery) {
		PageList<WxActJiugonggePrizes> result = new PageList<WxActJiugonggePrizes>();
		Integer itemCount = wxActJiugonggePrizesDao.count(pageQuery);
		List<WxActJiugonggePrizes> list = wxActJiugonggePrizesDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActJiugonggePrizes> queryByActId(String actid) {
		// TODO Auto-generated method stub
		return wxActJiugonggePrizesDao.queryByActId(actid);
	}
	@Override
	public List<WxActJiugonggePrizes> queryRemainAwardsByActId(String actid) {
		// TODO Auto-generated method stub
		return wxActJiugonggePrizesDao.queryRemainAwardsByActId(actid);
	}

	@Override
	public List<WxActJiugonggePrizes> queryByAwardIdAndActId(String awardid,String actId) {
		// TODO Auto-generated method stub
		return wxActJiugonggePrizesDao.queryByAwardIdAndActId(awardid,actId);
	}

	@Override
	public List<WxActJiugonggePrizes> queryPrizes(String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggePrizesDao.queryPrizes(jwid);
	}

	@Override
	public Boolean validUsed(String id) {
		// TODO Auto-generated method stub
		return wxActJiugonggeRelationDao.validUsed(null,id);
	}
	
}
