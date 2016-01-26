package ${serviceTestPackage};

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jd.jrpcore.BasicResult;
import com.jd.jrpcore.GenericResult;
import com.jd.jrpcore.ListResult;
import com.jd.jrpcore.PageListResult;
import com.jd.jrpcore.PageQuery;
import com.jd.jrpcore.Query;

import ${domainPackage}.${className};
import ${domainQueryPackage}.${className}Query;
import ${servicePackage}.${className}Service;
import ${serviceTestPackage}.base.BaseTest;

/**
 * 描述：</b>${className}ServiceTest<br>
 * @author：<a href="mailto:zhoujunfeng@jd.com">周俊峰</a>
 * @since：${nowDate}
 * @version:1.0
 */
public class ${className}ServiceTest extends BaseTest{

	public final static Logger log = LoggerFactory.getLogger(${className}ServiceTest.class);
	@Autowired
	private ${className}Service ${lowerName}Service;
	
//    @Test
	public void add${className}(){
		${className} ${lowerName} = new ${className}();
		//TODO set 测试数据
		
		GenericResult<String> result = ${lowerName}Service.add${className}(${lowerName});
		log.info("------------------------->"+result.getCode());
	}
	
//    @Test
    public void modify${className}(){
    	${className} ${lowerName} = new ${className}();
		//TODO set 测试数据
		
		BasicResult result = ${lowerName}Service.modify${className}(${lowerName});
		log.info("------------------------->"+result.getCode());
    }
    
    <#if columnKeyParam !="">
//    @Test
    public void findByPriKey(){
    	<#list columnKeyDatas as item>
    	String ${item.domainPropertyName} ="";
		</#list>
		GenericResult<${className}> result = ${lowerName}Service.findByPriKey(${columnKeyUseParam});
		log.info("------------------------->"+result.getCode());
    	log.info("------------------------->"+result.getValue());
    }
    </#if>
    
    <#if columnKeyParam !="">
//    @Test
    public void deleteByPriKey(){
    	<#list columnKeyDatas as item>
    	String ${item.domainPropertyName} ="";
		</#list>
		BasicResult result = ${lowerName}Service.deleteByPriKey(${columnKeyUseParam});
		log.info("------------------------->"+result.getCode());
    }
    </#if>
    
//    @Test
    public void search${className}(){
    	Query<${className}Query> query = new Query<${className}Query>();
    	${className}Query ${lowerName}Query = new ${className}Query();
    	query.setQuery(${lowerName}Query);

		ListResult<${className}> result = ${lowerName}Service.search${className}(query);
		log.info("------------------------->"+result.getCode());
    	log.info("------------------------->"+result.getValues().size());
    }
    
//    @Test
    public void searchPage${className}(){
    	PageQuery<${className}Query> query = new PageQuery<${className}Query>(1,2);
    	${className}Query ${lowerName}Query = new ${className}Query();
    	query.setQuery(${lowerName}Query);

		PageListResult<${className}> result = ${lowerName}Service.searchPage${className}(query);
		log.info("------------------------->"+result.getCode());
    	log.info("------------------------->"+result.getValues().size());
    }
	
	
}
