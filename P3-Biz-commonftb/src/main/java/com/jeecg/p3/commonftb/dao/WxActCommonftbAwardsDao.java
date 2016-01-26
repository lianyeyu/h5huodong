package com.jeecg.p3.commonftb.dao;

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonftb.entity.WxActCommonftbAwards;

/**
 * 描述：</b>WxActCommonsjAwardsDao<br>
 * @author：pituo
 * @since：2015年11月30日 15时51分47秒 星期一 
 * @version:1.0
 */
public interface WxActCommonftbAwardsDao extends GenericDao<WxActCommonftbAwards>{
	
	public Integer count(PageQuery<WxActCommonftbAwards> pageQuery);
	
	public List<WxActCommonftbAwards> queryPageList(PageQuery<WxActCommonftbAwards> pageQuery,Integer itemCount);
	public List<WxActCommonftbAwards> queryBargainAwardsByActIdAndOpenid(String actId,String openid);
	public List<WxActCommonftbAwards> queryBargainAwardsByActId(String actId);
	public Integer getMaxAwardsSeq(String actId);
	public List<WxActCommonftbAwards> queryBargainAwardsByMainActIdOpenid(String mainActId,String openid);
}

