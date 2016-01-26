package com.jeecg.p3.commonftb.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.commonftb.entity.WxActCommonftbMain;

/**
 * 描述：</b>WxActCommonftbMainService<br>
 * @author：pituo
 * @since：2016年01月05日 10时52分01秒 星期二 
 * @version:1.0
 */
public interface WxActCommonftbMainService {
	
	
	public void doAdd(WxActCommonftbMain wxActCommonftbMain);
	
	public void doEdit(WxActCommonftbMain wxActCommonftbMain);
	
	public void doDelete(String id);
	
	public WxActCommonftbMain queryById(String id);
	
	public PageList<WxActCommonftbMain> queryPageList(PageQuery<WxActCommonftbMain> pageQuery);
}

