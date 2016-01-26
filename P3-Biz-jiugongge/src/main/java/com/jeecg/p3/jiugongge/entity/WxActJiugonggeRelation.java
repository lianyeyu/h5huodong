package com.jeecg.p3.jiugongge.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.jeecgframework.p3.core.utils.persistence.Entity;

/**
 * 描述：</b>WxActJiugonggeRelation:配置<br>
 * @author junfeng.zhou
 * @since：2015年11月17日 18时58分22秒 星期二 
 * @version:1.0
 */
public class WxActJiugonggeRelation implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *	 */	private String prizeId;	/**	 *	 */	private String actId;	/**	 *	 */	private String jwid;	/**	 *	 */	private String awardId;	/**	 *数量	 */	private Integer amount;	/**	 *剩余数量	 */	private Integer remainNum;	/**	 *概率	 */	private Double probability;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getPrizeId() {	    return this.prizeId;	}	public void setPrizeId(String prizeId) {	    this.prizeId=prizeId;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}	public String getAwardId() {	    return this.awardId;	}	public void setAwardId(String awardId) {	    this.awardId=awardId;	}	public Integer getAmount() {	    return this.amount;	}	public void setAmount(Integer amount) {	    this.amount=amount;	}
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public Integer getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(Integer remainNum) {
		this.remainNum = remainNum;
	}

}

