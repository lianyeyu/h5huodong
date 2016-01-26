package com.jeecg.p3.commonftb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;


/**
 * 联通短信发送
 * @author zhangdaihao
 *
 */
public class SendMsgUtils {

	public final static Logger LOG = LoggerFactory.getLogger(SendMsgUtils.class);
	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		//update-begin--Author:alax  Date:20150921 for：增加17号段-------------------
		p = Pattern.compile("^1((3\\d)|(4[57])|(5[01256789])|(7[678])|(8\\d))\\d{8}$"); // 验证手机号
		//update-end--Author:alax  Date:20150921 for：增加17号段-------------------
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	public static String enCode(byte bsrc[]) {
		String str = "";
		StringBuffer dest = new StringBuffer(bsrc.length * 2);
		if (bsrc == null)
			return "";
		for (int ii = 0; ii < bsrc.length; ii++) {
			byte bb = bsrc[ii];
			int num;
			if (bb >= 0)
				num = bb;
			else
				num = (bb & 0x7f) + 128;
			str = Integer.toHexString(num);
			if (str.length() < 2)
				str = (new StringBuilder()).append("0").append(str).toString();
			dest.append(str.toUpperCase());
		}
		return new String(dest);
	} 
	
	// 这是一个加码的函数
	public static String sendSMS(String SMS, String telphone){
		String url;
		try {
			url = "http://211.94.188.17:8030/Submit%20Gatename=ringplus&CommandId=1&Name=tjCMX0&Pwd=TJcmx0%20&ItemId=17401&SpNumber=10655123&UserNumber="+telphone+"&FeeType=1&MTFlag=2&ReportFlag=0&MsgCode=15&ExtData=123&Msg:=" +enCode(SMS.getBytes("GBK"));
			//org.jeecgframework.core.util.LogUtil.info("execute getMethod for url:" + url);
			HttpUtil.httpRequest(url, "GET", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			LOG.info(" -----------sendSMS---------------------- "+e.toString());
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
		sendSMS("你好", "1872127605");
	}
}
