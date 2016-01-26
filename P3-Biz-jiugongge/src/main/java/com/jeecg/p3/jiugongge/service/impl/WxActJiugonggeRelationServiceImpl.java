package com.jeecg.p3.jiugongge.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRelationService;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRelation;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRelationDao;

@Service("wxActJiugonggePrizesRelationService")
public class WxActJiugonggeRelationServiceImpl implements WxActJiugonggeRelationService {
	@Resource
	private WxActJiugonggeRelationDao wxActJiugonggePrizesRelationDao;

	@Override
	public void doAdd(WxActJiugonggeRelation wxActJiugonggePrizesRelation) {
		wxActJiugonggePrizesRelationDao.add(wxActJiugonggePrizesRelation);
	}

	@Override
	public void doEdit(WxActJiugonggeRelation wxActJiugonggePrizesRelation) {
		wxActJiugonggePrizesRelationDao.update(wxActJiugonggePrizesRelation);
	}

	@Override
	public void doDelete(String id) {
		wxActJiugonggePrizesRelationDao.delete(id);
	}

	@Override
	public WxActJiugonggeRelation queryById(String id) {
		WxActJiugonggeRelation wxActJiugonggePrizesRelation  = wxActJiugonggePrizesRelationDao.get(id);
		return wxActJiugonggePrizesRelation;
	}

	@Override
	public PageList<WxActJiugonggeRelation> queryPageList(
		PageQuery<WxActJiugonggeRelation> pageQuery) {
		PageList<WxActJiugonggeRelation> result = new PageList<WxActJiugonggeRelation>();
		Integer itemCount = wxActJiugonggePrizesRelationDao.count(pageQuery);
		List<WxActJiugonggeRelation> list = wxActJiugonggePrizesRelationDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActJiugonggeRelation> queryByActIdAndJwid(String actid,
			String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggePrizesRelationDao.queryByActIdAndJwid(actid, jwid);
	}

}
