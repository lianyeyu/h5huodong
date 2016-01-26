package com.jeecg.p3.jiugongge.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;

/**
 * 描述：</b>WxActJiugonggeAwardsDao<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
public interface WxActJiugonggeAwardsDao extends GenericDao<WxActJiugonggeAwards>{
	
	public Integer count(PageQuery<WxActJiugonggeAwards> pageQuery);
	
	public List<WxActJiugonggeAwards> queryPageList(PageQuery<WxActJiugonggeAwards> pageQuery,Integer itemCount);
	public List<WxActJiugonggeAwards> queryAwards(String jwid);
	public Boolean validReat(int value,String jwid);
	public Boolean validReat(String id,int value,String jwid);
}

