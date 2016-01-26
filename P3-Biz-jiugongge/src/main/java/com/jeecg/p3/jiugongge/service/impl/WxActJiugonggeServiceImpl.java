package com.jeecg.p3.jiugongge.service.impl;

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

import com.jeecg.p3.dict.service.SystemActTxtService;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeDao;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRelationDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugongge;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRelation;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeService;

@Service("wxActJiugonggeService")
public class WxActJiugonggeServiceImpl implements WxActJiugonggeService {
	@Resource
	private WxActJiugonggeDao wxActJiugonggeDao;
	@Resource
	private WxActJiugonggeRelationDao wxActJiugonggeRelationDao;
	@Autowired
	private SystemActTxtService systemActTxtService;
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAdd(WxActJiugongge wxActJiugongge) {
		wxActJiugongge.setProjectCode("jiugongge");
		wxActJiugonggeDao.add(wxActJiugongge);
		List<WxActJiugonggeRelation> awardsList= wxActJiugongge.getAwarsList();
		if(awardsList!=null){
			for (WxActJiugonggeRelation wxActJiugonggeRelation : awardsList) {
				wxActJiugonggeRelation.setActId(wxActJiugongge.getId());
				if(wxActJiugonggeRelation.getProbability()==null){
					wxActJiugonggeRelation.setProbability(0d);
				}
			}
			wxActJiugonggeRelationDao.batchInsert(awardsList);
		}
		systemActTxtService.doCopyTxt(WeiXinHttpUtil.getLocalValue("jiugongge", WeiXinHttpUtil.TXT_ACTID_KEY), wxActJiugongge.getId());
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doEdit(WxActJiugongge wxActJiugongge) {
		wxActJiugonggeDao.update(wxActJiugongge);
		List<WxActJiugonggeRelation> newAwardsList= wxActJiugongge.getAwarsList();//新的明细配置集合
		List<String> ids=new ArrayList<String>();
		if(newAwardsList!=null){
			for (WxActJiugonggeRelation relation : newAwardsList) {
				if(StringUtils.isNotEmpty(relation.getId())){				
					ids.add(relation.getId());
				}
			}
			wxActJiugonggeRelationDao.bactchDeleteOldAwards(ids,wxActJiugongge.getId());//批量删除不在新的明细配置集合的数据
			for (WxActJiugonggeRelation wxActJiugonggeRelation : newAwardsList) {
				if(StringUtils.isEmpty(wxActJiugonggeRelation.getId())){
					wxActJiugonggeRelation.setActId(wxActJiugongge.getId());
					wxActJiugonggeRelationDao.add(wxActJiugonggeRelation);
				}else{
					wxActJiugonggeRelationDao.update(wxActJiugonggeRelation);
				}
			}
		}else{
			wxActJiugonggeRelationDao.bactchDeleteOldAwards(ids,wxActJiugongge.getId());//批量删除不在新的明细配置集合的数据
		}
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doDelete(String id) {
		wxActJiugonggeDao.delete(id);
		wxActJiugonggeRelationDao.batchDeleteByActId(id);//同步活动明细配置
		systemActTxtService.batchDeleteByActCode(id);//同步删除系统文本
	}

	@Override
	public WxActJiugongge queryById(String id) {
		WxActJiugongge wxActJiugongge  = wxActJiugonggeDao.get(id);
		return wxActJiugongge;
	}

	@Override
	public PageList<WxActJiugongge> queryPageList(
		PageQuery<WxActJiugongge> pageQuery) {
		PageList<WxActJiugongge> result = new PageList<WxActJiugongge>();
		Integer itemCount = wxActJiugonggeDao.count(pageQuery);
		List<WxActJiugongge> list = wxActJiugonggeDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActJiugongge> queryActs(String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggeDao.queryActs(jwid);
	}
	
}
