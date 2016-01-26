package com.jeecg.p3.shaketicket.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.base.vo.WeixinDto;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.jeecgframework.p3.core.util.WeiXinHttpUtil;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.dict.service.SystemActTxtService;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketAward;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketHome;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketRecord;
import com.jeecg.p3.shaketicket.exception.ShaketicketHomeException;
import com.jeecg.p3.shaketicket.exception.ShaketicketHomeExceptionEnum;
import com.jeecg.p3.shaketicket.service.WxActShaketicketAwardService;
import com.jeecg.p3.shaketicket.service.WxActShaketicketHomeService;
import com.jeecg.p3.shaketicket.service.WxActShaketicketRecordService;
import com.jeecg.p3.shaketicket.util.EmojiFilter;
import com.jeecg.p3.shaketicket.util.LotteryUtil;

@Controller
@RequestMapping("/shaketicket")
public class ShaketicketHomeController extends BaseController {
	public final static Logger LOG = LoggerFactory
			.getLogger(ShaketicketHomeController.class);
	@Autowired
	private WxActShaketicketHomeService homeService;
	@Autowired
	private WxActShaketicketRecordService recordService;
	@Autowired
	private WxActShaketicketAwardService awardService;
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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOG.info(request, "toIndex parameter WeixinDto={}.",
				new Object[] { weixinDto });
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();

		VelocityContext velocityContext = new VelocityContext();
		String viewName = "shaketicket/default/vm/index.vm";
		try {
			if (weixinDto.getOpenid() != null) {
				// 得到昵称并进行过滤
				String nickname = WeiXinHttpUtil.getNickName(
						weixinDto.getOpenid(), jwid);
				weixinDto.setNickname(EmojiFilter.filterNickName(nickname));
			}
			// 参数验证
			validateBargainDtoParam(weixinDto);
			// 获取活动信息
			WxActShaketicketHome shaketicket = homeService
					.queryById(actId);
			if (!jwid.equals(shaketicket.getJwid())) {
				throw new ShaketicketHomeException(
						ShaketicketHomeExceptionEnum.DATA_NOT_EXIST_ERROR,
						"活动不属于该微信公众号");
			}			
			if("0".equals(shaketicket.getActiveFlag())){
				throw new ShaketicketHomeException(ShaketicketHomeExceptionEnum.ACT_BARGAIN_END,"活动未激活！");
			}
			
			if (StringUtils.isNotEmpty(shaketicket.getTemplate())) {
				viewName = "shaketicket/"+shaketicket.getTemplate()+"/vm/index.vm";
			}
			if(shaketicket.getNumPerDay()==0){//每天次数设置为0，代表不限制每天抽奖次数
				velocityContext.put("perday", 0);
			}
			Date currDate = new Date();
			// 根据活动id，访问人openid查询抽奖人抽奖次数和每日抽奖次数
			Map<String, Integer> countMap = recordService
					.getRecordCountByActIdAndOpenid(weixinDto.getActId(),weixinDto.getOpenid(), currDate);		
			velocityContext.put("weixinDto", weixinDto);
			velocityContext.put("shaketicket", shaketicket);
			int count= ((Number)countMap.get("count")).intValue();
			int countday= ((Number)countMap.get("countday")).intValue();
			velocityContext.put("count", count);//累计抽奖次数
			velocityContext.put("countday", countday);//每日抽奖次数
			String url = request.getRequestURL() + "?"
					+ request.getQueryString();
			if (url.indexOf("#") != -1) {
				url = url.substring(0, url.indexOf("#"));
			}

			System.out.println("--------------当前访问PageUrl---------------："
					+ url);
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl", shaketicket.getHdurl());
			velocityContext.put("appId", appid);
			velocityContext.put("signature",
					WeiXinHttpUtil.getSignature(request, jwid));

		} catch (ShaketicketHomeException e) {
			LOG.error("toIndex error:{}", e.getMessage());
			viewName = "shaketicket/default/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("toIndex error:{}", e);
			viewName = "shaketicket/default/vm/error.vm";
			velocityContext.put("errCode",
					ShaketicketHomeExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					ShaketicketHomeExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response, viewName, velocityContext);
	}

