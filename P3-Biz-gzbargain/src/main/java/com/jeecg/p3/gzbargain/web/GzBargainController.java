package com.jeecg.p3.gzbargain.web;

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
import org.jeecgframework.p3.core.common.utils.Constants;
import org.jeecgframework.p3.core.common.utils.DateUtil;
import org.jeecgframework.p3.core.common.utils.RandomUtils;
import org.jeecgframework.p3.core.common.utils.StringUtil;
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

import com.jeecg.p3.gzbargain.entity.GzWxActBargain;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainAwards;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRecord;
import com.jeecg.p3.gzbargain.entity.GzWxActBargainRegistration;
import com.jeecg.p3.gzbargain.exception.GzbargainException;
import com.jeecg.p3.gzbargain.exception.GzbargainExceptionEnum;
import com.jeecg.p3.gzbargain.service.GzWxActBargainAwardsService;
import com.jeecg.p3.gzbargain.service.GzWxActBargainRecordService;
import com.jeecg.p3.gzbargain.service.GzWxActBargainRegistrationService;
import com.jeecg.p3.gzbargain.service.GzWxActBargainService;
import com.jeecg.p3.gzbargain.util.EmojiFilter;

 /**
 * 描述：砍价活动
 * @author junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/gzbargain")
public class GzBargainController extends BaseController{
	
	  public final static Logger LOG = LoggerFactory.getLogger(GzBargainController.class);
	  @Autowired
	  private GzWxActBargainService gzWxActBargainService;
	  @Autowired
	  private GzWxActBargainRecordService gzWxActBargainRecordService;
	  @Autowired
	  private GzWxActBargainAwardsService gzWxActBargainAwardsService;
	  @Autowired
	  private GzWxActBargainRegistrationService gzWxActBargainRegistrationService;
	  
	 /**
	  * 跳转到活动首页
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping(value = "/toIndex",method ={RequestMethod.GET, RequestMethod.POST})
	 public void toIndex(@ModelAttribute WeixinDto weixinDto,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception{
		 LOG.info(request, "toIndex parameter WeixinDto={}.", new Object[]{weixinDto});
		 
		 //====================================================================================================
		 //装载微信所需参数
		 String jwid = weixinDto.getJwid();
		 String appid = weixinDto.getAppid();
		 String actId = weixinDto.getActId();
		//====================================================================================================
		 
		 //update-begin-----author:scott---------date:21050809------for:解码昵称------------------
		 if(weixinDto.getFxOpenid()!=null){
			 String nickname = WeiXinHttpUtil.getNickName(weixinDto.getFxOpenid(),jwid);
			 weixinDto.setFxNickname(EmojiFilter.filterNickName(nickname));
		 }
		 if(weixinDto.getOpenid()!=null){
			 String nickname =WeiXinHttpUtil.getNickName(weixinDto.getOpenid(),jwid);
			 weixinDto.setNickname(EmojiFilter.filterNickName(nickname));
		 }
		 //update-begin-----author:scott---------date:21050809------for:解码昵称------------------
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = null;
		 try {
			 //参数验证
			 validateWeixinDtoParam(weixinDto);
			 //获取活动信息
			 GzWxActBargain gzWxActBargain = gzWxActBargainService.queryWxActBargain(weixinDto.getActId());
			 if(gzWxActBargain==null){
				 throw new GzbargainException(GzbargainExceptionEnum.DATA_NOT_EXIST_ERROR,"活动不存在");
			 }
			 if(!weixinDto.getJwid().equals(gzWxActBargain.getJwid())){
				 throw new GzbargainException(GzbargainExceptionEnum.DATA_NOT_EXIST_ERROR,"活动不属于该微信公众号");
			 }
			 velocityContext.put("bargain", gzWxActBargain);
			 //有效期内可参与  
			 Date currDate = new Date();
			 if(currDate.before(gzWxActBargain.getBegainTime())){
				  String begainTime = DateUtil.convertToShowTime(gzWxActBargain.getBegainTime());
				 throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_NO_START,"活动未开始,开始时间为"+begainTime+",请耐心等待！");
			 }
			 if(currDate.after(gzWxActBargain.getEndTime())){
				 velocityContext.put("actStatus", "0");//活动结束
			 }else{
				 velocityContext.put("actStatus", "1");
			 }
			
			 List<GzWxActBargainRecord> bargainRecordList = new ArrayList<GzWxActBargainRecord>();
			 if(StringUtil.isEmpty(gzWxActBargain.getTemplate())){
				 gzWxActBargain.setTemplate("default");
			 }
			 //判断是否是分享活动
			 if(isShareAct(weixinDto)){
				 if(weixinDto.getFxOpenid().equals(weixinDto.getOpenid())){
					 viewName = "gzbargain/"+gzWxActBargain.getTemplate()+ "/vm/index.vm";
				 }else{
					 viewName = "gzbargain/"+gzWxActBargain.getTemplate()+ "/vm/fxindex.vm";
				 }
				//根据分享人openid查询分享人的报名信息
				 GzWxActBargainRegistration gzWxActBargainRegistration =  gzWxActBargainRegistrationService.queryRegistrationByOpenidAndActId(weixinDto.getFxOpenid(), weixinDto.getActId());
				 if(gzWxActBargainRegistration==null){
					 throw new GzbargainException(GzbargainExceptionEnum.DATA_NOT_EXIST_ERROR,"活动无效");
				 }
				 velocityContext.put("bargainRegistration", gzWxActBargainRegistration);
				 //查询砍价记录
				 bargainRecordList =  gzWxActBargainRecordService.queryBargainRecordListByRegistrationId(gzWxActBargainRegistration.getId());
			 }else{
				 viewName = "gzbargain/"+gzWxActBargain.getTemplate()+ "/vm/index.vm";
//				 if("0".equals(weixinDto.getSubscribe())){
//					 throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_NO_FOCUS,"非关注用户");
//				 }
				 //根据访问人openid查询访问人的报名信息 
				 GzWxActBargainRegistration gzWxActBargainRegistration =  gzWxActBargainRegistrationService.queryRegistrationByOpenidAndActId(weixinDto.getOpenid(), weixinDto.getActId());
				 if(gzWxActBargainRegistration==null){
					 gzWxActBargainRegistration = new GzWxActBargainRegistration();
					 gzWxActBargainRegistration.setId(RandomUtils.generateID());
					 gzWxActBargainRegistration.setActId(weixinDto.getActId());
					 gzWxActBargainRegistration.setOpenid(weixinDto.getOpenid());
					 gzWxActBargainRegistration.setNickname(weixinDto.getNickname());
					 gzWxActBargainRegistration.setProductName(gzWxActBargain.getProductName());
					 gzWxActBargainRegistration.setProductNewPrice(gzWxActBargain.getProductPrice());
					 gzWxActBargainRegistration.setProductPrice(gzWxActBargain.getProductPrice());
					 gzWxActBargainRegistration.setCreateTime(new Date());
					 gzWxActBargainRegistration.setJwid(gzWxActBargain.getJwid());
					 gzWxActBargainRegistrationService.add(gzWxActBargainRegistration);
				 }else{
					//查询砍价记录
					bargainRecordList =  gzWxActBargainRecordService.queryBargainRecordListByRegistrationId(gzWxActBargainRegistration.getId());
				 }
				 velocityContext.put("bargainRegistration", gzWxActBargainRegistration);
			 }
			 velocityContext.put("recordListCount", bargainRecordList==null?0:bargainRecordList.size());
			 velocityContext.put("recordList", bargainRecordList);
			 velocityContext.put("weixinDto", weixinDto);
			 
			 
			//update-begin-----author:scott---------date:21050809------for:微信分享参数------------------
			 velocityContext.put("nonceStr", WeiXinHttpUtil.nonceStr);
			 velocityContext.put("timestamp", WeiXinHttpUtil.timestamp);
			 
			 velocityContext.put("hdUrl",gzWxActBargain.getHdurl()); //获取分享URL
			 velocityContext.put("appId", appid);
			 velocityContext.put("signature", WeiXinHttpUtil.getSignature(request,jwid));
			//update-begin-----author:scott---------date:21050809------for:微信分享参数------------------
			 ViewVelocity.view(request, response, viewName, velocityContext);
		}catch (GzbargainException e) {
			LOG.error("toIndex error:{}", e.getMessage());
			viewName = "gzbargain/vm/default/error.vm";
			velocityContext.put("errCode", e.getDefineCode());
			velocityContext.put("errMsg", e.getMessage());
		} catch (Exception e) {
			 LOG.error("toIndex error:{}", e);
			 viewName = "gzbargain/vm/default/error.vm";
			 velocityContext.put("errCode", GzbargainExceptionEnum.SYS_ERROR.getErrCode());
			 velocityContext.put("errMsg", GzbargainExceptionEnum.SYS_ERROR.getErrChineseMsg());
		} 
	 }
	 
  
	 private void validateWeixinDtoParam(WeixinDto weixinDto){
		 if(StringUtils.isEmpty(weixinDto.getActId())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"活动ID不能为空");
		 }
		 if(StringUtils.isEmpty(weixinDto.getOpenid())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"参与人openid不能为空");
		 }
		 if(StringUtils.isEmpty(weixinDto.getJwid())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"微信ID不能为空");
		 }
		 if(StringUtils.isEmpty(weixinDto.getSubscribe())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"关注状态不能为空");
		 }
	 }
	 
	 private boolean isShareAct(WeixinDto weixinDto){
		 boolean flag = false;
		 if(StringUtils.isNotEmpty(weixinDto.getFxOpenid())){
			 flag = true;
		 }
		 return flag;
	 }
 
	/**
	 * 去砍价
	 * @return
	 */
	@RequestMapping(value = "/goBargain",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson goBargain(@ModelAttribute GzWxActBargainRecord gzWxActBargainRecord,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		LOG.info(request, "goBargain parameter gzWxActBargainRecord={}.", new Object[]{gzWxActBargainRecord});
		try {
			//参数验证
			validateGoBargainRecordParam(gzWxActBargainRecord);
			// 判断是否已经领奖了
			GzWxActBargainRegistration bargainRegistration = gzWxActBargainRegistrationService.queryBargainRegistrationById(gzWxActBargainRecord.getRegistrationId());
			if (Constants.PRIZE_RECEIVED.equals(bargainRegistration
					.getAwardsStatus())) {
				j.setObj("received");//已领奖
				j.setSuccess(false);
				return j;
			}
			//判断是否已经砍价
			List<GzWxActBargainRecord> bargainRecordList = gzWxActBargainRecordService.queryBargainRecordListByRegistrationIdAndOpenid(gzWxActBargainRecord.getRegistrationId(), gzWxActBargainRecord.getOpenid());
			if(bargainRecordList!=null&&bargainRecordList.size()>0){
				j.setSuccess(false);
				j.setObj("bargained");//已砍价
				return j;
			}
			//判断是否已砍至底价
			GzWxActBargain gzWxActBargain = gzWxActBargainService.queryWxActBargain(bargainRegistration.getActId());
			if(bargainRegistration.getProductNewPrice().compareTo(gzWxActBargain.getCutMinPrice())<=0){
				j.setSuccess(false);
				j.setObj("cutMin");//已砍至底价
				return j;
			}
		} catch (GzbargainException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("gzbargain error:{}", e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("砍价异常!");
			LOG.error("gzbargain error:{}", e.getMessage());
		}
		return j;
	}
	
	private void validateGoBargainRecordParam(GzWxActBargainRecord gzWxActBargainRecord){
		 if(StringUtils.isEmpty(gzWxActBargainRecord.getRegistrationId())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"报名ID不能为空");
		 }
		 if(StringUtils.isEmpty(gzWxActBargainRecord.getOpenid())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"砍价人openid不能为空");
		 }
	 }
 
 	/**
	 * 砍价
	 * @return
	 */
	@RequestMapping(value = "/bargain",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson bargain(@ModelAttribute GzWxActBargainRecord gzWxActBargainRecord,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		LOG.info(request, "bargain parameter gzWxActBargainRecord={}.", new Object[]{gzWxActBargainRecord});
		try {
			//参数验证
			validateBargainRecordParam(gzWxActBargainRecord);
						
			//验证码验证
			if (!gzWxActBargainRecord.getRandCode().equalsIgnoreCase(String.valueOf(request.getSession().getAttribute("randCode")))){
				throw new GzbargainException(GzbargainExceptionEnum.VALIDATE_RANDCODE_ERROR,"验证码输入错误");
			}
			
			GzWxActBargainRegistration bargainRegistration = gzWxActBargainRegistrationService.queryBargainRegistrationById(gzWxActBargainRecord.getRegistrationId());
			// 判断是否已经领奖了
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if (Constants.PRIZE_RECEIVED.equals(bargainRegistration
					.getAwardsStatus())) {
				dataMap.put("cutStatus", "received");
				j.setAttributes(dataMap);
				return j;
			}
			
			//判断是否已经砍价
			List<GzWxActBargainRecord> bargainRecordList = gzWxActBargainRecordService.queryBargainRecordListByRegistrationIdAndOpenid(gzWxActBargainRecord.getRegistrationId(), gzWxActBargainRecord.getOpenid());
			if(bargainRecordList!=null&&bargainRecordList.size()>0){
				dataMap.put("cutStatus", "bargained");
				j.setAttributes(dataMap);
				return j;
			}
			String cutCount = gzWxActBargainRecordService.bargain(bargainRegistration,gzWxActBargainRecord);
			dataMap.put("cutStatus", cutCount);
			dataMap.put("cutPrice", gzWxActBargainRecord.getCutPrice());
			j.setAttributes(dataMap);
		} catch (GzbargainException e) {
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
	
	private void validateBargainRecordParam(GzWxActBargainRecord gzWxActBargainRecord){
		 if(StringUtils.isEmpty(gzWxActBargainRecord.getRegistrationId())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"报名ID不能为空");
		 }
		 if(StringUtils.isEmpty(gzWxActBargainRecord.getOpenid())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"砍价人openid不能为空");
		 }
//		 if(StringUtils.isEmpty(gzWxActBargainRecord.getNickname())){
//			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"砍价人昵称不能为空");
//		 }
		 if(StringUtils.isEmpty(gzWxActBargainRecord.getSubscribe())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"关注状态不能为空");
		 }
//		 if("0".equals(gzWxActBargainRecord.getSubscribe())){
//			 throw new GzbargainException(GzbargainExceptionEnum.ACT_BARGAIN_NO_FOCUS,"砍价人非关注用户");
//		 }
		 if(StringUtils.isEmpty(gzWxActBargainRecord.getRandCode())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"验证码不能为空");
		 }
	 }
	 
	/**
	 * 去兑奖
	 * @return
	 */
	@RequestMapping(value = "/goReceivePrize",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson goReceivePrize(@ModelAttribute GzWxActBargainRegistration bargainRegistration,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		LOG.info(request, "goReceivePrize parameter bargainRegistration={}.", new Object[]{bargainRegistration});
		try {
			//参数验证
			validateGoReceivePrizeParam(bargainRegistration);
			//判断是否已领取奖品
			List<GzWxActBargainAwards> bargainAwardsList = gzWxActBargainAwardsService.queryBargainAwardsByActIdAndOpenid(bargainRegistration.getActId(), bargainRegistration.getOpenid());
			if(bargainAwardsList!=null&&bargainAwardsList.size()>0){
				GzWxActBargainAwards gzWxActBargainAwards = bargainAwardsList.get(0);
				j.setObj(gzWxActBargainAwards);
				
			}else{
				//获取活动信息
				GzWxActBargain gzWxActBargain = gzWxActBargainService.queryWxActBargain(bargainRegistration.getActId());
				bargainRegistration =  gzWxActBargainRegistrationService.queryRegistrationByOpenidAndActId(bargainRegistration.getOpenid(), bargainRegistration.getActId());
				//判断是否已砍至底价
				if(bargainRegistration.getProductNewPrice().compareTo(gzWxActBargain.getCutMinPrice())<=0){
					GzWxActBargainAwards gzWxActBargainAwards = new GzWxActBargainAwards();
					Integer maxAwardsSeq = gzWxActBargainAwardsService.getMaxAwardsSeq(bargainRegistration.getActId());
					Integer nextAwardsSeq = maxAwardsSeq+1;
										
					if(nextAwardsSeq>gzWxActBargain.getProductNum()){
						j.setSuccess(false);
						j.setObj("noRecevied");
						return j;
					}else{
						gzWxActBargainAwards.setAwardsSeq(nextAwardsSeq);
						gzWxActBargainAwards.setActId(bargainRegistration.getActId());
						gzWxActBargainAwards.setOpenid(bargainRegistration.getOpenid());
						gzWxActBargainAwards.setNickname(bargainRegistration.getNickname());
						gzWxActBargainAwards.setJwid(bargainRegistration.getJwid());
						gzWxActBargainAwardsService.createAwards(gzWxActBargainAwards);
						j.setObj(gzWxActBargainAwards);
					}
				}else{
					j.setSuccess(false);
					j.setObj("notCutMin");
					return j;
				}
			}
		}catch (GzbargainException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			LOG.error("goReceivePrize error:{}", e.getMessage());
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("奖品领取失败！");
			LOG.error("goReceivePrize error:{}", e.getMessage());
		}
		return j;
	}
	
	private void validateGoReceivePrizeParam(GzWxActBargainRegistration bargainRegistration){
		if(StringUtils.isEmpty(bargainRegistration.getActId())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"活动ID不能为空");
		 }
		 if(StringUtils.isEmpty(bargainRegistration.getOpenid())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"兑奖人openid不能为空");
		 }
