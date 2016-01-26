package com.jeecg.p3.jiugongge.service;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRelation;

/**
 * 描述：</b>WxActJiugonggePrizesRelationService<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分13秒 星期一 
 * @version:1.0
 */
public interface WxActJiugonggeRelationService {
	
	
	public void doAdd(WxActJiugonggeRelation wxActJiugonggePrizesRelation);
	
	public void doEdit(WxActJiugonggeRelation wxActJiugonggePrizesRelation);
	
	public void doDelete(String id);
	
	public WxActJiugonggeRelation queryById(String id);
	
	public PageList<WxActJiugonggeRelation> queryPageList(PageQuery<WxActJiugonggeRelation> pageQuery);
	public List<WxActJiugonggeRelation> queryByActIdAndJwid(String actid,String jwid);
}

