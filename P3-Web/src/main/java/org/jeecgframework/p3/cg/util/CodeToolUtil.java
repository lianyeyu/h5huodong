package org.jeecgframework.p3.cg.util;

import org.jeecgframework.p3.cg.def.FtlDef;
import org.jeecgframework.p3.cg.factory.CodeGenerateFactory;
import org.jeecgframework.p3.core.utils.common.StringUtils;

/**
 * 描述：根据自定义表生成
 * @author：zhoujf
 * @since：
 * @version:1.0
 */
public class CodeToolUtil {

	public static void main(String[] args) {
		 /** 此处修改成你的 表名 和 中文注释***/
//		 String codeCgTables = CodeResourceUtil.getConfigInfo("code_cg_tables");
		String code_cg_tables="weixin_vip_member";
		 if(StringUtils.isEmpty(code_cg_tables)){
			 return;
		 }
		 String[] tables =code_cg_tables.split(",");
		 for(String tableName:tables){
			CodeGenerateFactory.codeGenerateByFTL(tableName, "",FtlDef.KEY_TYPE_02);
		 }
		
//		String dbtableName = "wx_act_jsbargain_coupon";
//		String title = "砍价活动配置";
//		CodeGenerateFactory.codeGenerateByFTL(dbtableName, title,FtlDef.KEY_TYPE_02);
	}
}
