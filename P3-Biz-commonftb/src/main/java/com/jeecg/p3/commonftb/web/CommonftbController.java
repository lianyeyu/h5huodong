package com.jeecg.p3.commonftb.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.common.utils.Constants;
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
import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbMain;
import com.jeecg.p3.commonftb.entity.WxActCommonftbAwards;
import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRecord;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRegistration;
import com.jeecg.p3.commonftb.exception.CommonftbException;
import com.jeecg.p3.commonftb.exception.CommonftbExceptionEnum;
import com.jeecg.p3.commonftb.service.WxActCommonftbMainService;
import com.jeecg.p3.commonftb.service.WxActCommonftbService;
import com.jeecg.p3.commonftb.service.WxActCommonftbAwardsService;
import com.jeecg.p3.commonftb.service.WxActCommonftbCouponService;
import com.jeecg.p3.commonftb.service.WxActCommonftbRecordService;
import com.jeecg.p3.commonftb.service.WxActCommonftbRegistrationService;
import com.jeecg.p3.commonftb.util.EmojiFilter;
import com.jeecg.p3.commonftb.util.SendMsgUtils;
import com.jeecg.p3.commonftb.web.vo.BargainDto;
import com.jeecg.p3.dict.service.SystemActTxtService;

@Controller
@RequestMapping("/commonftb")
public class CommonftbController extends BaseController {
	public final static Logger LOG = LoggerFactory
			.getLogger(CommonftbController.class);
	@Autowired
	private WxActCommonftbMainService commonftbMainService;
	@Autowired
	private WxActCommonftbService commonftbService;
	@Autowired
	private WxActCommonftbRegistrationService commonftbRegistrationService;
	@Autowired
	private WxActCommonftbRecordService commonftbRecordService;
	@Autowired
	private WxActCommonftbAwardsService commonftbAwardsService;
	@Autowired
	private WxActCommonftbCouponService commonftbCouponService;
	@Autowired
	private SystemActTxtService systemActTxtService;
	/**
	 * 跳转到个人信息页,如未参加活动（子活动ID为空），则跳转到首页，如果是分享活动链接，则跳转到帮砍页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toIndex", method = { RequestMethod.GET,
			RequestMethod.POST })
			public void toIndex(@ModelAttribute BargainDto bargainDto,
					HttpServletRequest request, HttpServletResponse response,
					ModelMap model) throws Exception {
		LOG.info(request, "toIndex parameter BargainDto={}.",
				new Object[] { bargainDto });
		long start = System.currentTimeMillis();
		initBargainDto(bargainDto);
		// ====================================================================================================
		// 装载微信所需参数
		String jwid = bargainDto.getJwid();
		String appid = bargainDto.getAppid();
		String mainActId = bargainDto.getMainActId();
		// ====================================================================================================
		if (bargainDto.getFxOpenid() != null) {
			String nickname = WeiXinHttpUtil.getNickName(
					bargainDto.getFxOpenid(), jwid);
			bargainDto.setFxNickname(EmojiFilter.filterNickName(nickname));
		}
		if (bargainDto.getOpenid() != null) {
			String nickname = WeiXinHttpUtil.getNickName(
					bargainDto.getOpenid(), jwid);
			bargainDto.setNickname(EmojiFilter.filterNickName(nickname));
		}
		WxActCommonftbMain commonftbMain = commonftbMainService
		.queryById(mainActId);
		// 子活动ID为空，跳转到首页
		if (StringUtils.isEmpty(bargainDto.getActId())) {
			toHome(bargainDto, request, response);
			return;
		}
		// 跳转个人信息页处理
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonftb/default/vm/information.vm";
		try {
			// 参数验证
			validateBargainDtoParam(bargainDto);
			// 获取活动信息
			WxActCommonftb commonftb = commonftbService.queryById(bargainDto
					.getActId());
			if (commonftb == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "砍价活动不存在");
			}
			velocityContext.put("commonftb", commonftb);
			// 有效期内可参与
			Date currDate = new Date();
			if (currDate.before(commonftb.getBegainTime())) {
				String begainTime = DateUtil.convertToShowTime(commonftb
						.getBegainTime());
				throw new CommonftbException(
						CommonftbExceptionEnum.ACT_BARGAIN_NO_START,
						"活动未开始,开始时间为" + begainTime + ",请耐心等待！");
			}
			if (currDate.after(commonftb.getEndTime())) {
				throw new CommonftbException(
						CommonftbExceptionEnum.ACT_BARGAIN_END, "活动已结束");
			}
			if (StringUtils.isEmpty(commonftbMain.getTemplate())) {
				commonftbMain.setTemplate("default");
			}
			// 砍价记录
			List<WxActCommonftbRecord> bargainRecordList = new ArrayList<WxActCommonftbRecord>();
			// 判断是否是分享活动
			if (isShareAct(bargainDto)) {
				if (bargainDto.getFxOpenid().equals(bargainDto.getOpenid())) {
					viewName = "commonftb/"+commonftbMain.getTemplate()+"/vm/information.vm";
				} else {
					viewName = "commonftb/"+commonftbMain.getTemplate()+"/vm/bhykj.vm";
				}
				// 根据分享人openid查询分享人的报名信息
				WxActCommonftbRegistration commonftbRegistration = commonftbRegistrationService
				.queryRegistrationByOpenidAndActId(
						bargainDto.getFxOpenid(), bargainDto.getActId());
				if (commonftbRegistration == null) {
					throw new CommonftbException(
							CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "活动无效");
				}
				velocityContext
				.put("bargainRegistration", commonftbRegistration);
				// 累计砍掉价格
				velocityContext.put(
						"cutPrice",
						commonftbRegistration.getProductPrice().subtract(
								commonftbRegistration.getProductNewPrice()));
				// 查询砍价记录
				bargainRecordList = commonftbRecordService
				.queryBargainRecordListByRegistrationId(commonftbRegistration
						.getId());
				// 组合分享语内容
			} else {
				viewName = "commoninvite/" + commonftbMain.getTemplate()
				+ "/vm/information.vm";
				// 获取微信头像
				JSONObject json = WeiXinHttpUtil.getUserInfo(
						bargainDto.getOpenid(), jwid);
				String headimgurl = json.getString("headimgurl");
				// 根据访问人openid查询访问人的报名信息
				WxActCommonftbRegistration commonftbRegistration = commonftbRegistrationService
				.queryRegistrationByOpenidAndActId(
						bargainDto.getOpenid(), bargainDto.getActId());
				if (commonftbRegistration == null) {
					commonftbRegistration = new WxActCommonftbRegistration();
					commonftbRegistration.setId(RandomUtils.generateID());
					commonftbRegistration.setActId(bargainDto.getActId());
					commonftbRegistration.setOpenid(bargainDto.getOpenid());
					commonftbRegistration.setNickname(bargainDto.getNickname());
					commonftbRegistration.setHead(headimgurl);
					commonftbRegistration.setProductName(commonftb
							.getProductName());
					commonftbRegistration.setProductNewPrice(commonftb
							.getProductPrice());
					commonftbRegistration.setProductPrice(commonftb
							.getProductPrice());
					commonftbRegistration.setJwid(jwid);
					commonftbRegistration.setCreateTime(new Date());
					// 自砍一刀
					commonftbRecordService.bargainMyself(commonftbRegistration,
							commonftb);
				}
				bargainRecordList = commonftbRecordService
				.queryBargainRecordListByRegistrationId(commonftbRegistration
						.getId());
				velocityContext
				.put("bargainRegistration", commonftbRegistration);
				// 累计砍掉价格
				velocityContext.put(
						"cutPrice",
						commonftbRegistration.getProductPrice().subtract(
								commonftbRegistration.getProductNewPrice()));
			}
			velocityContext.put("recordListCount",
					bargainRecordList == null ? 0 : bargainRecordList.size());
			velocityContext.put("recordList", bargainRecordList);
			velocityContext.put("bargainDto", bargainDto);
			velocityContext.put("commonftbMain", commonftbMain);
			
			// update-begin-----author:scott---------date:21050809------for:获取分享signature------------------
			String url = request.getRequestURL() + "?"
			+ request.getQueryString();// .replace("&", "@");
			if (url.indexOf("#") != -1) {
				url = url.substring(0, url.indexOf("#"));
			}
			// update-begin----------for: 组合分享JS-SDK所需参数------------------
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",
					getOauth2Urltest(bargainDto, commonftbMain.getHdurl())); // 获取分享URL
			velocityContext.put("appId", appid);
			velocityContext.put("signature",
					WeiXinHttpUtil.getSignature(request, jwid));
			// update-end----------for: 组合分享JS-SDK所需参数------------------
			
		} catch (CommonftbException e) {
			LOG.error("toIndex error:{}", e.getMessage());
			viewName = "commonftb/default/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("toIndex error:{}", e);
			viewName = "commonftb/default/vm/error.vm";
			velocityContext.put("errCode",
					CommonftbExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					CommonftbExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response, viewName, velocityContext);
		LOG.info(request, "toIndex time={}ms.",
				new Object[] { System.currentTimeMillis() - start });
	}
	/**
	 * 跳转到活动首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toHome", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void toHome(@ModelAttribute BargainDto bargainDto,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOG.info(request, "toHome parameter BargainDto={}.",
				new Object[] { bargainDto });

		// ====================================================================================================
		// 装载微信所需参数
		String jwid = bargainDto.getJwid();
		String appid = bargainDto.getAppid();
		String mainActId = bargainDto.getMainActId();
		// ====================================================================================================

		if (bargainDto.getFxOpenid() != null) {
			String nickname = WeiXinHttpUtil.getNickName(
					bargainDto.getFxOpenid(), jwid);
			bargainDto.setFxNickname(EmojiFilter.filterNickName(nickname));
		}
		if (bargainDto.getOpenid() != null) {
			String nickname = WeiXinHttpUtil.getNickName(
					bargainDto.getOpenid(), jwid);
			bargainDto.setNickname(EmojiFilter.filterNickName(nickname));
		}
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonftb/default/vm/home.vm";
		try {
			// 参数验证
			validateBargainDtoParam(bargainDto);
			// 获取主活动信息
			WxActCommonftbMain commonftbMain = commonftbMainService
					.queryById(mainActId);
			if (commonftbMain == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			if (!bargainDto.getJwid().equals(commonftbMain.getJwid())) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR,
						"活动不属于该微信公众号");
			}
			Date currDate = new Date();
			if (currDate.before(commonftbMain.getBegainTime())) {
				String begainTime = DateUtil.convertToShowTime(commonftbMain
						.getBegainTime());
				throw new CommonftbException(
						CommonftbExceptionEnum.ACT_BARGAIN_NO_START,
						"活动未开始,开始时间为" + begainTime + ",请耐心等待！");
			}
			if (currDate.after(commonftbMain.getEndTime())) {
				throw new CommonftbException(
						CommonftbExceptionEnum.ACT_BARGAIN_END, "活动已结束");
			}
			if (StringUtils.isNotEmpty(commonftbMain.getTemplate())) {
				viewName = "commonftb/"+commonftbMain.getTemplate()+"/vm/home.vm";
			}
			// 查询产品活动列表
			List<WxActCommonftb> commonftbsList = commonftbService
					.queryCommonftbByMainActId(mainActId);
			if (commonftbsList.isEmpty()) {
				throw new CommonftbException(
						CommonftbExceptionEnum.ARGUMENT_ERROR, "不存在产品信息");
			}
			velocityContext.put("bargainDto", bargainDto);
			velocityContext.put("commonftbMain", commonftbMain);
			velocityContext.put("commonftbsList", commonftbsList);
			String url = request.getRequestURL() + "?"
					+ request.getQueryString();// .replace("&", "@");
			if (url.indexOf("#") != -1) {
				url = url.substring(0, url.indexOf("#"));
			}
			// ------微信分享参数-------
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",getOauth2Urltest(bargainDto, commonftbMain.getHdurl())); // 获取分享URL
			velocityContext.put("appId", appid);
			velocityContext.put("signature",
					WeiXinHttpUtil.getSignature(request, jwid));
			// ------微信分享参数-------

		} catch (CommonftbException e) {
			LOG.error("toHome error:{}", e.getMessage());
			viewName = "commonftb/default/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("toHome error:{}", e);
			viewName = "commonftb/default/vm/error.vm";
			velocityContext.put("errCode",
					CommonftbExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					CommonftbExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response, viewName, velocityContext);
	}

	/**
	 * 我要砍价
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wantBargain", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson wantBargain(@ModelAttribute BargainDto bargainDto,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "wantBargain parameter bargainDto={}.",
				new Object[] { bargainDto });
		try {
			// 参数验证
			validateBargainDtoParam(bargainDto);
			// 获取主活动信息
			WxActCommonftbMain commonftbMain = commonftbMainService
					.queryById(bargainDto.getMainActId());
			if (commonftbMain == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "砍价活动不存在");
			}
			// 获取子活动信息
			WxActCommonftb commonftb = commonftbService.queryById(bargainDto
					.getActId());
			if (commonftb == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "砍价活动不存在");
			}
			if("1".equals(commonftbMain.getManyCanJoin())){//如果主活动设置了用户只能参加一个子活动	
				 WxActCommonftb commonftbExist = commonftbService
				 .queryWxActCommonftbByMainActIdAndOpenid(
						 bargainDto.getMainActId(), bargainDto.getOpenid());
				 if (commonftbExist != null) {
					 if (!bargainDto.getActId().equals(commonftbExist.getId())) {
						 throw new CommonftbException(
								 CommonftbExceptionEnum.ACT_BARGAIN_JOIN_PRD,
								 "已参加过" + commonftbExist.getName()
								 + "的砍价<br/>不能再参加"
								 + commonftb.getName() + "的砍价");
					 }
				 }
			 }
			if("1".equals(commonftb.getFoucsUserCanJoin())){//如果选择的子活动设置了关注用户才能参加	
				//未关注
				 if("0".equals(bargainDto.getSubscribe())){
					 j.setSuccess(false);
						j.setObj("isNotFoucs");
						return j;
				 }
			 }
			// 产品剩余数量校验
			// 剩余0台
			if (commonftb.getProductRemainNum() < 1) {
				j.setSuccess(false);
				j.setObj("noProduct");
				return j;
			}
			j.setObj(commonftb);
			WxActCommonftbRegistration commonftbRegistration = commonftbRegistrationService
					.queryRegistrationByOpenidAndActId(bargainDto.getOpenid(),
							bargainDto.getActId());
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("join", false);// 当前活动还没参加过
			if (commonftbRegistration != null) {
				dataMap.put("join", true);// 当前活动正在参加
			}
			j.setAttributes(dataMap);

		} catch (CommonftbException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("commonftb error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("砍价异常!");
			LOG.error("commonftb error:{}", e.getMessage());
		}
		return j;
	}


	/**
	 * 去请人帮杀
	 * 
	 * @return
	 */
	@RequestMapping(value = "/goShare", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson goShare(@ModelAttribute BargainDto bargainDto,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "goShare parameter bargainDto={}.",
				new Object[] { bargainDto });
		try {
			// 参数验证
			validateGoShareParam(bargainDto);
			// 获取子活动信息
			WxActCommonftb commonftb = commonftbService.queryById(bargainDto
					.getActId());
			if (commonftb == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "砍价活动不存在");
			}
			// 根据openid和活动id查询报名信息
			WxActCommonftbRegistration commonftbRegistration = commonftbRegistrationService
					.queryRegistrationByOpenidAndActId(bargainDto.getOpenid(),
							bargainDto.getActId());
			if (commonftbRegistration == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "未找到报名信息");
			}
			// 判断是否砍到最低价
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("isCutMinPrice", false);
			BigDecimal cutMinPrice = commonftb.getCutMinPrice();
			BigDecimal productNewPrice = commonftbRegistration
					.getProductNewPrice();
			if (productNewPrice.compareTo(cutMinPrice) <= 0) {
				dataMap.put("isCutMinPrice", true);// 砍到最低价
			}
			j.setAttributes(dataMap);
			j.setSuccess(true);
		} catch (CommonftbException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("goShare error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("分享失败！");
			LOG.error("goShare error:{}", e.getMessage());
		}
		return j;
	}

	/**
	 * 兑奖处理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/receivePrize", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson receivePrize(@ModelAttribute BargainDto bargainDto,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "receivePrize parameter wxActJsftbbargainAwards={}.",
				new Object[] { bargainDto });
		try {
			// 参数验证
			validateGoReceivePrizeParam(bargainDto);
			// 获取主活动信息
			WxActCommonftbMain commonftbMain = commonftbMainService
					.queryById(bargainDto.getMainActId());
			if (commonftbMain == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			// 获取子活动信息
			WxActCommonftb commonftb = commonftbService.queryById(bargainDto
					.getActId());
			if (commonftb == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "砍价活动不存在");
			}
			// 获取兑奖截止日期
			Date awardsEndTime = commonftb.getAwardsEndTime();
			Date currDate = new Date();
			if (currDate.after(awardsEndTime)) {
				throw new CommonftbException(
						CommonftbExceptionEnum.ACT_BARGAIN_AWARDS_END_TIME,
						systemActTxtService.queryActTxtByCode(
								"controller.couponExpire", commonftbMain.getId()));
			}

			// 根据openid和活动id查询报名信息
			WxActCommonftbRegistration commonftbRegistration = commonftbRegistrationService
					.queryRegistrationByOpenidAndActId(bargainDto.getOpenid(),
							bargainDto.getActId());
			if (commonftbRegistration == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "未找到报名信息");
			}
			// 判断是否已中奖，中奖则直接返回中奖记录
			List<WxActCommonftbAwards> commonftbAwardsList = commonftbAwardsService
					.queryBargainAwardsByActIdAndOpenid(bargainDto.getActId(),
							bargainDto.getOpenid());
			BigDecimal cutMinPrice = commonftb.getCutMinPrice();
			BigDecimal productNewPrice = commonftbRegistration
			.getProductNewPrice();
			if("1".equals(commonftb.getIfCutMin())){//如果子活动设置了必须砍到底价才能领奖	
			   // 没有砍到最底价
				if (!(productNewPrice.compareTo(cutMinPrice) <= 0)) {
					j.setSuccess(false);
					j.setObj("isNotCutMinPrice");
					return j;
				}		
			}		
			// 判断是否可领奖
			Integer maxAwardsSeq = commonftbAwardsService
			.getMaxAwardsSeq(bargainDto.getActId());
			Integer nextAwardsSeq = maxAwardsSeq + 1;
			if (nextAwardsSeq > commonftb.getProductNum()) {
				throw new CommonftbException(
						CommonftbExceptionEnum.ACT_BARGAIN_PRIZE_NONE,
						systemActTxtService.queryActTxtByCode(
								"controller.noCoupon", commonftbMain.getId()));
			}
			if (Constants.PRIZE_RECEIVED.equals(commonftbRegistration
					.getAwardsStatus())) {
				WxActCommonftbAwards wxActCommonftbAwards = commonftbAwardsList
						.get(0);
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put("cardPsd", wxActCommonftbAwards.getCardPsd());
				j.setObj(dataMap);
			} else {
				String bindPhone="";
				if("1".equals(commonftb.getBindingMobileCanJoin())){//如果子活动设置了绑定手机号才能领奖	
					// 获取绑定手机号
					bindPhone = getBindPhone(bargainDto.getOpenid(),bargainDto.getJwid());
					// 判断是否绑定了手机号
					if (StringUtils.isEmpty(bindPhone)) {
						j.setSuccess(false);
						j.setObj("isNotBind");
						return j;
					}
				 }else{//如果子活动设置了不需要绑定手机号也能领奖，则需要用户填写手机号码	
					 bindPhone = bargainDto.getMobile();
						if (StringUtils.isEmpty(bindPhone)) {
							j.setSuccess(false);
							j.setObj("addPhone");
							return j;
						}

				 }	
				// 砍掉的价格（总计）
				BigDecimal cutPrice = commonftbRegistration.getProductPrice()
						.subtract(commonftbRegistration.getProductNewPrice());
				// 获取卡券ID
				WxActCommonftbCoupon commonftbCoupon = commonftbCouponService
						.routeCardId(bargainDto.getMainActId(), commonftb,
								cutPrice);
				WxActCommonftbAwards wxActCommonftbAwards = new WxActCommonftbAwards();
				wxActCommonftbAwards.setAwardsSeq(nextAwardsSeq);
				wxActCommonftbAwards.setMobile(bindPhone);
				// 新增中奖记录，更新报名者中奖信息，更新奖品剩余数量,更新卡券状态
				commonftbAwardsService.creatAwards(wxActCommonftbAwards,
						commonftbCoupon, commonftbRegistration, commonftb);

				// 返回中奖信息和密码
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put("cardPsd", commonftbCoupon.getCardPsd());
				j.setObj(dataMap);
				String smstext = systemActTxtService.queryActTxtByCode(
						"controller.shortMessage", commonftbMain.getId());
				String SMS = String.format(smstext, commonftbCoupon
						.getReduceCost().setScale(0).toString(),
						commonftbCoupon.getCardPsd(), commonftbCoupon.getTitle());
				LOG.info("-----shortMsg-----" + SMS);
				SendMsgUtils.sendSMS(SMS, bindPhone);
			}
			j.setSuccess(true);
		} catch (CommonftbException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("receivePrize error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("现金券兑换失败");
			LOG.error("receivePrize error:{}", e.getMessage());
		}
		return j;
	}

	/**
	 * 砍价处理
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bargain", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson bargain(
			@ModelAttribute WxActCommonftbRecord wxActCommonftbRecord,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "bargain parameter bargainDto={}.",
				new Object[] { wxActCommonftbRecord });
		try {
			// 参数验证
			validateGoBargainRecordParam(wxActCommonftbRecord);
			// 根据openid和活动id查询报名信息
			WxActCommonftbRegistration commonftbRegistration = commonftbRegistrationService
					.queryById(wxActCommonftbRecord.getRegistrationId());
			if (commonftbRegistration == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "未找到报名信息");
			}
			// 判断是否已经领奖了
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if (Constants.PRIZE_RECEIVED.equals(commonftbRegistration
					.getAwardsStatus())) {
				dataMap.put("cutCount", "received");
				j.setAttributes(dataMap);
				return j;
			}
			// 判断是否已经砍价
			List<WxActCommonftbRecord> bargainRecordList = commonftbRecordService
					.queryBargainRecordListByRegistrationIdAndOpenid(
							wxActCommonftbRecord.getRegistrationId(),
							wxActCommonftbRecord.getOpenid());
			if (bargainRecordList != null && bargainRecordList.size() > 0) {
				dataMap.put("cutCount", "next");
				j.setAttributes(dataMap);
				return j;
			}
			String cutCount = commonftbRecordService.bargain(
					wxActCommonftbRecord, commonftbRegistration);
			dataMap.put("cutCount", cutCount);
			j.setAttributes(dataMap);
			j.setObj(wxActCommonftbRecord);
		} catch (CommonftbException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("bargain error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("砍价失败!");
			LOG.error("bargain error:{}", e.getMessage());
		}
		return j;
	}

	/**
	 * 跳转到活动说明页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void detail(@ModelAttribute BargainDto bargainDto,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ====================================================================================================
		// 装载微信所需参数
		String jwid = bargainDto.getJwid();
		String appid = bargainDto.getAppid();
		String mainActId = bargainDto.getMainActId();
		// ====================================================================================================

		VelocityContext velocityContext;
		String viewName = "commonftb/default/vm/detail.vm";
		velocityContext = new VelocityContext();			
		try {
			// 获取主活动信息
			WxActCommonftbMain commonftbMain = commonftbMainService
					.queryById(mainActId);
			if (commonftbMain == null) {
				throw new CommonftbException(
						CommonftbExceptionEnum.DATA_NOT_EXIST_ERROR, "活动不存在");
			}
			velocityContext.put("commonftbMain", commonftbMain);
			velocityContext.put("bargainDto", bargainDto);
			String url = request.getRequestURL() + "?" + request.getQueryString();																				// "@");
			if (url.indexOf("#") != -1) {
				url = url.substring(0, url.indexOf("#"));
			}
			// ------微信分享参数-------
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl",
					getOauth2Urltest(bargainDto, commonftbMain.getHdurl())); // 获取分享URL
			velocityContext.put("appId", appid);
			velocityContext.put("signature",
					WeiXinHttpUtil.getSignature(request, jwid));
		} catch (CommonftbException e) {
			LOG.error("toIndex error:{}", e.getMessage());
			viewName = "commonftb/default/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("toIndex error:{}", e);
			viewName = "commonftb/default/vm/error.vm";
			velocityContext.put("errCode",
					CommonftbExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					CommonftbExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		// ------微信分享参数-------
		ViewVelocity.view(request,response, viewName, velocityContext);
	}

	private void validateGoBargainRecordParam(
			WxActCommonftbRecord wxActCommonftbRecord) {
		if (StringUtils.isEmpty(wxActCommonftbRecord.getRegistrationId())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"报名ID不能为空");
		}
		if (StringUtils.isEmpty(wxActCommonftbRecord.getOpenid())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"砍价人openid不能为空");
		}
	}

	private void validateGoShareParam(BargainDto bargainDto) {
		if (StringUtils.isEmpty(bargainDto.getActId())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"活动ID不能为空");
		}
		if (StringUtils.isEmpty(bargainDto.getOpenid())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"现金券兑换人openid不能为空");
		}
	}

	private void validateGoReceivePrizeParam(BargainDto bargainDto) {
		if (StringUtils.isEmpty(bargainDto.getActId())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"活动ID不能为空");
		}
		if (StringUtils.isEmpty(bargainDto.getOpenid())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"现金券兑换人openid不能为空");
		}
	}

	private void validateBargainDtoParam(BargainDto bargainDto) {
		if (StringUtils.isEmpty(bargainDto.getMainActId())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"活动ID不能为空");
		}
		if (StringUtils.isEmpty(bargainDto.getOpenid())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"参与人openid不能为空");
		}
		if (StringUtils.isEmpty(bargainDto.getJwid())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"微信ID不能为空");
		}
		if (StringUtils.isEmpty(bargainDto.getSubscribe())) {
			throw new CommonftbException(CommonftbExceptionEnum.ARGUMENT_ERROR,
					"关注状态不能为空");
		}
	}

	public String getOauth2Url(BargainDto bargainDto, String hdurl) {
		String oauth2url = "";
		String shareid = "";
		if (StringUtils.isEmpty(bargainDto.getActId())) {
			shareid = bargainDto.getOpenid();
		} else {
			if (StringUtils.isEmpty(bargainDto.getFxOpenid())) {
				shareid = bargainDto.getOpenid() + "," + bargainDto.getActId();
			} else {
				shareid = bargainDto.getFxOpenid() + ","
						+ bargainDto.getActId();
			}
		}
		String requestUrl = hdurl + shareid;
		JSONObject jsonObj = WeiXinHttpUtil.sendPost(requestUrl);
		if ("0".equals(jsonObj.getString("resultCode"))) {
			oauth2url = jsonObj.getString("oauth2url");
		} else {
			throw new CommonftbException(
					CommonftbExceptionEnum.ACT_BARGAIN_GEN_OAUTH_URL_ERROR);
		}
		return oauth2url;
	}

	public String getOauth2Urltest(BargainDto bargainDto, String hdurl) {
		String shareid = "";
		if (StringUtils.isEmpty(bargainDto.getActId())) {
			shareid = bargainDto.getOpenid();
		} else {
			if (StringUtils.isEmpty(bargainDto.getFxOpenid())) {
				shareid = bargainDto.getOpenid() + "," + bargainDto.getActId();
			} else {
				shareid = bargainDto.getFxOpenid() + ","
						+ bargainDto.getActId();
			}
		}
		String requestUrl = hdurl + "&fxOpenid=" + shareid;
		return requestUrl;
	}

	private boolean isShareAct(BargainDto bargainDto) {
		boolean flag = false;
		if (StringUtils.isNotEmpty(bargainDto.getFxOpenid())) {
			flag = true;
		}
		return flag;
	}

	private String getBindPhone(String openid,String jwid) {
		String bindPhine = "";
		try {
			JSONObject jsonObj = WeiXinHttpUtil.getUserInfo(openid, jwid);
			LOG.info("getBindPhone json{}.", new Object[] { jsonObj });
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

	// 江苏联通手机号可通过
	private String checkJsPhoneNum(String mobile, String checkPhoneEparchyUrl) {
		String flag = "0";// 默认为0，表示异网用户
		if (StringUtils.isNotEmpty(mobile)) {
			String requestUrl = checkPhoneEparchyUrl + mobile;
			LOG.info("----------checkJsPhoneNumAndNettype request--------------"
					+ requestUrl);
			JSONObject jsonObj = WeiXinHttpUtil.sendPost(requestUrl);
			LOG.info("----------checkJsPhoneNumAndNettype response--------------"
					+ jsonObj);
			if ("0".equals(jsonObj.getString("resultCode"))) {
				String provinceCode = jsonObj.getString("provinceCode");
				if ("34".equals(provinceCode)) {
					flag = "1";
				} else {
					flag = "0";
				}
			} else {
				flag = "fail";// 失败
			}
		}
		return flag;
	}

	private void initBargainDto(BargainDto bargainDto) {
		if (StringUtils.isNotEmpty(bargainDto.getFxOpenid())) {
			String[] params = bargainDto.getFxOpenid().split(",");
			if (params.length == 2) {
				bargainDto.setFxOpenid(params[0]);
				bargainDto.setActId(params[1]);
			}
		}

	}
}
