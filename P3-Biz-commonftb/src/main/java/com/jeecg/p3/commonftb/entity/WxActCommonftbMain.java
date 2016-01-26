package com.jeecg.p3.commonftb.entity;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActCommonftbMain:砍价主活动表<br>
 * @author pituo
 * @since：2016年01月05日 10时52分01秒 星期二 
 * @version:1.0
 */
public class WxActCommonftbMain implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *砍价活动名称	 */	private String actName;	/**	 *活动开始时间	 */	private Date begainTime;	/**	 * 活动结束时间	 */	private Date endTime;	/**	 *是否只能参加一个子活动 0否1是	 */	private String manyCanJoin;	/**	 *入口地址	 */	private String hdurl;	/**	 *模版(省份简称)	 */	private String template;	/**	 *微信公众号	 */	private String jwid;	/**	 *微信公众号	 */	private String jwidName;	/**	 *所属项目编码	 */	private String projectCode;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActName() {	    return this.actName;	}	public void setActName(String actName) {	    this.actName=actName;	}	public Date getBegainTime() {	    return this.begainTime;	}	public void setBegainTime(Date begainTime) {	    this.begainTime=begainTime;	}	public Date getEndTime() {	    return this.endTime;	}	public void setEndTime(Date endTime) {	    this.endTime=endTime;	}	public String getHdurl() {	    return this.hdurl;	}	public void setHdurl(String hdurl) {	    this.hdurl=hdurl;	}	public String getTemplate() {	    return this.template;	}	public void setTemplate(String template) {	    this.template=template;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getProjectCode() {	    return this.projectCode;	}	public void setProjectCode(String projectCode) {	    this.projectCode=projectCode;	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	public String getManyCanJoin() {
		return manyCanJoin;
	}
	public void setManyCanJoin(String manyCanJoin) {
		this.manyCanJoin = manyCanJoin;
	}
	private List<WxActCommonftbRelation> ftbList;
	public List<WxActCommonftbRelation> getFtbList() {
		return ftbList;
	}
	public void setFtbList(List<WxActCommonftbRelation> ftbList) {
		this.ftbList = ftbList;
	}

}