	/**
	 * 摇奖
	 * 
	 * @return
	 */
	@RequestMapping(value = "/shakeTicket", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public AjaxJson shakeTicket(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "shakeTicket parameter WeixinDto={}.",
				new Object[] { weixinDto });
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String appid = weixinDto.getAppid();
		String actId = weixinDto.getActId();
		try {

			// 参数验证
			validateBargainDtoParam(weixinDto);
			if (weixinDto.getOpenid() != null) {
				String nickname = WeiXinHttpUtil.getNickName(
						weixinDto.getOpenid(), jwid);
				weixinDto.setNickname(EmojiFilter.filterNickName(nickname));
			}
			// 获取活动信息
			WxActShaketicketHome shaketicket = homeService
					.queryById(weixinDto.getActId());
			if("1".equals(shaketicket.getFoucsUserCanJoin())){//如果活动设置了需要关注用户才能参加	
				//未关注
				 if("0".equals(weixinDto.getSubscribe())){
					 j.setSuccess(false);
						j.setObj("isNotFoucs");
						return j;
				 }
			 }
			if("1".equals(shaketicket.getBindingMobileCanJoin())){//如果活动设置了需要绑定手机号才能参加				
				// 获取绑定手机号
				String bindPhone = getBindPhone(weixinDto.getOpenid(),jwid);
				// 判断是否绑定了手机号
				if (StringUtils.isEmpty(bindPhone)) {
					j.setSuccess(false);
					j.setObj("isNotBind");
					return j;
				}
			}
			Date currDate = new Date();
			// 根据活动id，访问人openid查询抽奖人抽奖次数和每日抽奖次数
			Map<String, Integer> countMap = recordService
					.getRecordCountByActIdAndOpenid(weixinDto.getActId(),weixinDto.getOpenid(), currDate);	
			int count= ((Number)countMap.get("count")).intValue();
			int countday= ((Number)countMap.get("countday")).intValue();
			int wincount= ((Number)countMap.get("wincount")).intValue();
			// 判断总抽奖次数是否用完
			if (count>= shaketicket.getCount()) {
				throw new ShaketicketHomeException(
						ShaketicketHomeExceptionEnum.DATA_EXIST_ERROR,systemActTxtService.queryActTxtByCode(
								"controller.exception.nocount",
								weixinDto.getActId()));
			}
			if(shaketicket.getNumPerDay()!= 0){	//每天次数设置为0，代表不限制每天抽奖次数，如果不等于0代表限制了每天抽奖次数	
				if (countday>= shaketicket
						.getNumPerDay()) {
					throw new ShaketicketHomeException(
							ShaketicketHomeExceptionEnum.DATA_EXIST_ERROR,systemActTxtService.queryActTxtByCode(
									"controller.exception.nownocount",
									weixinDto.getActId()));
				}
			}
			//生成用户的抽奖记录
			WxActShaketicketRecord shaketicketRecord = new WxActShaketicketRecord();
			shaketicketRecord.setDrawStatus("0");//初始化为未中奖
			//为用户抽取活动奖品
			//得到有剩余的活动奖品
			List<WxActShaketicketAward> awards = awardService.queryRemainAwardsByActId(weixinDto.getActId());
			if("0".equals(shaketicket.getWinCanJoin())){//活动设置为中奖可继续参与	
				//得到各奖品的概率列表
				List<Double> orignalRates = new ArrayList<Double>(awards.size());
				for (WxActShaketicketAward award : awards) {
					orignalRates.add(award.getProbability());
				}
				//根据概率产生奖品
				int index = LotteryUtil.lottery(orignalRates);					
				if (index>=0) {//中奖啦
					WxActShaketicketAward award	= awards.get(index);
					shaketicketRecord.setAwardId(award.getId());
					shaketicketRecord.setDrawStatus("1");//设置为已中奖
				}
			}else{//活动设置为中奖不可继续参与，即一旦中奖不可继续参与	
				if (wincount==0) {//中将次数为0，表示没有中过奖。可继续正常参与抽奖
					//得到各奖品的概率列表
					List<Double> orignalRates = new ArrayList<Double>(awards.size());
					for (WxActShaketicketAward award : awards) {
						orignalRates.add(award.getProbability());
					}
					//根据概率产生奖品
					int index = LotteryUtil.lottery(orignalRates);					
					if (index>=0) {//中奖啦
						WxActShaketicketAward award	= awards.get(index);
						shaketicketRecord.setAwardId(award.getId());
						shaketicketRecord.setDrawStatus("1");//设置为已中奖
					}
				}else{
					throw new ShaketicketHomeException(
							ShaketicketHomeExceptionEnum.DATA_EXIST_ERROR,systemActTxtService.queryActTxtByCode(
									"controller.exception.winnotcanjoin",
									weixinDto.getActId()));
				}
			}
			WxActShaketicketAward award = recordService.creatRecordAndReturnAward(shaketicketRecord,weixinDto);
			j.setSuccess(true);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("shaketicketRecord",shaketicketRecord);
			map.put("shaketicketAward",award);
			j.setAttributes(map);	
		} catch (ShaketicketHomeException e) {
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
	 * 我的卡券
	 * 
	 * @return
	 */
	    @RequestMapping(value = "/getMyAwards", method = { RequestMethod.GET,
			RequestMethod.POST })
		@ResponseBody
		public AjaxJson getMyAwards(@ModelAttribute WeixinDto weixinDto,
					HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LOG.info(request, "getMyAwards parameter WeixinDto={}.",
				new Object[] { weixinDto });
		try {
			// 我的中奖记录
			List<WxActShaketicketRecord> recordList = recordService.queryMyAwardsRecordByOpenidAndActid(weixinDto.getOpenid(), weixinDto.getActId());
			//卡券参数
			Map<String,String> dataMap = new HashMap<String, String>();
			populationMap(dataMap,recordList.get(0),weixinDto.getJwid());
			
			dataMap.put("record_id", recordList.get(0).getId());
			j.setObj(dataMap);
			// ====================================================================================================

			//j.setObj(recordList.get(0));
			j.setSuccess(true);
		} catch (ShaketicketHomeException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("bargain error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("获取失败!");
			LOG.error("bargain error:{}", e.getMessage());
		}
		return j;
	}
		private void populationMap(Map<String,String> dataMap,WxActShaketicketRecord record,String jwid){
			String api_ticket = WeiXinHttpUtil.getApiticket(jwid);
			dataMap.put("api_ticket", api_ticket);
			System.out.println("api_ticket------->"+api_ticket);
			dataMap.put("timestamp", WeiXinHttpUtil.create_timestamp());
			System.out.println("timestamp------->"+dataMap.get("timestamp"));
			dataMap.put("nonce_str", WeiXinHttpUtil.create_nonce_str());
			System.out.println("nonce_str------->"+dataMap.get("nonce_str"));
			dataMap.put("card_id", record.getCardId());
			System.out.println("card_id------->"+dataMap.get("card_id"));
			dataMap.put("openid", record.getOpenid());
			System.out.println("openid------->"+dataMap.get("openid"));
//			dataMap.put("record_id", record.getId());
//			System.out.println("record_id------->"+dataMap.get("record_id"));
			signatureDataMap(dataMap);
		}
		
		private void signatureDataMap(Map<String,String> dataMap){
			List<String> list = new ArrayList<String>();
			for(Map.Entry<String,String> entry : dataMap.entrySet()){
				list.add(entry.getValue());
			}
			Collections.sort(list);
			String signatureStr = "";
			for(String str:list){
				if(StringUtils.isNotEmpty(str)){
					signatureStr+=str;
				}
			}
			String signature = DigestUtils.shaHex(signatureStr);
			System.out.println("signatureStr------->"+signatureStr);
			System.out.println("signature------->"+signature);
			dataMap.put("signature", signature);
		}
	/**
	 * 跳转到我的奖品
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toMyAwardsRecordList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void toMyAwardsRecordList(@ModelAttribute WeixinDto weixinDto,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LOG.info(request, "toMyAwardsRecordList parameter WeixinDto={}.",
				new Object[] { weixinDto });
		// 装载微信所需参数
		String jwid = weixinDto.getJwid();
		String actId = weixinDto.getActId();
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "shaketicket/default/vm/myprizes.vm";
		try {
			// 参数验证
			validateBargainDtoParam(weixinDto);
			// 获取活动信息
			WxActShaketicketHome shaketicket = homeService
					.queryById(actId);		
			velocityContext.put("weixinDto", weixinDto);
			velocityContext.put("shaketicket", shaketicket);
			// 我的中奖记录
			List<WxActShaketicketRecord> recordList = recordService.queryMyAwardsRecordByOpenidAndActid(weixinDto.getOpenid(), weixinDto.getActId());
			velocityContext.put("record", recordList.get(0));
			String url = request.getRequestURL() + "?"
					+ request.getQueryString();
			if (url.indexOf("#") != -1) {
				url = url.substring(0, url.indexOf("#"));
			}
			System.out.println("--------------当前访问PageUrl---------------："
					+ url);
			velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			velocityContext.put("hdUrl", shaketicket.getHdurl());
			velocityContext.put("signature",
					WeiXinHttpUtil.getSignature(request, jwid));
		} catch (ShaketicketHomeException e) {
			LOG.error("toMyAwardsRecordList error:{}", e.getMessage());
			viewName = "shaketicket/vm/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			LOG.error("toMyAwardsRecordList error:{}", e);
			viewName = "shaketicket/default/vm/error.vm";
			velocityContext.put("errCode",
					ShaketicketHomeExceptionEnum.SYS_ERROR.getErrCode());
			velocityContext.put("errMsg",
					ShaketicketHomeExceptionEnum.SYS_ERROR.getErrChineseMsg());
		}
		ViewVelocity.view(request,response, viewName, velocityContext);
	}
	
	/**
	 * 卡券发放回调
	 * @return
	 */
	@RequestMapping(value = "/addCardCallback",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson addCardCallback(@ModelAttribute WxActShaketicketRecord record,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		LOG.info(request, "addCardCallback parameter wxActShakecouponRecord={}.", new Object[]{record});
		try {
			//参数验证
			if(StringUtils.isEmpty(record.getId())){
				 throw new ShaketicketHomeException(ShaketicketHomeExceptionEnum.ARGUMENT_ERROR,"获奖记录ID不能为空");
			 }
			record.setReceiveStatus("1");//已领取
			record.setReceiveTime(new Date());
			recordService.doEdit(record);
		} catch (ShaketicketHomeException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("addCardCallback error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("优惠券兑换回调失败");
			e.printStackTrace();
			LOG.error("addCardCallback error:{}", e.getMessage());
		}
		return j;
	}
	private void validateBargainDtoParam(WeixinDto weixinDto) {
		if (StringUtils.isEmpty(weixinDto.getActId())) {
			throw new ShaketicketHomeException(
					ShaketicketHomeExceptionEnum.ARGUMENT_ERROR, "活动ID不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getOpenid())) {
			throw new ShaketicketHomeException(
					ShaketicketHomeExceptionEnum.ARGUMENT_ERROR,
					"参与人openid不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getSubscribe())) {
			throw new ShaketicketHomeException(
					ShaketicketHomeExceptionEnum.ARGUMENT_ERROR, "关注状态不能为空");
		}
		if (StringUtils.isEmpty(weixinDto.getJwid())) {
			throw new ShaketicketHomeException(
					ShaketicketHomeExceptionEnum.ARGUMENT_ERROR, "微信原始id不能为空");
		}
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
}
