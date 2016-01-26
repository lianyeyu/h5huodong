package com.jeecg.p3.commonftb.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActCommonsjRecord:砍价帮砍记录表<br>
 * @author pituo
 * @since：2015年11月30日 15时51分48秒 星期一 
 * @version:1.0
 */
public class WxActCommonftbRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *记录id	 */	private String id;	/**	 *报名id	 */	private String registrationId;	/**	 *砍价人openid	 */	private String openid;	/**	 *砍价人昵称	 */	private String nickname;	/**	 *砍掉价格	 */	private BigDecimal cutPrice;	/**	 *砍后价格	 */	private BigDecimal currPrice;	/**	 *创建时间	 */	private Date createTime;	/**	 *对应微信平台原始id	 */	private String jwid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getRegistrationId() {	    return this.registrationId;	}	public void setRegistrationId(String registrationId) {	    this.registrationId=registrationId;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public BigDecimal getCutPrice() {	    return this.cutPrice;	}	public void setCutPrice(BigDecimal cutPrice) {	    this.cutPrice=cutPrice;	}	public BigDecimal getCurrPrice() {	    return this.currPrice;	}	public void setCurrPrice(BigDecimal currPrice) {	    this.currPrice=currPrice;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
}

