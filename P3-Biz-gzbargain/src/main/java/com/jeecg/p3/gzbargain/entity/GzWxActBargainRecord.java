package com.jeecg.p3.gzbargain.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActBargainRecord:砍价帮砍记录表<br>
 * @author junfeng.zhou
 * @since：2015年08月06日 18时46分35秒 星期四 
 * @version:1.0
 */
public class GzWxActBargainRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *记录id	 */	private String id;	/**	 *报名id	 */	private String registrationId;	/**	 *砍价人openid	 */	private String openid;	/**	 *砍价人昵称	 */	private String nickname;	/**	 *砍掉价格	 */	private BigDecimal cutPrice;	/**	 *砍后价格	 */	private BigDecimal currPrice;	/**	 *创建时间	 */	private Date createTime;
	/**
	 * 对应微信平台原始id
	 */
	private String jwid;
	
	/**
	 * 是否关注（ 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。）
	 */
	private String subscribe;
	/**
	 *验证码
	 */
	private String randCode;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getRegistrationId() {	    return this.registrationId;	}	public void setRegistrationId(String registrationId) {	    this.registrationId=registrationId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public BigDecimal getCutPrice() {	    return this.cutPrice;	}	public void setCutPrice(BigDecimal cutPrice) {	    this.cutPrice=cutPrice;	}	public BigDecimal getCurrPrice() {	    return this.currPrice;	}	public void setCurrPrice(BigDecimal currPrice) {	    this.currPrice=currPrice;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}
	public String getJwid() {
		return jwid;
	}
	public void setJwid(String jwid) {
		this.jwid = jwid;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getRandCode() {
		return randCode;
	}
	public void setRandCode(String randCode) {
		this.randCode = randCode;
	}
	@Override
	public String toString() {
		return "GzWxActBargainRecord [id=" + id + ", registrationId="
				+ registrationId + ", openid=" + openid + ", nickname="
				+ nickname + ", cutPrice=" + cutPrice + ", currPrice="
				+ currPrice + ", createTime=" + createTime + ", jwid=" + jwid
				+ ", subscribe=" + subscribe + ", randCode=" + randCode + "]";
	}
	
	
}

