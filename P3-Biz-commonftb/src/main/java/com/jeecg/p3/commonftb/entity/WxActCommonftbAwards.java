package com.jeecg.p3.commonftb.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

import com.jeecg.p3.commonftb.annotation.Excel;

/**
 * 描述：</b>WxActCommonsjAwards:领取奖品记录表<br>
 * @author pituo
 * @since：2015年11月30日 15时51分47秒 星期一 
 * @version:1.0
 */
public class WxActCommonftbAwards implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *活动id	 */	private String actId;	/**	 *奖品序号	 */	private Integer awardsSeq;	/**	 *兑奖人openid	 */
	@Excel(exportName="openid", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String openid;	/**	 *兑奖人昵称	 */
	@Excel(exportName="昵称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String nickname;	/**	 *真实姓名	 */
	@Excel(exportName="昵称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String realName;	/**	 *手机号	 */
	@Excel(exportName="手机号", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String mobile;	/**	 *详细地址	 */
	@Excel(exportName="详细地址", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String address;	/**	 *卡券id	 */
	@Excel(exportName="卡券id", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String couponId;
	/**
	 *卡券密码
	 */
	@Excel(exportName="卡券密码", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String cardPsd;	/**	 *创建时间	 */	private Date createTime;	/**	 *对应微信平台原始id	 */	private String jwid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public Integer getAwardsSeq() {	    return this.awardsSeq;	}	public void setAwardsSeq(Integer awardsSeq) {	    this.awardsSeq=awardsSeq;	}	public String getOpenid() {	    return this.openid;	}	public void setOpenid(String openid) {	    this.openid=openid;	}	public String getNickname() {	    return this.nickname;	}	public void setNickname(String nickname) {	    this.nickname=nickname;	}	public String getRealName() {	    return this.realName;	}	public void setRealName(String realName) {	    this.realName=realName;	}	public String getMobile() {	    return this.mobile;	}	public void setMobile(String mobile) {	    this.mobile=mobile;	}	public String getAddress() {	    return this.address;	}	public void setAddress(String address) {	    this.address=address;	}	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public String getCardPsd() {
		return cardPsd;
	}
	public void setCardPsd(String cardPsd) {
		this.cardPsd = cardPsd;
	}
	
}

