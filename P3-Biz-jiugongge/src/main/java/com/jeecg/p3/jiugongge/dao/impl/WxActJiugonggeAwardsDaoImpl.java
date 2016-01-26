package com.jeecg.p3.jiugongge.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeAwardsDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;

/**
 * 描述：</b>WxActJiugonggeAwardsDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
@Repository("wxActJiugonggeAwardsDao")
public class WxActJiugonggeAwardsDaoImpl extends GenericDaoDefault<WxActJiugonggeAwards> implements WxActJiugonggeAwardsDao{

	@Override
	public Integer count(PageQuery<WxActJiugonggeAwards> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActJiugonggeAwards> queryPageList(
			PageQuery<WxActJiugonggeAwards> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActJiugonggeAwards> wrapper = new PageQueryWrapper<WxActJiugonggeAwards>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActJiugonggeAwards>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActJiugonggeAwards> queryAwards(String jwid) {
		// TODO Auto-generated method stub
		return (List<WxActJiugonggeAwards>)super.query("queryAwards",jwid);
	}

	@Override
	public Boolean validReat(int value, String jwid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("value", String.valueOf(value));
		param.put("jwid", jwid);
		WxActJiugonggeAwards award=(WxActJiugonggeAwards)super.queryOne("validReat",param);
		if(award==null){			
			return false;
		}else{
			return true;
		}	
	}

	@Override
	public Boolean validReat(String id, int value, String jwid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("id", id);
		param.put("value", String.valueOf(value));
		param.put("jwid", jwid);
		WxActJiugonggeAwards award=(WxActJiugonggeAwards)super.queryOne("editValidReat",param);
		if(award==null){			
			return false;
		}else{
			return true;
		}
	}


}

