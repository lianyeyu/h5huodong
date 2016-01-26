package com.jeecg.p3.shaketicket.service.impl;

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
import com.jeecg.p3.shaketicket.dao.WxActShaketicketConfigDao;
import com.jeecg.p3.shaketicket.dao.WxActShaketicketHomeDao;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketConfig;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketHome;
import com.jeecg.p3.shaketicket.service.WxActShaketicketHomeService;

@Service("wxActShaketicketHomeService")
public class WxActShaketicketHomeServiceImpl implements WxActShaketicketHomeService {
	@Resource
	private WxActShaketicketHomeDao wxActShaketicketHomeDao;
	@Resource
	private WxActShaketicketConfigDao wxActShaketicketConfigDao;
	@Autowired
	private SystemActTxtService systemActTxtService;
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAdd(WxActShaketicketHome wxActShaketicketHome) {
		wxActShaketicketHome.setProjectCode("shaketicket");
		wxActShaketicketHomeDao.add(wxActShaketicketHome);
		List<WxActShaketicketConfig> awardsList= wxActShaketicketHome.getAwarsList();
		if(awardsList!=null){
			for (WxActShaketicketConfig actShaketicketConfig : awardsList) {
				actShaketicketConfig.setActId(wxActShaketicketHome.getId());
				if(actShaketicketConfig.getProbability()==null){
					actShaketicketConfig.setProbability(0d);
				}
			}
			wxActShaketicketConfigDao.batchInsert(awardsList);
		}
		systemActTxtService.doCopyTxt(WeiXinHttpUtil.getLocalValue("shaketicket", WeiXinHttpUtil.TXT_ACTID_KEY), wxActShaketicketHome.getId());
	}

	@Override
	public void doEdit(WxActShaketicketHome wxActShaketicketHome) {
		wxActShaketicketHomeDao.update(wxActShaketicketHome);
		List<WxActShaketicketConfig> newAwardsList= wxActShaketicketHome.getAwarsList();//新的明细配置集合
		List<String> ids=new ArrayList<String>();
		if(newAwardsList!=null){
			for (WxActShaketicketConfig relation : newAwardsList) {
				if(StringUtils.isNotEmpty(relation.getId())){				
					ids.add(relation.getId());
				}
			}
			wxActShaketicketConfigDao.bactchDeleteOldAwards(ids,wxActShaketicketHome.getId());//批量删除不在新的明细配置集合的数据
			for (WxActShaketicketConfig actShaketicketConfig : newAwardsList) {
				if(StringUtils.isEmpty(actShaketicketConfig.getId())){
					actShaketicketConfig.setActId(wxActShaketicketHome.getId());
					wxActShaketicketConfigDao.add(actShaketicketConfig);
				}else{
					wxActShaketicketConfigDao.update(actShaketicketConfig);
				}
			}
		}else{
			wxActShaketicketConfigDao.bactchDeleteOldAwards(ids,wxActShaketicketHome.getId());//批量删除不在新的明细配置集合的数据
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doDelete(String id) {
		wxActShaketicketHomeDao.delete(id);
		wxActShaketicketConfigDao.batchDeleteByActId(id);//同步活动明细配置
		systemActTxtService.batchDeleteByActCode(id);//同步删除系统文本
	}

	@Override
	public WxActShaketicketHome queryById(String id) {
		WxActShaketicketHome wxActShaketicketHome  = wxActShaketicketHomeDao.get(id);
		return wxActShaketicketHome;
	}

	@Override
	public PageList<WxActShaketicketHome> queryPageList(
		PageQuery<WxActShaketicketHome> pageQuery) {
		PageList<WxActShaketicketHome> result = new PageList<WxActShaketicketHome>();
		Integer itemCount = wxActShaketicketHomeDao.count(pageQuery);
		List<WxActShaketicketHome> list = wxActShaketicketHomeDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
