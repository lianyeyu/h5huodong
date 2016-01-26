<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//ibatis.apache.org//DTD iBatis Mapper 3.0 //EN" 
	"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">

<mapper namespace="${domainPackage}.${className}">

	<!-- Result Map-->
	<resultMap id="${className}" type="${domainPackage}.${className}" >
	<#list columnDatas as item>
		<result column="${item.columnName}" property="${item.domainPropertyName}" jdbcType="${item.jdbcType?upper_case}"/>
	</#list>
	</resultMap>
	
	<!-- 查询条件 -->
	<sql id="wherecontation">
		<trim  suffixOverrides="," >
		   <#list columnDatas as item>
				<#if item.columnKey != 'PRI'>
				 <if test="query.${item.domainPropertyName} != null and query.${item.domainPropertyName} != ''" >
	  		 		/* ${item.columnComment} */
			    	AND ${tablesAsName}.${item.columnName} =  ${"#"}{query.${item.domainPropertyName},jdbcType=${item.jdbcType?upper_case}}
				 </if>
				</#if>
			</#list>
		</trim>
	</sql>

	<!--
	方法名称: insert
	调用路径: ${domainPackage}.${className}.insert
	开发信息: 
	处理信息: 保存信息
	-->
	<insert id="insert" parameterType="Object" >
	  INSERT  INTO  ${tableName}   /* ${codeName} */  
					(	
					<#list columnDatas as item>
						<#assign x="${item.columnName?length}" /> 
						<#if item_index==0>
${"                      "}${item.columnName}${"                              "?substring(item.columnName?length)}/* ${item.columnComment} */ 
						<#elseif item_index gt 0>
${"                     "},${item.columnName}${"                              "?substring(item.columnName?length)}/* ${item.columnComment} */ 
						</#if>
					</#list> 		
					)
			values (
					<#list columnDatas as item>
						<#if item_index==0>
${"                      "}${"#"}{${item.domainPropertyName},jdbcType=${item.jdbcType?upper_case}}${"                              "?substring(item.domainPropertyName?length)}/* ${item.columnComment} */ 
						<#elseif item_index gt 0>
${"                     "},${"#"}{${item.domainPropertyName},jdbcType=${item.jdbcType?upper_case}}${"                              "?substring(item.domainPropertyName?length)}/* ${item.columnComment} */ 
						</#if>
					</#list>
					)
	</insert>

	
	<!--
	方法名称: update
	调用路径: ${domainPackage}.${className}.update
	开发信息: 
	处理信息: 修改信息
	-->  
	 <update id="update" parameterType="Object" >
	  UPDATE   ${tableName}  	/* ${codeName} */
	  			<trim   prefix="SET" suffixOverrides="," >
	  				<#list columnDatas as item>
						<#if item.columnKey !='PRI' >
						 <if test="${item.domainPropertyName} != null">
		    		 		/* ${item.columnComment} */ 
	    		 			${item.columnName} = ${"#"}{${item.domainPropertyName},jdbcType=${item.jdbcType?upper_case}},
						 </if>
						</#if>
					</#list>
	  	  		</trim>
				WHERE
	  	 		 		id = ${"#"+"{id}"}		/* 序号 */ 
	 </update>
	
	<!--
	方法名称: get
	调用路径: ${domainPackage}.${className}.get
	开发信息: 
	处理信息: 根据主键查询记录
	-->
	<select id="get" parameterType="Object"  resultMap="${className}">
		   SELECT   
				  <#list columnDatas as item>
					   <#if item_index==0>
${"                   "}${tablesAsName}.${item.columnName}${"                              "?substring(item.columnName?length)}/* ${item.columnComment} */ 
					   <#else>
${"                  "},${tablesAsName}.${item.columnName}${"                              "?substring(item.columnName?length)}/* ${item.columnComment} */ 
						 </#if>
				   </#list>
		   FROM   ${tableName}      AS ${tablesAsName}      /* ${codeName} */ 
		   WHERE
				id = ${"#"+"{id}"}				/* 序号 */ 
	</select>
	
	<!--
	方法名称: delete
	调用路径: ${domainPackage}.${className}.delete
	开发信息: 
	处理信息: 删除记录
	-->
	<delete id="delete" parameterType="Object">
		DELETE 	FROM ${tableName} 	/* ${codeName} */  
		WHERE 
			id = ${"#"+"{id}"}					/* 序号 */ 
	</delete>
	
	<!--
	方法名称: count
	调用路径: ${domainPackage}.${className}.count
	开发信息: 
	处理信息: 列表总数
	-->
	<select id="count" resultType="java.lang.Integer"  parameterType="Object">
		SELECT count(*)  FROM  ${tableName}      AS ${tablesAsName}      /* ${codeName} */ 
		 WHERE 1=1
		    <include refid="wherecontation"/>
	</select>
  	
  	<!--
	方法名称: queryPageList
	调用路径: ${domainPackage}.${className}.queryPageList
	开发信息: 
	处理信息: 列表
	-->
	<select id="queryPageList" parameterType="Object"  resultMap="${className}">
		    SELECT 
				  <#list columnDatas as item>
					   <#if item_index==0>
${"                   "}${tablesAsName}.${item.columnName}${"                              "?substring(item.columnName?length)}/* ${item.columnComment} */ 
					   <#else>
${"                  "},${tablesAsName}.${item.columnName}${"                              "?substring(item.columnName?length)}/* ${item.columnComment} */ 
						 </#if>
				   </#list> 
		FROM   	 ${tableName}      AS ${tablesAsName}      /* ${codeName} */ 
		WHERE 1=1
		   <include refid="wherecontation"/>
		LIMIT  ${"#"+"{startRow}"}  		/* 开始序号 */ 
			  ,${"#"+"{pageSize}"}		/* 每页显示条数 */ 
	</select>
	
</mapper>