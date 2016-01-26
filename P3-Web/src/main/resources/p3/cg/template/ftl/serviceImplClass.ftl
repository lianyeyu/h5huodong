package ${serviceImplPackage};

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.Pagenation;
import ${servicePackage}.${className}Service;
import ${domainPackage}.${className};
import ${daoPackage}.${className}Dao;

@Service("${lowerName}Service")
public class ${className}ServiceImpl implements ${className}Service {
	@Resource
	private ${className}Dao ${lowerName}Dao;

	@Override
	public void doAdd(${className} ${lowerName}) {
		${lowerName}Dao.add(${lowerName});
	}

	@Override
	public void doEdit(${className} ${lowerName}) {
		${lowerName}Dao.update(${lowerName});
	}

	@Override
	public void doDelete(String id) {
		${lowerName}Dao.delete(id);
	}

	@Override
	public ${className} queryById(String id) {
		${className} ${lowerName}  = ${lowerName}Dao.get(id);
		return ${lowerName};
	}

	@Override
	public PageList<${className}> queryPageList(
		PageQuery<${className}> pageQuery) {
		PageList<${className}> result = new PageList<${className}>();
		Integer itemCount = ${lowerName}Dao.count(pageQuery);
		List<${className}> list = ${lowerName}Dao.queryPageList(pageQuery,itemCount);
		Pagenation pagenation = new Pagenation(pageQuery.getPageNo(), itemCount, pageQuery.getPageSize());
		result.setPagenation(pagenation);
		result.setValues(list);
		return result;
	}
	
}
