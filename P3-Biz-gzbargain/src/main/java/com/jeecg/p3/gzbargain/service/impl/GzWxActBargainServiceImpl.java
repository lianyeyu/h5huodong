package com.jeecg.p3.gzbargain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.p3.dict.service.SystemActTxtService;
import com.jeecg.p3.gzbargain.dao.GzWxActBargainDao;
import com.jeecg.p3.gzbargain.entity.GzWxActBargain;
import com.jeecg.p3.gzbargain.service.GzWxActBargainService;

@Service("gzWxActBargainService")
public class GzWxActBargainServiceImpl implements GzWxActBargainService {
	@Resource
	private GzWxActBargainDao gzWxActBargainDao;
	@Autowired
	private SystemActTxtService systemActTxtService;
	
	@Override
	public GzWxActBargain queryWxActBargain(String actId) {
		GzWxActBargain gzWxActBargain  = gzWxActBargainDao.get(actId);
		return gzWxActBargain;
	}

	@Override
	public void doAdd(GzWxActBargain gzWxActBargain) {
		gzWxActBargain.setProjectCode("gzbargain");
		gzWxActBargainDao.add(gzWxActBargain);
		systemActTxtService.doCopyTxt(WeiXinHttpUtil.getLocalValue("gzbargain", WeiXinHttpUtil.TXT_ACTID_KEY), gzWxActBargain.getId());
	}


	@Override
	public void doEdit(GzWxActBargain gzWxActBargain) {
		gzWxActBargainDao.update(gzWxActBargain);
	}


	@Override
	public void doDelete(String id) {
		gzWxActBargainDao.delete(id);
	}
	
	@Override
	public GzWxActBargain queryById(String id) {
		GzWxActBargain gzWxActBargain  = gzWxActBargainDao.get(id);
		return gzWxActBargain;
	}
	

	@Override
	public PageList<GzWxActBargain> queryPageList(
		PageQuery<GzWxActBargain> pageQuery) {
		PageList<GzWxActBargain> result = new PageList<GzWxActBargain>();
		Integer itemCount = gzWxActBargainDao.count(pageQuery);
		List<GzWxActBargain> list = gzWxActBargainDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
}
