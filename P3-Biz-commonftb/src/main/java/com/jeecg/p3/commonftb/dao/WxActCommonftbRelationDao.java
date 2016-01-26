package com.jeecg.p3.commonftb.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonftb.entity.WxActCommonftbRelation;

/**
 * 描述：</b>WxActCommonftbRelationDao<br>
 * @author：pituo
 * @since：2015年11月30日 17时32分07秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbRelationDao extends GenericDao<WxActCommonftbRelation>{
	
	public Integer count(PageQuery<WxActCommonftbRelation> pageQuery);
	
	public List<WxActCommonftbRelation> queryPageList(PageQuery<WxActCommonftbRelation> pageQuery,Integer itemCount);
	public void batchDeleteByMainActId(String mainActId);
	
	public void bactchDeleteOldFtbs(List<String> ids,String mainActId) ;
	
	public List<WxActCommonftbRelation> queryByMainActId(String mainActId);
}

