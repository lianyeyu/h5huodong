package com.jeecg.p3.commonftb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.commonftb.dao.WxActCommonftbMainDao;
import com.jeecg.p3.commonftb.dao.WxActCommonftbRelationDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbMain;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRelation;
import com.jeecg.p3.commonftb.service.WxActCommonftbMainService;
import com.jeecg.p3.dict.service.SystemActTxtService;

@Service("wxActCommonftbMainService")
public class WxActCommonftbMainServiceImpl implements WxActCommonftbMainService {
	@Resource
	private WxActCommonftbMainDao wxActCommonftbMainDao;
	@Autowired
	private SystemActTxtService systemActTxtService;
	@Resource
	private WxActCommonftbRelationDao wxActCommonftbRelationDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doAdd(WxActCommonftbMain wxActCommonftbMain) {
		wxActCommonftbMain.setProjectCode("commonftb");
		wxActCommonftbMainDao.add(wxActCommonftbMain);
		List<WxActCommonftbRelation> ftbList = wxActCommonftbMain.getFtbList();
		if (ftbList != null) {
			for (WxActCommonftbRelation relation : ftbList) {
				relation.setMainActId(wxActCommonftbMain.getId());
			}
		}
		wxActCommonftbRelationDao.batchInsert(ftbList);
		systemActTxtService.doCopyTxt(WeiXinHttpUtil.getLocalValue("commonftb",
				WeiXinHttpUtil.TXT_ACTID_KEY), wxActCommonftbMain.getId());

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doEdit(WxActCommonftbMain wxActCommonftbMain) {
		wxActCommonftbMainDao.update(wxActCommonftbMain);
		List<WxActCommonftbRelation> newFtbList = wxActCommonftbMain
				.getFtbList();// 新的明细配置集合
		List<String> ids = new ArrayList<String>();
		if (newFtbList != null) {
			for (WxActCommonftbRelation relation : newFtbList) {
				if (StringUtils.isNotEmpty(relation.getId())) {
					ids.add(relation.getId());
				}
			}
			wxActCommonftbRelationDao.bactchDeleteOldFtbs(ids,
					wxActCommonftbMain.getId());// 批量删除不在新的明细配置集合的数据
			for (WxActCommonftbRelation relation : newFtbList) {
				if (StringUtils.isEmpty(relation.getId())) {
					relation.setMainActId(wxActCommonftbMain.getId());
					wxActCommonftbRelationDao.add(relation);
				} else {
					wxActCommonftbRelationDao.update(relation);
				}
			}
		} else {
			wxActCommonftbRelationDao.bactchDeleteOldFtbs(ids,
					wxActCommonftbMain.getId());// 批量删除不在新的明细配置集合的数据
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doDelete(String id) {
		wxActCommonftbMainDao.delete(id);
		wxActCommonftbRelationDao.batchDeleteByMainActId(id);// 同步活动明细配置
		systemActTxtService.batchDeleteByActCode(id);// 同步删除系统文本
	}

	@Override
	public WxActCommonftbMain queryById(String id) {
		WxActCommonftbMain wxActCommonftbMain = wxActCommonftbMainDao.get(id);
		return wxActCommonftbMain;
	}

	@Override
	public PageList<WxActCommonftbMain> queryPageList(
			PageQuery<WxActCommonftbMain> pageQuery) {
		PageList<WxActCommonftbMain> result = new PageList<WxActCommonftbMain>();
		Integer itemCount = wxActCommonftbMainDao.count(pageQuery);
		List<WxActCommonftbMain> list = wxActCommonftbMainDao.queryPageList(
				pageQuery, itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(),
				itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

}
