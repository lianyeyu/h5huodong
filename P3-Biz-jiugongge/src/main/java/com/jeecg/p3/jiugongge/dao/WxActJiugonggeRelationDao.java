package com.jeecg.p3.jiugongge.dao;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRelation;

/**
 * 描述：</b>WxActJiugonggePrizesRelationDao<br>
 * @author：junfeng.zhou
 * @since：2015年11月16日 11时07分13秒 星期一 
 * @version:1.0
 */
public interface WxActJiugonggeRelationDao extends GenericDao<WxActJiugonggeRelation>{
	
	public Integer count(PageQuery<WxActJiugonggeRelation> pageQuery);
	
	public List<WxActJiugonggeRelation> queryPageList(PageQuery<WxActJiugonggeRelation> pageQuery,Integer itemCount);
	public List<WxActJiugonggeRelation> queryByActIdAndJwid(String actid,String jwid);
	public void updateRemainNum(String actid,String jwid,String awardid);
	
	public void batchDeleteByActId( String actid) ;
	public void bactchDeleteOldAwards(List<String> ids,String actId) ;
	
	public Boolean validUsed(String awadsid,String prizeid);
}

