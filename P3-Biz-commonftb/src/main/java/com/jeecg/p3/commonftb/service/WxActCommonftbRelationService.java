package com.jeecg.p3.commonftb.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;

import com.jeecg.p3.commonftb.entity.WxActCommonftbRelation;

/**
 * 描述：</b>WxActCommonftbRelationService<br>
 * @author：pituo
 * @since：2015年11月30日 17时32分07秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbRelationService {
	
	
	public void doAdd(WxActCommonftbRelation wxActCommonftbRelation);
	
	public void doEdit(WxActCommonftbRelation wxActCommonftbRelation);
	
	public void doDelete(String id);
	
	public WxActCommonftbRelation queryById(String id);
	
	public PageList<WxActCommonftbRelation> queryPageList(PageQuery<WxActCommonftbRelation> pageQuery);
	
	public List<WxActCommonftbRelation> queryByMainActId(String mainActId);
}

