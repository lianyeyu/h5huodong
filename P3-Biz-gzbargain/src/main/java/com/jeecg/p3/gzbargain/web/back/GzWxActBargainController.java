package com.jeecg.p3.gzbargain.web.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ContextHolderUtils;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.p3.gzbargain.entity.GzWxActBargain;
import com.jeecg.p3.gzbargain.service.GzWxActBargainService;

 /**
 * 描述：</b>GzWxActBargainController<br>砍价活动配置
 * @author junfeng.zhou
 * @since：2015年10月27日 12时04分42秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/gzbargain/back/gzWxActBargain")
public class GzWxActBargainController extends BaseController{
  @Autowired
  private GzWxActBargainService gzWxActBargainService;
  
  
 /**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute GzWxActBargain query,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	try {
			PageQuery<GzWxActBargain> pageQuery = new PageQuery<GzWxActBargain>();
			pageQuery.setPageNo(pageNo);
			pageQuery.setPageSize(pageSize);
			VelocityContext velocityContext = new VelocityContext();
			String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 	query.setJwid(jwid);
			pageQuery.setQuery(query);
			velocityContext.put("query",query);
			velocityContext.put("pageInfos",SystemTools.convertPaginatedList(gzWxActBargainService.queryPageList(pageQuery)));
			String viewName = "gzbargain/back/gzWxActBargain-list.vm";
			ViewVelocity.view(response,viewName,velocityContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void gzWxActBargainDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response)throws Exception{
		try {
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "gzbargain/back/gzWxActBargain-detail.vm";
			GzWxActBargain gzWxActBargain = gzWxActBargainService.queryById(id);
			velocityContext.put("gzWxActBargain",gzWxActBargain);
			ViewVelocity.view(response,viewName,velocityContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "gzbargain/back/gzWxActBargain-add.vm";
	 ViewVelocity.view(response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute GzWxActBargain gzWxActBargain){
	AjaxJson j = new AjaxJson();
	try {
		gzWxActBargainService.doAdd(gzWxActBargain);
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 跳转到编辑页面
 * @return
 */
@RequestMapping(value="toEdit",method = RequestMethod.GET)
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response) throws Exception{
		 try {
			VelocityContext velocityContext = new VelocityContext();
			 GzWxActBargain gzWxActBargain = gzWxActBargainService.queryById(id);
			 velocityContext.put("gzWxActBargain",gzWxActBargain);
			 String viewName = "gzbargain/back/gzWxActBargain-edit.vm";
			 ViewVelocity.view(response,viewName,velocityContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute GzWxActBargain gzWxActBargain){
	AjaxJson j = new AjaxJson();
	try {
		gzWxActBargainService.doEdit(gzWxActBargain);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}


/**
 * 删除
 * @return
 */
@RequestMapping(value="doDelete",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			gzWxActBargainService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

