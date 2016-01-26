package com.jeecg.p3.gzbargain.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActBargain:砍价活动表<br>
 * @author junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
public class GzWxActBargain implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *砍价活动名称	 */	private String actName;	/**	 *活动描述	 */	private String actDetail;
	/**
	 *活动规则
	 */
	private String actRule;	/**	 *活动开始时间	 */	private Date begainTime;	/**	 * 活动结束时间	 */	private Date endTime;	/**	 *产品数量	 */	private Integer productNum;	/**	 *产品单位	 */	private String productUnit;	/**	 *产品名称	 */	private String productName;
	/**
	 *产品图片
	 */
	private String productImg;	/**	 *产品价格	 */	private BigDecimal productPrice;	/**	 *是否关注用户参与	 */	private String foucsUserCanJoin;	/**	 *展示比例	 */	private Integer showRate;	/**	 *创建时间	 */	private Date createTime;
	/**
	 *产品剩余数量
	 */
	private Integer productRemainNum;
	/**
	 *砍价最低金额
	 */
	private BigDecimal cutMinPrice;
	/**
	 *模版
	 */
	private String template;
	/**
	 *入口地址
	 */
	private String hdurl;
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public BigDecimal getCutMinPrice() {
		return cutMinPrice;
	}
	public void setCutMinPrice(BigDecimal cutMinPrice) {
		this.cutMinPrice = cutMinPrice;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getHdurl() {
		return hdurl;
	}
	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}
	/**
	 * 对应微信平台原始id
	 */
	private String jwid;
	
	/**
	 *活动项目编码
	 */
	private String projectCode;
	
	/**
	 * 微信公众号名称
	 */
	private String jwName;
	/**
	 * 第几页
	 */
	private Integer offset;
	/**
	 * 每页多少条
	 */
	private Integer limit;
	
		public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActName() {	    return this.actName;	}	public void setActName(String actName) {	    this.actName=actName;	}	public String getActDetail() {	    return this.actDetail;	}	public void setActDetail(String actDetail) {	    this.actDetail=actDetail;	}	public String getActRule() {
		return actRule;
	}
	public void setActRule(String actRule) {
		this.actRule = actRule;
	}
	public Date getBegainTime() {	    return this.begainTime;	}	public void setBegainTime(Date begainTime) {	    this.begainTime=begainTime;	}	public Date getEndTime() {	    return this.endTime;	}	public void setEndTime(Date endTime) {	    this.endTime=endTime;	}	public Integer getProductNum() {	    return this.productNum;	}	public void setProductNum(Integer productNum) {	    this.productNum=productNum;	}	public String getProductUnit() {	    return this.productUnit;	}	public void setProductUnit(String productUnit) {	    this.productUnit=productUnit;	}	public String getProductName() {	    return this.productName;	}	public void setProductName(String productName) {	    this.productName=productName;	}	public BigDecimal getProductPrice() {	    return this.productPrice;	}	public void setProductPrice(BigDecimal productPrice) {	    this.productPrice=productPrice;	}	public String getFoucsUserCanJoin() {	    return this.foucsUserCanJoin;	}	public void setFoucsUserCanJoin(String foucsUserCanJoin) {	    this.foucsUserCanJoin=foucsUserCanJoin;	}	public Integer getShowRate() {	    return this.showRate;	}	public void setShowRate(Integer showRate) {	    this.showRate=showRate;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}
	public Integer getProductRemainNum() {
		return productRemainNum;
	}
	public void setProductRemainNum(Integer productRemainNum) {
		this.productRemainNum = productRemainNum;
	}
	public String getJwid() {
		return jwid;
	}
	public void setJwid(String jwid) {
		this.jwid = jwid;
	}
	@Override
	public String toString() {
		return "GzWxActBargain [" +
				"id=" + id + "" +
				", actName=" + actName+ "" +
				", actDetail=" + actDetail + 
				", actRule=" + actRule + 
				", begainTime=" + begainTime + 
				", endTime=" + endTime + 
				", productNum=" + productNum + 
				", productUnit=" + productUnit + 
				", productName=" + productName + 
				", productImg=" + productImg + 
				", productPrice=" + productPrice + 
				", foucsUserCanJoin=" + foucsUserCanJoin + 
				", showRate="+ showRate + 
				", createTime=" + createTime+ 
				", productRemainNum=" + productRemainNum + 
				", cutMinPrice=" + cutMinPrice + 
				", template=" + template + 
				", hdurl=" + hdurl + 
				", jwid=" + jwid +
				", projectCode=" + projectCode +
				"]";
	}
	public void setJwName(String jwName) {
		this.jwName = jwName;
	}
	public String getJwName() {
		return jwName;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectCode() {
		return projectCode;
	}
	
	
}

