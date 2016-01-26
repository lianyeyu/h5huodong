package com.jeecg.p3.jiugongge.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActJiugonggeAwards:配置<br>
 * @author junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
public class WxActJiugonggeAwards implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *奖项名称	 */	private String awardsName;	/**	 *	 */	private String jwid;	/**	 *	 */	private Integer awardsValue;
	private String jwidName;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getAwardsName() {	    return this.awardsName;	}	public void setAwardsName(String awardsName) {	    this.awardsName=awardsName;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public Integer getAwardsValue() {	    return this.awardsValue;	}	public void setAwardsValue(Integer awardsValue) {	    this.awardsValue=awardsValue;	}
	public String getJwidName() {
		return jwidName;
	}
	public void setJwidName(String jwidName) {
		this.jwidName = jwidName;
	}
	
	
}

