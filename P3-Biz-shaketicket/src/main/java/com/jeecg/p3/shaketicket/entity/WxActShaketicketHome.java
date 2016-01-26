package com.jeecg.p3.shaketicket.entity;

import java.util.List;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActShaketicketHome:九宫格活动表<br>
 * @author pituo
 * @since：2015年12月22日 19时03分50秒 星期二 
 * @version:1.0
 */
public class WxActShaketicketHome implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *键主	 */	private String id;	/**	 *活动名称	 */	private String actName;	/**	 *启用状态（0：停用；1：激活）	 */	private String activeFlag;	/**	 *模版简称	 */	private String template;	/**	 *抽奖次数	 */	private Integer count;	/**	 *每日抽奖次数	 */	private Integer numPerDay;	/**	 *入口地址	 */	private String hdurl;	/**	 *是否关注可参加 0否1是	 */	private String foucsUserCanJoin;	/**	 *是否绑定手机可参加 0否1是	 */	private String bindingMobileCanJoin;	/**	 *是否中奖可参与 0：中奖可继续参与 1:中奖不可参与	 */	private String winCanJoin;	/**	 *微信原始id	 */	private String jwid;	/**	 *微信原始id	 */	private String jwidName;	/**	 *所属项目编码	 */	private String projectCode;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActName() {	    return this.actName;	}	public void setActName(String actName) {	    this.actName=actName;	}	public String getActiveFlag() {	    return this.activeFlag;	}	public void setActiveFlag(String activeFlag) {	    this.activeFlag=activeFlag;	}		
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Integer getCount() {	    return this.count;	}	public void setCount(Integer count) {	    this.count=count;	}	public Integer getNumPerDay() {	    return this.numPerDay;	}	public void setNumPerDay(Integer numPerDay) {	    this.numPerDay=numPerDay;	}	public String getHdurl() {	    return this.hdurl;	}	public void setHdurl(String hdurl) {	    this.hdurl=hdurl;	}	public String getFoucsUserCanJoin() {	    return this.foucsUserCanJoin;	}	public void setFoucsUserCanJoin(String foucsUserCanJoin) {	    this.foucsUserCanJoin=foucsUserCanJoin;	}	public String getBindingMobileCanJoin() {	    return this.bindingMobileCanJoin;	}	public void setBindingMobileCanJoin(String bindingMobileCanJoin) {	    this.bindingMobileCanJoin=bindingMobileCanJoin;	}	public String getWinCanJoin() {	    return this.winCanJoin;	}	public void setWinCanJoin(String winCanJoin) {	    this.winCanJoin=winCanJoin;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getProjectCode() {	    return this.projectCode;	}	public void setProjectCode(String projectCode) {	    this.projectCode=projectCode;	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	
	private List<WxActShaketicketConfig> awarsList;
	public List<WxActShaketicketConfig> getAwarsList() {
		return awarsList;
	}
	public void setAwarsList(List<WxActShaketicketConfig> awarsList) {
		this.awarsList = awarsList;
	}

	
}

