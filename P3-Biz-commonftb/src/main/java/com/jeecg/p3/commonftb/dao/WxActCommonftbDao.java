package com.jeecg.p3.commonftb.dao;

import java.util.Date;
import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.commonftb.entity.WxActCommonftb;

/**
 * 描述：</b>WxActCommonftbDao<br>
 * @author：pituo
 * @since：2016年01月05日 11时07分05秒 星期二 
 * @version:1.0
 */
public interface WxActCommonftbDao extends GenericDao<WxActCommonftb>{
	
	public Integer count(PageQuery<WxActCommonftb> pageQuery);
	
	public List<WxActCommonftb> queryPageList(PageQuery<WxActCommonftb> pageQuery,Integer itemCount);
	public List<WxActCommonftb> queryCommonftbByMainActId(String mainActId);
	/**
	 * 奖品剩余数量更新
	 * @param id 
	 * @param cutNum 扣除的数量
	 */
	public void updateProductRemainNum(String id,Integer cutNum,Date lastUpdateTime);
	
	public WxActCommonftb queryWxActCommonftbByMainActIdAndOpenid(String mainActId,String openid);
	
	public List<WxActCommonftb> queryCommonftb(String jwid);
}

