package com.jeecg.p3.commonftb.service.impl;

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

import org.jeecgframework.p3.core.common.utils.Constants;
import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.p3.commonftb.dao.WxActCommonftbAwardsDao;
import com.jeecg.p3.commonftb.dao.WxActCommonftbCouponDao;
import com.jeecg.p3.commonftb.dao.WxActCommonftbDao;
import com.jeecg.p3.commonftb.dao.WxActCommonftbRegistrationDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbAwards;
import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;
import com.jeecg.p3.commonftb.service.WxActCommonftbAwardsService;
import com.jeecg.p3.commonftb.util.ExcelUtil;

@Service("wxActCommonftbAwardsService")
public class WxActCommonftbAwardsServiceImpl implements WxActCommonftbAwardsService {
	@Resource
	private WxActCommonftbAwardsDao wxActCommonftbAwardsDao;
	@Resource
	private WxActCommonftbRegistrationDao wxActCommonftbRegistrationDao;
	@Resource
	private WxActCommonftbDao wxActCommonftbDao;
	@Resource
	private WxActCommonftbCouponDao wxActCommonftbCouponDao;

	@Override
	public void doAdd(WxActCommonftbAwards wxActCommonftbAwards) {
		wxActCommonftbAwardsDao.add(wxActCommonftbAwards);
	}

	@Override
	public void doEdit(WxActCommonftbAwards wxActCommonftbAwards) {
		wxActCommonftbAwardsDao.update(wxActCommonftbAwards);
	}

	@Override
	public void doDelete(String id) {
		wxActCommonftbAwardsDao.delete(id);
	}

	@Override
	public WxActCommonftbAwards queryById(String id) {
		WxActCommonftbAwards wxActCommonftbAwards  = wxActCommonftbAwardsDao.get(id);
		return wxActCommonftbAwards;
	}

	@Override
	public PageList<WxActCommonftbAwards> queryPageList(
		PageQuery<WxActCommonftbAwards> pageQuery) {
		PageList<WxActCommonftbAwards> result = new PageList<WxActCommonftbAwards>();
		Integer itemCount = wxActCommonftbAwardsDao.count(pageQuery);
		List<WxActCommonftbAwards> list = wxActCommonftbAwardsDao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}

	@Override
	public List<WxActCommonftbAwards> queryBargainAwardsByActIdAndOpenid(
			String actId, String openid) {
		// TODO Auto-generated method stub
		return wxActCommonftbAwardsDao.queryBargainAwardsByActIdAndOpenid(actId, openid);
	}

	@Override
	public Integer getMaxAwardsSeq(String actId) {
		// TODO Auto-generated method stub
		return wxActCommonftbAwardsDao.getMaxAwardsSeq(actId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void creatAwards(WxActCommonftbAwards wxActCommonftbAwards,
			WxActCommonftbCoupon commonftbCoupon,WxActCommonftbRegistration commonftbRegistration,WxActCommonftb commonftb) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		//新增中奖记录
		wxActCommonftbAwards.setId(RandomUtils.generateID());
		wxActCommonftbAwards.setCouponId(commonftbCoupon.getId());
		wxActCommonftbAwards.setCreateTime(currentDate);
		wxActCommonftbAwards.setActId(commonftb.getId());
		wxActCommonftbAwards.setOpenid(commonftbRegistration.getOpenid());
		wxActCommonftbAwards.setJwid(commonftb.getJwid());
		wxActCommonftbAwards.setNickname(commonftbRegistration.getNickname());
		wxActCommonftbAwardsDao.add(wxActCommonftbAwards);		
		//更新报名人中奖状态
		commonftbRegistration.setAwardsStatus(Constants.PRIZE_RECEIVED);
		wxActCommonftbRegistrationDao.update(commonftbRegistration);
		//更新子活动剩余产品数量
		wxActCommonftbDao.updateProductRemainNum(commonftb.getId(),1,currentDate);
		//更新卡券中奖状态
		commonftbCoupon.setStatus(Constants.PRIZE_RECEIVED);
		wxActCommonftbCouponDao.update(commonftbCoupon);	
	}

	@Override
	public List<WxActCommonftbAwards> queryBargainAwardsByMainActIdOpenid(String mainActId,String openid) {
		// TODO Auto-generated method stub
		return wxActCommonftbAwardsDao.queryBargainAwardsByMainActIdOpenid(mainActId,openid);
	}

	@Override
	public InputStream exportExcel(String actId)
			throws FileNotFoundException {
	    List<WxActCommonftbAwards> listAwards = wxActCommonftbAwardsDao.queryBargainAwardsByActId(actId);  
	    File file = new File(new Date(0).getTime()+".xls");  
	    OutputStream outputStream;
			outputStream = new FileOutputStream(file);
	    ExcelUtil.exportExcel("导出信息", WxActCommonftbAwards.class, listAwards, outputStream);  
	    InputStream is = new BufferedInputStream(new FileInputStream(file.getPath()));  
	    file.delete();        
	    return is;  
	}
	
}
