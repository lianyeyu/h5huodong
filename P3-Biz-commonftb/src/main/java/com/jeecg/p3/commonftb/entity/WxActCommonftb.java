package com.jeecg.p3.commonftb.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActCommonftb:砍价子活动表<br>
 * @author pituo
 * @since：2016年01月05日 11时07分05秒 星期二 
 * @version:1.0
 */
public class WxActCommonftb implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *活动确认选择提示语	 */	private String confirm;	/**	 *子活动名称	 */	private String name;	/**	 *子活动标题图片	 */	private String binner;	/**	 *子活动规则	 */	private String actRule;	/**	 *子活动开始时间	 */	private Date begainTime;	/**	 *子 活动结束时间	 */	private Date endTime;
	
	/**
	 *是否关注用户参与 0否1是
	 */
	private String foucsUserCanJoin;
	/**
	 *是否绑定手机可参加 0否1是
	 */
	private String bindingMobileCanJoin;
	/**	 *产品数量	 */	private Integer productNum;	/**	 *产品单位	 */	private String productUnit;	/**	 *产品名称	 */	private String productName;	/**	 *产品价格	 */	private BigDecimal productPrice;	/**	 *产品图片	 */	private String productImg;	/**	 *产品剩余数量	 */	private Integer productRemainNum;	/**	 *兑奖截止日期	 */	private Date awardsEndTime;	/**	 *券开始时间	 */	private Date couponStartTime;	/**	 *券结束时间	 */	private Date couponEndTime;	/**	 *砍价最低金额	 */	private BigDecimal cutMinPrice;	/**	 *是否砍到最低价	 */	private String ifCutMin;	/**	 *卡券档位	 */	private String couponLevel;	/**	 *取整方式（0向下取整1向上取整）	 */	private String roundType;	/**	 *更新时间	 */	private Date updateTime;	/**	 *微信平台原始id	 */	private String jwid;	/**	 *微信平台原始id	 */	private String jwidName;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getActRule() {	    return this.actRule;	}	public void setActRule(String actRule) {	    this.actRule=actRule;	}	public Date getBegainTime() {	    return this.begainTime;	}	public void setBegainTime(Date begainTime) {	    this.begainTime=begainTime;	}	public Date getEndTime() {	    return this.endTime;	}	public void setEndTime(Date endTime) {	    this.endTime=endTime;	}	public Integer getProductNum() {	    return this.productNum;	}	public void setProductNum(Integer productNum) {	    this.productNum=productNum;	}	public String getProductUnit() {	    return this.productUnit;	}	public void setProductUnit(String productUnit) {	    this.productUnit=productUnit;	}	public String getProductName() {	    return this.productName;	}	public void setProductName(String productName) {	    this.productName=productName;	}	public BigDecimal getProductPrice() {	    return this.productPrice;	}	public void setProductPrice(BigDecimal productPrice) {	    this.productPrice=productPrice;	}	public String getProductImg() {	    return this.productImg;	}	public void setProductImg(String productImg) {	    this.productImg=productImg;	}	public Integer getProductRemainNum() {	    return this.productRemainNum;	}	public void setProductRemainNum(Integer productRemainNum) {	    this.productRemainNum=productRemainNum;	}	public Date getAwardsEndTime() {	    return this.awardsEndTime;	}	public void setAwardsEndTime(Date awardsEndTime) {	    this.awardsEndTime=awardsEndTime;	}	public Date getCouponStartTime() {	    return this.couponStartTime;	}	public void setCouponStartTime(Date couponStartTime) {	    this.couponStartTime=couponStartTime;	}	public Date getCouponEndTime() {	    return this.couponEndTime;	}	public void setCouponEndTime(Date couponEndTime) {	    this.couponEndTime=couponEndTime;	}	public BigDecimal getCutMinPrice() {	    return this.cutMinPrice;	}	public void setCutMinPrice(BigDecimal cutMinPrice) {	    this.cutMinPrice=cutMinPrice;	}	public String getCouponLevel() {	    return this.couponLevel;	}	public void setCouponLevel(String couponLevel) {	    this.couponLevel=couponLevel;	}	public String getRoundType() {	    return this.roundType;	}	public void setRoundType(String roundType) {	    this.roundType=roundType;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	public String getBinner() {
		return binner;
	}
	public void setBinner(String binner) {
		this.binner = binner;
	}
	public String getFoucsUserCanJoin() {
		return foucsUserCanJoin;
	}
	public void setFoucsUserCanJoin(String foucsUserCanJoin) {
		this.foucsUserCanJoin = foucsUserCanJoin;
	}
	public String getBindingMobileCanJoin() {
		return bindingMobileCanJoin;
	}
	public void setBindingMobileCanJoin(String bindingMobileCanJoin) {
		this.bindingMobileCanJoin = bindingMobileCanJoin;
	}
	public String getIfCutMin() {
		return ifCutMin;
	}
	public void setIfCutMin(String ifCutMin) {
		this.ifCutMin = ifCutMin;
	}
	
}

