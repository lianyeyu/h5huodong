package com.jeecg.p3.commonftb.dao.impl;

import java.util.List;
import java.util.Map;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.jeecg.p3.commonftb.dao.WxActCommonftbRelationDao;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRelation;

/**
 * 描述：</b>WxActCommonftbRelationDaoImpl<br>
 * @author：pituo
 * @since：2015年11月30日 17时32分07秒 星期一 
 * @version:1.0
 */
@Repository("wxActCommonftbRelationDao")
public class WxActCommonftbRelationDaoImpl extends GenericDaoDefault<WxActCommonftbRelation> implements WxActCommonftbRelationDao{

	@Override
	public Integer count(PageQuery<WxActCommonftbRelation> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxActCommonftbRelation> queryPageList(
			PageQuery<WxActCommonftbRelation> pageQuery,Integer itemCount) {
		PageQueryWrapper<WxActCommonftbRelation> wrapper = new PageQueryWrapper<WxActCommonftbRelation>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<WxActCommonftbRelation>)super.query("queryPageList",wrapper);
	}

	@Override
	public void batchDeleteByMainActId(String mainActId) {
		// TODO Auto-generated method stub
		super.delete("batchDeleteByMainActId", mainActId);
	}

	@Override
	public void bactchDeleteOldFtbs(List<String> ids,String mainActId) {
		Map<String,Object> param = Maps.newConcurrentMap();
		param.put("ids", ids);
		param.put("mainActId", mainActId);
		super.delete("bactchDeleteOldFtbs",param);
	}

	@Override
	public List<WxActCommonftbRelation> queryByMainActId(String mainActId) {
		// TODO Auto-generated method stub
		return (List<WxActCommonftbRelation>)super.query("queryByMainActId",mainActId);
	}
}

