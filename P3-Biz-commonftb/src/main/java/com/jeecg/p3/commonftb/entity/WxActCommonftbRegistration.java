package com.jeecg.p3.commonftb.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActCommonsjRegistration:砍价报名表<br>
 * @author pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
public class WxActCommonftbRegistration implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *活动id	 */	private String actId;	/**	 *活动所属人openid	 */	private String openid;	/**	 *活动所属人昵称	 */	private String nickname;	/**	 *活动所属人头像	 */	private String head;	/**	 *产品名称	 */	private String productName;	/**	 *产品价格	 */	private BigDecimal productPrice;	/**	 *砍后价格	 */	private BigDecimal productNewPrice;	/**	 *创建时间	 */	private Date createTime;	/**	 *更新时间	 */	private Date updateTime;	/**	 *领奖状态0:未领奖;1:已领奖	 */	private String awardsStatus;	/**	 *对应微信平台原始id	 */	private String jwid;
	/**
	 *卡券密码
	 */
	private String cardPsd;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public String getProductName() {	    return this.productName;	}	public void setProductName(String productName) {	    this.productName=productName;	}	public BigDecimal getProductPrice() {	    return this.productPrice;	}	public void setProductPrice(BigDecimal productPrice) {	    this.productPrice=productPrice;	}	public BigDecimal getProductNewPrice() {	    return this.productNewPrice;	}	public void setProductNewPrice(BigDecimal productNewPrice) {	    this.productNewPrice=productNewPrice;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(Date updateTime) {	    this.updateTime=updateTime;	}	public String getAwardsStatus() {	    return this.awardsStatus;	}	public void setAwardsStatus(String awardsStatus) {	    this.awardsStatus=awardsStatus;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getCardPsd() {
		return cardPsd;
	}
	public void setCardPsd(String cardPsd) {
		this.cardPsd = cardPsd;
	}
	
	
}

