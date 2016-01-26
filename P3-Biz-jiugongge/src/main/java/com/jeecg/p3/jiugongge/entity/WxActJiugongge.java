package com.jeecg.p3.jiugongge.entity;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActJiugongge:配置<br>
 * @author junfeng.zhou
 * @since：2015年11月16日 11时07分11秒 星期一 
 * @version:1.0
 */
public class WxActJiugongge implements Entity<String> {
	private static final long serialVersionUID = 1L;
private List<WxActJiugonggeRelation> awarsList;	/**	 *键主	 */	private String id;	/**	 *活动名称	 */	private String title;	/**	 *活动描述	 */	private String description;	/**	 *开始时间	 */	private Date starttime;	/**	 *结束时间	 */	private Date endtime;	/**	 *背景图	 */	private String banner;	/**	 *抽奖次数	 */	private Integer count;	/**	 *活动链接	 */	private String hdurl;	/**	 *是否关注可参加	 */	private String foucsUserCanJoin;	/**	 *是否绑定手机可参加	 */	private String bindingMobileCanJoin;	/**	 *每日抽奖次数	 */	private Integer numPerDay;	/**	 *是否中奖可参与 0：中奖可继续参与 1:中奖不可参与	 */	private String prizeStatus;	/**	 *微信原始id	 */	private String jwid;	/**	 *微信原始id	 */	private String jwidName;	/**	 *活动项目编码	 */	private String projectCode;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public String getDescription() {	    return this.description;	}	public void setDescription(String description) {	    this.description=description;	}	public Date getStarttime() {	    return this.starttime;	}	public void setStarttime(Date starttime) {	    this.starttime=starttime;	}	public Date getEndtime() {	    return this.endtime;	}	public void setEndtime(Date endtime) {	    this.endtime=endtime;	}	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getHdurl() {
		return hdurl;
	}
	public void setHdurl(String hdurl) {
		this.hdurl = hdurl;
	}
	public String getFoucsUserCanJoin() {	    return this.foucsUserCanJoin;	}	public void setFoucsUserCanJoin(String foucsUserCanJoin) {	    this.foucsUserCanJoin=foucsUserCanJoin;	}	public String getBindingMobileCanJoin() {	    return this.bindingMobileCanJoin;	}	public void setBindingMobileCanJoin(String bindingMobileCanJoin) {	    this.bindingMobileCanJoin=bindingMobileCanJoin;	}	public Integer getNumPerDay() {	    return this.numPerDay;	}	public void setNumPerDay(Integer numPerDay) {	    this.numPerDay=numPerDay;	}		public String getPrizeStatus() {
		return prizeStatus;
	}
	public void setPrizeStatus(String prizeStatus) {
		this.prizeStatus = prizeStatus;
	}
	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	public List<WxActJiugonggeRelation> getAwarsList() {
		return awarsList;
	}
	public void setAwarsList(List<WxActJiugonggeRelation> awarsList) {
		this.awarsList = awarsList;
	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	
}

