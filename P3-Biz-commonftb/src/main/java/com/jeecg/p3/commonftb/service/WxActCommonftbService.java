package com.jeecg.p3.commonftb.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonftb.entity.WxActCommonftb;

/**
 * 描述：</b>WxActCommonftbService<br>
 * @author：pituo
 * @since：2016年01月05日 11时07分05秒 星期二 
 * @version:1.0
 */
public interface WxActCommonftbService {
	
	
	public void doAdd(WxActCommonftb wxActCommonftb);
	
	public void doEdit(WxActCommonftb wxActCommonftb);
	
	public void doDelete(String id);
	
	public WxActCommonftb queryById(String id);
	
	public PageList<WxActCommonftb> queryPageList(PageQuery<WxActCommonftb> pageQuery);
	
	/**
	 * 根据主活动id查询需要组织的砍价产品活动
	 * @param mainActId
	 * @return
	 */
	public List<WxActCommonftb> queryCommonftbByMainActId(String mainActId);
	public WxActCommonftb queryWxActCommonftbByMainActIdAndOpenid(String mainActId,String openid);
	
	public List<WxActCommonftb> queryCommonftb(String jwid);
}

