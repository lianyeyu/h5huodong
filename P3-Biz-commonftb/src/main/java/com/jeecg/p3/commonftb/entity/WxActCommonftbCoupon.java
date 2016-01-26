package com.jeecg.p3.commonftb.entity;

import java.io.File;
import java.math.BigDecimal;

import org.jeecgframework.p3.core.utils.persistence.Entity;
import org.springframework.web.multipart.MultipartFile;

import com.jeecg.p3.commonftb.annotation.Excel;

/**
 * 描述：</b>WxActCommonsjCoupon:卡券配置表<br>
 * @author pituo
 * @since：2015年12月02日 17时54分58秒 星期三 
 * @version:1.0
 */
public class WxActCommonftbCoupon implements Entity<String> {
	private static final long serialVersionUID = 1L;
		/**	 *	 */	private String id;	/**	 *活动id(子活动)	 */	private String actId;	/**	 *卡券ID	 */
	@Excel(exportName="卡券ID", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String cardId;	/**	 *卡券密码	 */
	@Excel(exportName="卡券密码", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String cardPsd;	/**	 *卡券类型 CASH：代金券类型	 */
	@Excel(exportName="卡券类型", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String cardType;	/**	 *商户名字	 */
	@Excel(exportName="商户名字", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String brandName;	/**	 *卡券名	 */
	@Excel(exportName="卡券名", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private String title;	/**	 *代金券专用，表示起用金额（单位为元）	 */
	@Excel(exportName="起用金额", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)	private BigDecimal leastCost;	/**	 *代金券专用，表示减免金额。（单位为元）	 */
	@Excel(exportName="减免金额", exportConvertSign = 0, exportFieldWidth = 30, importConvertSign = 0)    	private BigDecimal reduceCost;	/**	 *状态（0:未领取，1:已领取）	 */	private String status;	/**	 *微信原始id	 */	private String jwid;	public String getId() {	    return this.id;	}	public void setId(String id) {	    this.id=id;	}	public String getActId() {	    return this.actId;	}	public void setActId(String actId) {	    this.actId=actId;	}	public String getCardId() {	    return this.cardId;	}	public void setCardId(String cardId) {	    this.cardId=cardId;	}	public String getCardPsd() {	    return this.cardPsd;	}	public void setCardPsd(String cardPsd) {	    this.cardPsd=cardPsd;	}	public String getCardType() {	    return this.cardType;	}	public void setCardType(String cardType) {	    this.cardType=cardType;	}	public String getBrandName() {	    return this.brandName;	}	public void setBrandName(String brandName) {	    this.brandName=brandName;	}	public String getTitle() {	    return this.title;	}	public void setTitle(String title) {	    this.title=title;	}	public BigDecimal getLeastCost() {	    return this.leastCost;	}	public void setLeastCost(BigDecimal leastCost) {	    this.leastCost=leastCost;	}	public BigDecimal getReduceCost() {	    return this.reduceCost;	}	public void setReduceCost(BigDecimal reduceCost) {	    this.reduceCost=reduceCost;	}	public String getStatus() {	    return this.status;	}	public void setStatus(String status) {	    this.status=status;	}	public String getJwid() {	    return this.jwid;	}	public void setJwid(String jwid) {	    this.jwid=jwid;	}
	
	private MultipartFile  filedata;
	public MultipartFile getFiledata() {
		return filedata;
	}
	public void setFiledata(MultipartFile filedata) {
		this.filedata = filedata;
	}
	private String filedataFileName;
	public String getFiledataFileName() {
		return filedataFileName;
	}
	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}  
	
}

