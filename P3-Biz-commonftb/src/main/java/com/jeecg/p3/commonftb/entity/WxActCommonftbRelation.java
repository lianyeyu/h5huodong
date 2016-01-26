package com.jeecg.p3.commonftb.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActCommonsjRelation:活动首页活动配置<br>
 * @author pituo
 * @since：2015年11月30日 17时32分07秒 星期一 
 * @version:1.0
 */
public class WxActCommonftbRelation implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *ID	 */	private String id;	/**	 *主活动编码	 */	private String mainActId;	/**	 *活动编码	 */	private String actId;	/**	 *活动排序	 */	private Integer actSort;	/**	 *创建时间	 */	private Date createTime;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getMainActId() {	    return this.mainActId;	}	public void setMainActId(String mainActId) {	    this.mainActId=mainActId;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public Integer getActSort() {	    return this.actSort;	}	public void setActSort(Integer actSort) {	    this.actSort=actSort;	}	public Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(Date createTime) {	    this.createTime=createTime;	}
}

