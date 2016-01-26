package com.jeecg.p3.jiugongge.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jeecg.p3.dict.service.SystemActTxtService;
import com.jeecg.p3.jiugongge.entity.WxActJiugongge;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggePrizes;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRecord;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRegistration;
import com.jeecg.p3.jiugongge.exception.JiugonggeException;
import com.jeecg.p3.jiugongge.exception.JiugonggeExceptionEnum;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeAwardsService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggePrizesService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRecordService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRegistrationService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRelationService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeService;
import com.jeecg.p3.jiugongge.util.EmojiFilter;
import com.jeecg.p3.jiugongge.util.LotteryUtil;

/**
 * 描述：九宫格
 * 
 * @author junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四
 * @version:1.0
 */
@Controller
@RequestMapping("/jiugongge")
public class JiugonggeController extends BaseController {

	public final static Logger LOG = LoggerFactory
			.getLogger(JiugonggeController.class);
	@Autowired
	private WxActJiugonggeService wxActJiugonggeService;
	@Autowired
	private WxActJiugonggeRelationService wxActJiugonggeRelationService;
	@Autowired
	private WxActJiugonggePrizesService wxActJiugonggePrizesService;
	@Autowired
	private WxActJiugonggeRegistrationService wxActJiugonggeRegistrationService;
	@Autowired
	private WxActJiugonggeRecordService wxActJiugonggeRecordService;
	@Autowired
	private WxActJiugonggeAwardsService wxActJiugonggeAwardsService;
	@Autowired
	private SystemActTxtService systemActTxtService;

