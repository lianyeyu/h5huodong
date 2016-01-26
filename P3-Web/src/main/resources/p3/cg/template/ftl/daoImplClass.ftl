package ${daoImplPackage};

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.mybatis.GenericDaoDefault;
import org.springframework.stereotype.Repository;
import ${daoPackage}.${className}Dao;
import ${domainPackage}.${className};

/**
 * 描述：</b>${className}DaoImpl<br>
 * @author：${author}
 * @since：${nowDate}
 * @version:1.0
 */
@Repository("${lowerName}Dao")
public class ${className}DaoImpl extends GenericDaoDefault<${className}> implements ${className}Dao{

	@Override
	public Integer count(PageQuery<${className}> pageQuery) {
		return (Integer) super.queryOne("count",pageQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<${className}> queryPageList(
			PageQuery<${className}> pageQuery,Integer itemCount) {
		PageQueryWrapper<${className}> wrapper = new PageQueryWrapper<${className}>(pageQuery.getPageNo(), pageQuery.getPageSize(),itemCount, pageQuery.getQuery());
		return (List<${className}>)super.query("queryPageList",wrapper);
	}


}

