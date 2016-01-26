package com.jeecg.p3.commonftb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;

import com.jeecg.p3.commonftb.dao.WxActCommonftbRelationDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRelation;
import com.jeecg.p3.commonftb.service.WxActCommonftbRelationService;

@Service("wxActCommonftbRelationService")
public class WxActCommonftbRelationServiceImpl implements WxActCommonftbRelationService {
	@Resource
	private WxActCommonftbRelationDao wxActCommonftbRelationDao;

	@Override
	public void doAdd(WxActCommonftbRelation wxActCommonftbRelation) {
		wxActCommonftbRelationDao.add(wxActCommonftbRelation);
	}

	@Override
	public void doEdit(WxActCommonftbRelation wxActCommonftbRelation) {
		wxActCommonftbRelationDao.update(wxActCommonftbRelation);
	}

	@Override
	public void doDelete(String id) {
		wxActCommonftbRelationDao.delete(id);
	}

	@Override
	public WxActCommonftbRelation queryById(String id) {
		WxActCommonftbRelation wxActCommonftbRelation  = wxActCommonftbRelationDao.get(id);
		return wxActCommonftbRelation;
	}

	@Override
	public PageList<WxActCommonftbRelation> queryPageList(
		PageQuery<WxActCommonftbRelation> pageQuery) {
		PageList<WxActCommonftbRelation> result = new PageList<WxActCommonftbRelation>();
		Integer itemCount = wxActCommonftbRelationDao.count(pageQuery);
		List<WxActCommonftbRelation> list = wxActCommonftbRelationDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActCommonftbRelation> queryByMainActId(String mainActId) {
		// TODO Auto-generated method stub
		return wxActCommonftbRelationDao.queryByMainActId(mainActId);
	}
	
}
