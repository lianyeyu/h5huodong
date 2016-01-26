package com.jeecg.p3.jiugongge.entity;

import java.util.Date;

import org.jeecgframework.p3.core.utils.persistence.Entity;

import com.jeecg.p3.jiugongge.annotation.Excel;

/**
 * 描述：</b>WxActJiugonggeRecord:抽奖记录<br>
 * @author junfeng.zhou
 * @since：2015年11月20日 15时37分02秒 星期五 
 * @version:1.0
 */
public class WxActJiugonggeRecord implements Entity<String> {
	private static final long serialVersionUID = 1L;
	
	/**
	 *记录id
	 */
	private String id;
	/**
	 *
	 */
	private String actId;
	/**
	 *openid
	 */
	@Excel(exportName="openid", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String openid;
	/**
	 *昵称
	 */
	@Excel(exportName="昵称", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String nickname;
	/**
	 *中奖时间
	 */
	@Excel(exportName="中奖时间", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private Date recieveTime;
	/**
	 *奖项
	 */
	
	private String awardsId;
	/**
	 *收货人姓名
	 */
	@Excel(exportName="收货人姓名", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String realname;
	/**
	 *手机号
	 */
	@Excel(exportName="手机号", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String phone;
	/**
	 *地址
	 */
	@Excel(exportName="地址", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String address;
	/**
	 *对应微信平台原始id
	 */
	private String jwid;
	private String jwidName;
	private String actName;
	private Integer seq;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 *奖项名称
	 */
	@Excel(exportName="奖项", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)
	private String awardsName;
	public String getId() {
	    return this.id;
	}
	public void setId(String id) {
	    this.id=id;
	}
	public String getActId() {
	    return this.actId;
	}
	public void setActId(String actId) {
	    this.actId=actId;
	}
	public String getOpenid() {
	    return this.openid;
	}
	public void setOpenid(String openid) {
	    this.openid=openid;
	}
	public String getNickname() {
	    return this.nickname;
	}
	public void setNickname(String nickname) {
	    this.nickname=nickname;
	}
	public Date getRecieveTime() {
	    return this.recieveTime;
	}
	public void setRecieveTime(Date recieveTime) {
	    this.recieveTime=recieveTime;
	}
	public String getAwardsId() {
	    return this.awardsId;
	}
	public void setAwardsId(String awardsId) {
	    this.awardsId=awardsId;
	}
	public String getRealname() {
	    return this.realname;
	}
	public void setRealname(String realname) {
	    this.realname=realname;
	}
	public String getPhone() {
	    return this.phone;
	}
	public void setPhone(String phone) {
	    this.phone=phone;
	}
	public String getAddress() {
	    return this.address;
	}
	public void setAddress(String address) {
	    this.address=address;
	}
	public String getJwid() {
	    return this.jwid;
	}
	public void setJwid(String jwid) {
	    this.jwid=jwid;
	}
	public String getAwardsName() {
		return awardsName;
	}
	public void setAwardsName(String awardsName) {
		this.awardsName = awardsName;
	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	
	
}

