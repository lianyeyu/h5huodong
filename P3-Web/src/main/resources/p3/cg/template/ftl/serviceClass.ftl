package ${servicePackage};

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import ${domainPackage}.${className};

/**
 * 描述：</b>${className}Service<br>
 * @author：${author}
 * @since：${nowDate}
 * @version:1.0
 */
public interface ${className}Service {
	
	
	public void doAdd(${className} ${lowerName});
	
	public void doEdit(${className} ${lowerName});
	
	public void doDelete(String id);
	
	public ${className} queryById(String id);
	
	public PageList<${className}> queryPageList(PageQuery<${className}> pageQuery);
}

