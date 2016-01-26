package com.jeecg.p3.commonftb.web.vo;

import org.jeecgframework.p3.base.vo.WeixinDto;

public class BargainDto extends WeixinDto{
	private static final long serialVersionUID = -1935558318674922380L;
	/**
	 *主活动id
	 */
	private String mainActId;
	/**
	 *报名id
	 */
	private String registrationId;
	/**
	 *手机号
	 */
	private String mobile;
	public String getMainActId() {
		return mainActId;
	}
	public void setMainActId(String mainActId) {
		this.mainActId = mainActId;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "BargainDto [mainActId=" + mainActId + ", registrationId="
				+ registrationId + ", mobile=" + mobile + "]";
	}


}
