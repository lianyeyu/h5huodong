package com.jeecg.p3.shaketicket.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActShaketicketConfig:活动奖项配置表<br>
 * @author pituo
 * @since：2015年12月24日 11时08分29秒 星期四 
 * @version:1.0
 */
public class WxActShaketicketConfig implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *活动ID	 */	private String id;	/**	 *所属活动	 */	private String actId;	/**	 *	 */	private String awardId;	/**	 *中奖概率	 */	private Double probability;	/**	 *总数量	 */	private Integer amount;	/**	 *剩余数量	 */	private Integer remainNum;	/**	 *微信公众号	 */	private String jwid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getAwardId() {	    return this.awardId;	}	public void setAwardId(String awardId) {	    this.awardId=awardId;	}	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public Integer getAmount() {	    return this.amount;	}	public void setAmount(Integer amount) {	    this.amount=amount;	}	public Integer getRemainNum() {	    return this.remainNum;	}	public void setRemainNum(Integer remainNum) {	    this.remainNum=remainNum;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
}

