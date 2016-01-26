package com.jeecg.p3.jiugongge.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.jiugongge.dao.WxActJiugonggeRelationDao;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRelation;

/**
 * 描述：</b>WxActJiugonggeRelationDaoImpl<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分13秒 星期一 
 * @version:1.0
 */
@Repository("wxActJiugonggeRelationDao")
public class WxActJiugonggeRelationDaoImpl extends GenericDaoDefault<WxActJiugonggeRelation> implements WxActJiugonggeRelationDao{

	@Override
	public Integer count(PageQuery<WxActJiugonggeRelation> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActJiugonggeRelation> queryPageList(
			PageQuery<WxActJiugonggeRelation> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActJiugonggeRelation> wrapper = new PageQueryWrapper<WxActJiugonggeRelation>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActJiugonggeRelation>)super.query("queryPageList",wrapper);
	}

	@Override
	public List<WxActJiugonggeRelation> queryByActIdAndJwid(String actid,
			String jwid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		param.put("jwid", jwid);
		return (List<WxActJiugonggeRelation>)super.query("queryByActIdAndJwid",param);
	}

	@Override
	public void batchDeleteByActId(String actid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		super.delete("batchDeleteByActId", param);
	}

	@Override
	public void updateRemainNum(String actid,String jwid,String awardid) {
		// TODO Auto-generated method stub
		Map<String,String> param = Maps.newConcurrentMap();
		param.put("actId", actid);
		param.put("jwid", jwid);
		param.put("awardid", awardid);
		super.update("updateRemainNum", param);
	}

	@Override
	public void bactchDeleteOldAwards(List<String> ids,String actId) {
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("ids", ids);
		param.put("actId", actId);
		super.delete("bactchDeleteOldAwards",param);
	}

	@Override
	public Boolean validUsed(String awardid,String prizeid) {
		Map<String,String> param = Maps.newConcurrentMap();
		if(StringUtils.isEmpty(awardid)){
			param.put("prizeId", prizeid);
		}else{			
			param.put("awardId", awardid);
		}
		List<WxActJiugonggeRelation> relations=(List<WxActJiugonggeRelation>)super.query("validUsed",param);
		if(relations.size()>0){			
			return true;
		}else{
			return false;
		}
	}

}

