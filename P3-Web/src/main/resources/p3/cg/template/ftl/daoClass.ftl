package ${daoPackage};

import java.util.List;

import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import ${domainPackage}.${className};

/**
 * 描述：</b>${className}Dao<br>
 * @author：${author}
 * @since：${nowDate}
 * @version:1.0
 */
public interface ${className}Dao extends GenericDao<${className}>{
	
	public Integer count(PageQuery<${className}> pageQuery);
	
	public List<${className}> queryPageList(PageQuery<${className}> pageQuery,Integer itemCount);
	
}