//		 if(StringUtils.isEmpty(gzWxActBargainAwards.getNickname())){
//			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"兑奖人昵称不能为空");
//		 }
		 if(StringUtils.isEmpty(bargainRegistration.getSubscribe())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"关注状态不能为空");
		 }
	 }
	
	/**
	 * 兑奖
	 * @return
	 */
	@RequestMapping(value = "/receivePrize",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson receivePrize(@ModelAttribute GzWxActBargainAwards gzWxActBargainAwards,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		LOG.info(request, "receivePrize parameter gzWxActBargainAwards={}.", new Object[]{gzWxActBargainAwards});
		try {
			//参数验证
			validateReceivePrizeParam(gzWxActBargainAwards);
			
			gzWxActBargainAwardsService.updateAwards(gzWxActBargainAwards);
		} catch (GzbargainException e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("兑奖失败");
		}
		return j;
	}
	
	private void validateReceivePrizeParam(GzWxActBargainAwards gzWxActBargainAwards){
		 if(StringUtils.isEmpty(gzWxActBargainAwards.getId())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"兑奖ID不能为空");
		 }
		 if(StringUtils.isEmpty(gzWxActBargainAwards.getRealName())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"用户真实姓名不能为空");
		 }
		 if(StringUtils.isEmpty(gzWxActBargainAwards.getMobile())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"用户手机号不能为空");
		 }
		 if(StringUtils.isEmpty(gzWxActBargainAwards.getAddress())){
			 throw new GzbargainException(GzbargainExceptionEnum.ARGUMENT_ERROR,"用户详细地址不能为空");
		 }
	 }
}