	/**
	 * 跳转到活动首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toIndex", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void toIndex(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		LOG.info(request, "toIndex parameter WeixinDto={}.",
				new Object[] { weixinDto });
		long start = System.currentTimeMillis();
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		if (weixinDto.getOpenid() != null) {
			String nickname = WeiXinHttpUtil.getNickName(weixinDto.getOpenid(),
					jwid);
			weixinDto.setNickname(EmojiFilter.filterNickName(nickname));
		}
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/vm/index.vm";
		WxActJiugongge wxActJiugongge=null;
		try {
			// 参数验证
			validateWeixinDtoParam(weixinDto);
			// 获取活动信息
			wxActJiugongge = wxActJiugonggeService
					.queryById(weixinDto.getActId());
			if (wxActJiugongge == null) {
				throw new JiugonggeException(
						JiugonggeExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			if (!weixinDto.getJwid().equals(wxActJiugongge.getJwid())) {
				throw new JiugonggeException(
						JiugonggeExceptionEnum.DATA_NOT_EXIST_ERROR,
						"活动不属于该微信公众号");
			}
			velocityContext.put("bargain", wxActJiugongge);
			// 有效期内可参与
			Date currDate = new Date();
			if (currDate.before(wxActJiugongge.getStarttime())) {
				String begainTime = DateUtil.convertToShowTime(wxActJiugongge
						.getStarttime());
				throw new JiugonggeException(
						JiugonggeExceptionEnum.ACT_BARGAIN_NO_START,
						"活动未开始,开始时间为" + begainTime + ",请耐心等待！");
			}
			if (currDate.after(wxActJiugongge.getEndtime())) {
				throw new JiugonggeException(
						JiugonggeExceptionEnum.ACT_BARGAIN_END, "活动已结束");
			}
			// 活动奖品
			List<WxActJiugonggePrizes> wxActJiugonggePrizesList = wxActJiugonggePrizesService
					.queryByActId(weixinDto.getActId());
			Map<String,String> prizeMap = Maps.newConcurrentMap();
			int i=1;
			for (WxActJiugonggePrizes wxActJiugonggePrizes : wxActJiugonggePrizesList) {								
				prizeMap.put("prizeImg"+i, wxActJiugonggePrizes.getImg());
				i++;
			}
			velocityContext.put("prizeMap", prizeMap);
			velocityContext.put("prizeList", wxActJiugonggePrizesList);
			if(wxActJiugongge.getNumPerDay()==0){//每天次数设置为0，代表不限制每天抽奖次数
				velocityContext.put("perday", 0);
			}
			// 根据访问人openid查询访问人的信息
			WxActJiugonggeRegistration wxActJiugonggeRegistration = wxActJiugonggeRegistrationService
					.queryRegistrationByOpenidAndActIdAndJwid(
							weixinDto.getOpenid(), weixinDto.getActId(),
							weixinDto.getJwid());
			if (wxActJiugonggeRegistration == null) {
				wxActJiugonggeRegistration = new WxActJiugonggeRegistration();
				wxActJiugonggeRegistration.setId(RandomUtils.generateID());
				wxActJiugonggeRegistration.setActId(actId);
				wxActJiugonggeRegistration.setOpenid(weixinDto.getOpenid());
				wxActJiugonggeRegistration.setNickname(weixinDto.getNickname());
				wxActJiugonggeRegistration.setCreateTime(new Date());
				wxActJiugonggeRegistration.setAwardsStatus("0");
				wxActJiugonggeRegistration.setAwardsNum(0);
				wxActJiugonggeRegistration.setJwid(wxActJiugongge.getJwid());
				wxActJiugonggeRegistrationService
						.add(wxActJiugonggeRegistration);// 如果当前访问人员不在参与活动的人员表中，则记录到参与活动人员表中
			}

			velocityContext.put("registration", wxActJiugonggeRegistration);
			velocityContext.put("weixinDto", weixinDto);
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",wxActJiugongge.getHdurl());
			velocityContext.put("appId", appid);			
			velocityContext.put("signature",WeiXinHttpUtil.getSignature(request, jwid));
			LOG.info(request, "toIndex time={}ms.",
					new Object[] { System.currentTimeMillis() - start });
		} catch (JiugonggeException e) {
			LOG.error("toIndex error:{}", e.getMessage());
			viewName = "jiugongge/vm/error.vm";
			velocityContext.put("bargain", wxActJiugongge);
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("toIndex error:{}", e);
			viewName = "jiugongge/vm/error.vm";
			velocityContext.put("errCode",
					JiugonggeExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					JiugonggeExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	private void validateWeixinDtoParam(WeixinDto weixinDto) {
		if (StringUtils.isEmpty(weixinDto.getActId())) {
			throw new JiugonggeException(JiugonggeExceptionEnum.ARGUMENT_ERROR,
					"活动ID不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getOpenid())) {
			throw new JiugonggeException(JiugonggeExceptionEnum.ARGUMENT_ERROR,
					"参与人openid不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getJwid())) {
			throw new JiugonggeException(JiugonggeExceptionEnum.ARGUMENT_ERROR,
					"微信ID不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getSubscribe())) {
			throw new JiugonggeException(JiugonggeExceptionEnum.ARGUMENT_ERROR,
					"关注状态不能为空");
		}
	}

	/**
	 * 抽奖
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAwards", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson getAwards(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "getAwards parameter WeixinDto={}.",
				new Object[] { weixinDto });
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		try {

			// 参数验证
			validateWeixinDtoParam(weixinDto);
			if (weixinDto.getOpenid() != null) {
				String nickname = WeiXinHttpUtil.getNickName(
						weixinDto.getOpenid(), jwid);
				weixinDto.setNickname(EmojiFilter.filterNickName(nickname));
			}
			// 获取活动信息
			WxActJiugongge wxActJiugongge = wxActJiugonggeService
					.queryById(weixinDto.getActId());
			if("1".equals(wxActJiugongge.getFoucsUserCanJoin())){//如果活动设置了需要关注用户才能参加	
				//未关注
				 if("0".equals(weixinDto.getSubscribe())){
					 j.setSuccess(false);
						j.setObj("isNotFoucs");
						return j;
				 }
			 }
			if("1".equals(wxActJiugongge.getBindingMobileCanJoin())){//如果活动设置了需要绑定手机号才能参加				
				// 获取绑定手机号
				String bindPhone = getBindPhone(weixinDto.getOpenid(),jwid);
				// 判断是否绑定了手机号
				if (StringUtils.isEmpty(bindPhone)) {
					j.setSuccess(false);
					j.setObj("isNotBind");
					return j;
				}
			}
			// 判断总抽奖次数是否用完
			Date currDate = new Date();
			List<WxActJiugonggeRecord> bargainRecordList = wxActJiugonggeRecordService
			.queryBargainRecordListByOpenidAndActidAndJwid(
					weixinDto.getOpenid(), weixinDto.getActId(),
					weixinDto.getJwid(), null);
			if (bargainRecordList != null
					&& bargainRecordList.size() >= wxActJiugongge.getCount()) {
				throw new JiugonggeException(
						JiugonggeExceptionEnum.DATA_EXIST_ERROR,systemActTxtService.queryActTxtByCode(
								"controller.exception.nocount",
								weixinDto.getActId()));
			}
			if(wxActJiugongge.getNumPerDay()!= 0){	//每天次数设置为0，代表不限制每天抽奖次数，如果不等于0代表限制了每天抽奖次数	
				bargainRecordList = wxActJiugonggeRecordService
				.queryBargainRecordListByOpenidAndActidAndJwid(
						weixinDto.getOpenid(), weixinDto.getActId(),
						weixinDto.getJwid(), currDate);
				if (bargainRecordList != null
						&& bargainRecordList.size() >= wxActJiugongge
						.getNumPerDay()) {
					throw new JiugonggeException(
							JiugonggeExceptionEnum.DATA_EXIST_ERROR,systemActTxtService.queryActTxtByCode(
									"controller.exception.nownocount",
									weixinDto.getActId()));
				}
			}
			//生成用户的抽奖记录
			WxActJiugonggeRecord wxActJiugonggeRecord = new WxActJiugonggeRecord();
			wxActJiugonggeRecord.setId(RandomUtils.generateID());
			wxActJiugonggeRecord.setActId(weixinDto.getActId());
			wxActJiugonggeRecord.setNickname(weixinDto.getNickname());
			wxActJiugonggeRecord.setOpenid(weixinDto.getOpenid());
			wxActJiugonggeRecord.setJwid(weixinDto.getJwid());
			wxActJiugonggeRecord.setRecieveTime(new Date());
			Map<String,Object> map = new HashMap<String,Object>();
			//为用户抽取活动奖品
			if("0".equals(wxActJiugongge.getPrizeStatus())){//中奖可继续参与		
				//活动奖品
				List<WxActJiugonggePrizes> awards = wxActJiugonggePrizesService
				.queryRemainAwardsByActId(weixinDto.getActId());
				//得到各奖品的概率列表
				List<Double> orignalRates = new ArrayList<Double>(awards.size());
				for (WxActJiugonggePrizes award : awards) {
					int remainNum = award.getRemainNum();
					double probability = award.getProbability();
					if (remainNum <= 0) {//剩余数量为零，需使它不能被抽到
						probability = 0;
					}
					orignalRates.add(probability);
				}
				//根据概率产生奖品
				WxActJiugonggePrizes tuple = new WxActJiugonggePrizes();			
				int index = LotteryUtil.lottery(orignalRates);
				if (index>=0) {//中奖啦
					tuple= awards.get(index);
					wxActJiugonggeRecord.setAwardsId(tuple.getAwardId());
					map.put("index", index+1);
				}
			}else{//一旦中奖不可继续参与	
				// 中奖记录
				bargainRecordList  = wxActJiugonggeRecordService
				.queryMyAwardsByOpenidAndActidAndJwid(
						weixinDto.getOpenid(), weixinDto.getActId(),
						weixinDto.getJwid());
				if (bargainRecordList.size()==0) {//未曾中过奖项可继续正常参与抽奖
					//活动奖品
					List<WxActJiugonggePrizes> awards = wxActJiugonggePrizesService
					.queryRemainAwardsByActId(weixinDto.getActId());
					//得到各奖品的概率列表
					List<Double> orignalRates = new ArrayList<Double>(awards.size());
					for (WxActJiugonggePrizes award : awards) {
						int remainNum = award.getRemainNum();
						double probability = award.getProbability();
						if (remainNum <= 0) {//剩余数量为零，需使它不能被抽到
							probability = 0;
						}
						orignalRates.add(probability);
					}
					//根据概率产生奖品
					WxActJiugonggePrizes tuple = new WxActJiugonggePrizes();			
					int index = LotteryUtil.lottery(orignalRates);
					if (index>=0) {//中奖啦
						tuple= awards.get(index);
						wxActJiugonggeRecord.setAwardsId(tuple.getAwardId());
						map.put("index", index+1);
					}
				}				
			}
						
			WxActJiugonggePrizes wxActJiugonggePrize = wxActJiugonggeRecordService.creatAwards(wxActJiugonggeRecord);
			j.setSuccess(true);
			String basePath = request.getContextPath();
			map.put("basePath",basePath);
			map.put("wxActJiugonggeRecord",wxActJiugonggeRecord);
			map.put("wxActJiugonggePrize", wxActJiugonggePrize);
			
			j.setAttributes(map);
			
			j.setObj(wxActJiugonggePrize);
		} catch (JiugonggeException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("bargain error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("抽奖失败!");
			LOG.error("bargain error:{}", e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到我的奖品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/myawardrecord", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void myawardrecord(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		LOG.info(request, "myawardrecord parameter WeixinDto={}.",
				new Object[] { weixinDto });
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/vm/myprizes.vm";
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		try {
			// 我的中奖记录
			List<WxActJiugonggeRecord> recordList = new ArrayList<WxActJiugonggeRecord>();
			recordList = wxActJiugonggeRecordService
					.queryMyAwardsByOpenidAndActidAndJwid(
							weixinDto.getOpenid(), weixinDto.getActId(),
							weixinDto.getJwid());
			velocityContext.put("recordList", recordList);
			// 获取活动信息
			WxActJiugongge wxActJiugongge = wxActJiugonggeService
					.queryById(weixinDto.getActId());
			velocityContext.put("bargain", wxActJiugongge);
			velocityContext.put("weixinDto", weixinDto);
			
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",wxActJiugongge.getHdurl());
			velocityContext.put("appId", appid);
			velocityContext.put("signature",WeiXinHttpUtil.getSignature(request, jwid));
		} catch (JiugonggeException e) {
			LOG.error("myawardrecord error:{}", e.getMessage());
			viewName = "jiugongge/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("myawardrecord error:{}", e);
			viewName = "jiugongge/vm/error.vm";
			velocityContext.put("errCode",
					JiugonggeExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					JiugonggeExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到获奖名单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/winners", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void winners(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		LOG.info(request, "winners parameter WeixinDto={}.",
				new Object[] { weixinDto });
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/vm/winners.vm";
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		try {
			// 获取活动信息
			WxActJiugongge wxActJiugongge = wxActJiugonggeService
					.queryById(weixinDto.getActId());
			// 我的中奖记录
			List<WxActJiugonggeRecord> recordList = new ArrayList<WxActJiugonggeRecord>();
			velocityContext.put("bargain", wxActJiugongge);
			velocityContext.put("weixinDto", weixinDto);
			recordList = wxActJiugonggeRecordService.queryBargainRecordListByActidAndJwid(weixinDto.getActId(), weixinDto.getJwid());
			velocityContext.put("recordList", recordList);
			
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",wxActJiugongge.getHdurl());
			velocityContext.put("appId", appid);
			velocityContext.put("signature",WeiXinHttpUtil.getSignature(request, jwid));
		} catch (JiugonggeException e) {
			LOG.error("winners error:{}", e.getMessage());
			viewName = "jiugongge/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("winners error:{}", e);
			viewName = "jiugongge/vm/error.vm";
			velocityContext.put("errCode",
					JiugonggeExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					JiugonggeExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response,viewName,velocityContext);
	}
	

	/**
	 * 领奖
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateRecord", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson updateRecord(@ModelAttribute WxActJiugonggeRecord wxActJiugonggeRecord,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "updateRecord parameter wxActJiugonggeRecord={}.",
				new Object[] { wxActJiugonggeRecord });
		try {
			wxActJiugonggeRecordService.doEdit(wxActJiugonggeRecord);
			j.setSuccess(true);
		} catch (JiugonggeException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("bargain error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("领奖失败!");
			LOG.error("bargain error:{}", e.getMessage());
		}
		return j;
	}
	
	private String getBindPhone(String openid, String jwid) {
		String bindPhine = "";
		try {
			JSONObject jsonObj = WeiXinHttpUtil.getUserInfo(openid, jwid);
			LOG.info("getBindPhine json{}.", new Object[] { jsonObj });
			if (jsonObj.containsKey("bindPhoneStatus")) {
				if ("Y".equals(jsonObj.getString("bindPhoneStatus"))) {
					if (jsonObj.containsKey("phone")) {
						bindPhine = jsonObj.getString("phone");
					}
				}
			}
		} catch (Exception e) {
		}
		return bindPhine;
	}
}
