package com.jeecg.p3.commonftb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import com.jeecg.p3.commonftb.service.WxActCommonftbService;
import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.dao.WxActCommonftbDao;

@Service("wxActCommonftbService")
public class WxActCommonftbServiceImpl implements WxActCommonftbService {
	@Resource
	private WxActCommonftbDao wxActCommonftbDao;

	@Override
	public void doAdd(WxActCommonftb wxActCommonftb) {
		wxActCommonftbDao.add(wxActCommonftb);
	}

	@Override
	public void doEdit(WxActCommonftb wxActCommonftb) {
		wxActCommonftbDao.update(wxActCommonftb);
	}

	@Override
	public void doDelete(String id) {
		wxActCommonftbDao.delete(id);
	}

	@Override
	public WxActCommonftb queryById(String id) {
		WxActCommonftb wxActCommonftb  = wxActCommonftbDao.get(id);
		return wxActCommonftb;
	}

	@Override
	public PageList<WxActCommonftb> queryPageList(
		PageQuery<WxActCommonftb> pageQuery) {
		PageList<WxActCommonftb> result = new PageList<WxActCommonftb>();
		Integer itemCount = wxActCommonftbDao.count(pageQuery);
		List<WxActCommonftb> list = wxActCommonftbDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActCommonftb> queryCommonftbByMainActId(String mainActId) {
		// TODO Auto-generated method stub
		return wxActCommonftbDao.queryCommonftbByMainActId(mainActId);
	}

	@Override
	public WxActCommonftb queryWxActCommonftbByMainActIdAndOpenid(
			String mainActId, String openid) {
		// TODO Auto-generated method stub
		return wxActCommonftbDao.queryWxActCommonftbByMainActIdAndOpenid(mainActId, openid);
	}

	@Override
	public List<WxActCommonftb> queryCommonftb(String jwid) {
		// TODO Auto-generated method stub
		return wxActCommonftbDao.queryCommonftb(jwid);
	}
	
}
