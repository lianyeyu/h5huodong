package com.jeecg.p3.jiugongge.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.jiugongge.dao.WxActJiugonggePrizesDao;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRecordDao;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRegistrationDao;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRelationDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggePrizes;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRecord;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRegistration;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRecordService;
import com.jeecg.p3.jiugongge.util.ContextHolderUtils;
import com.jeecg.p3.jiugongge.util.ExcelUtil;

@Service("wxActJiugonggeRecordService")
public class WxActJiugonggeRecordServiceImpl implements WxActJiugonggeRecordService {
	@Resource
	private WxActJiugonggeRecordDao wxActJiugonggeRecordDao;
	@Resource
	private WxActJiugonggePrizesDao wxActJiugonggePrizesDao;
	@Resource
	private WxActJiugonggeRegistrationDao wxActJiugonggeRegistrationDao;
	@Resource
	private WxActJiugonggeRelationDao wxActJiugonggeRelationDao;
	@Override
	public void doAdd(WxActJiugonggeRecord wxActJiugonggeRecord) {
		wxActJiugonggeRecordDao.add(wxActJiugonggeRecord);
	}

	@Override
	public void doEdit(WxActJiugonggeRecord wxActJiugonggeRecord) {
		wxActJiugonggeRecordDao.update(wxActJiugonggeRecord);
	}

	@Override
	public void doDelete(String id) {
		wxActJiugonggeRecordDao.delete(id);
	}

	@Override
	public WxActJiugonggeRecord queryById(String id) {
		WxActJiugonggeRecord wxActJiugonggeRecord  = wxActJiugonggeRecordDao.get(id);
		return wxActJiugonggeRecord;
	}

	@Override
	public PageList<WxActJiugonggeRecord> queryPageList(
		PageQuery<WxActJiugonggeRecord> pageQuery) {
		PageList<WxActJiugonggeRecord> result = new PageList<WxActJiugonggeRecord>();
		Integer itemCount = wxActJiugonggeRecordDao.count(pageQuery);
		List<WxActJiugonggeRecord> list = wxActJiugonggeRecordDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	@Override
	public PageList<WxActJiugonggeRecord> queryPageListForJoin(
			PageQuery<WxActJiugonggeRecord> pageQuery) {
		PageList<WxActJiugonggeRecord> result = new PageList<WxActJiugonggeRecord>();
		Integer itemCount = wxActJiugonggeRecordDao.count(pageQuery);
		List<WxActJiugonggeRecord> list = wxActJiugonggeRecordDao.queryPageListForJoin(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActJiugonggeRecord> queryBargainRecordListByOpenidAndActidAndJwid(
			String openid, String actId, String jwid, Date currDate) {
		// TODO Auto-generated method stub
		return wxActJiugonggeRecordDao.queryBargainRecordListByOpenidAndActidAndJwid(openid, actId, jwid, currDate);
	}
	@Override
	public List<WxActJiugonggeRecord> queryMyAwardsByOpenidAndActidAndJwid(
			String openid, String actId, String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggeRecordDao.queryMyAwardsByOpenidAndActidAndJwid(openid, actId, jwid);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public WxActJiugonggePrizes creatAwards(
			WxActJiugonggeRecord wxActJiugonggeRecord) {
		// TODO Auto-generated method stub
		//获取中奖序号
		Integer maxAwardsSeq = wxActJiugonggeRecordDao.getMaxAwardsSeq(wxActJiugonggeRecord.getActId());
		Integer nextAwardsSeq = maxAwardsSeq+1;
		wxActJiugonggeRecord.setSeq(nextAwardsSeq);
		wxActJiugonggeRecordDao.add(wxActJiugonggeRecord);
		 //查询访问人的信息 
		 WxActJiugonggeRegistration wxActJiugonggeRegistration =  wxActJiugonggeRegistrationDao.queryRegistrationByOpenidAndActIdAndJwid(wxActJiugonggeRecord.getOpenid(), wxActJiugonggeRecord.getActId(), wxActJiugonggeRecord.getJwid());
		 wxActJiugonggeRegistration.setAwardsStatus("1");
		 wxActJiugonggeRegistration.setAwardsNum(wxActJiugonggeRegistration.getAwardsNum()+1);
		 wxActJiugonggeRegistrationDao.update(wxActJiugonggeRegistration);//重置状态	
		 if(StringUtils.isEmpty(wxActJiugonggeRecord.getAwardsId())){
			 return new WxActJiugonggePrizes();			 
		 }else{
			 wxActJiugonggeRelationDao.updateRemainNum(wxActJiugonggeRecord.getActId(), wxActJiugonggeRecord.getJwid(), wxActJiugonggeRecord.getAwardsId());
			 return wxActJiugonggePrizesDao.queryByAwardIdAndActId(wxActJiugonggeRecord.getAwardsId(),wxActJiugonggeRecord.getActId()).get(0);
		 }
	}

	@Override
	public List<WxActJiugonggeRecord> queryBargainRecordListByActidAndJwid(String actId, String jwid) {
		// TODO Auto-generated method stub
		return wxActJiugonggeRecordDao.queryBargainRecordListByActidAndJwid(actId, jwid);
	}

	@Override
	public InputStream exportExcel(String actId, String jwid) throws FileNotFoundException {
		// TODO Auto-generated method stub
	    List<WxActJiugonggeRecord> listUser = wxActJiugonggeRecordDao.exportRecordListByActidAndJwid(actId, jwid);  
	    File file = new File(new Date(0).getTime()+".xls");  
	    OutputStream outputStream;
			outputStream = new FileOutputStream(file);
	    ExcelUtil.exportExcel("导出信息", WxActJiugonggeRecord.class, listUser, outputStream);  
	    InputStream is = new BufferedInputStream(new FileInputStream(file.getPath()));  
	    file.delete();        
	    return is;  
	}

}
