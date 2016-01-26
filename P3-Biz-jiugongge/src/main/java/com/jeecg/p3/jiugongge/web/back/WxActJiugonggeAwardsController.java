package com.jeecg.p3.jiugongge.web.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.core.util.SystemTools;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeAwardsService;
import com.jeecg.p3.jiugongge.util.ContextHolderUtils;

import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>WxActJiugonggeAwardsController<br>配置
 * @author junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/jiugongge/back/wxActJiugonggeAwards")
public class WxActJiugonggeAwardsController extends BaseController{
  @Autowired
  private WxActJiugonggeAwardsService wxActJiugonggeAwardsService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActJiugonggeAwards query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActJiugonggeAwards> pageQuery = new PageQuery<WxActJiugonggeAwards>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	 	query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("query",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActJiugonggeAwardsService.queryPageList(pageQuery)));
		String viewName = "jiugongge/back/wxActJiugonggeAwards-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActJiugonggeAwardsDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/back/wxActJiugonggeAwards-detail.vm";
		WxActJiugonggeAwards wxActJiugonggeAwards = wxActJiugonggeAwardsService.queryById(id);
		velocityContext.put("wxActJiugonggeAwards",wxActJiugonggeAwards);
		ViewVelocity.view(response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String viewName = "jiugongge/back/wxActJiugonggeAwards-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActJiugonggeAwards wxActJiugonggeAwards){
	AjaxJson j = new AjaxJson();
	try {
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		//奖项值验重，统一jwid下不能重复
		Boolean repeat=wxActJiugonggeAwardsService.validReat(wxActJiugonggeAwards.getAwardsValue(),jwid);	
		if(repeat){
			j.setSuccess(false);
			j.setMsg("奖项值重复，请重新设置");
		}else{			
			wxActJiugonggeAwardsService.doAdd(wxActJiugonggeAwards);
			j.setMsg("保存成功");
		}
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
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 WxActJiugonggeAwards wxActJiugonggeAwards = wxActJiugonggeAwardsService.queryById(id);
		 velocityContext.put("wxActJiugonggeAwards",wxActJiugonggeAwards);
		 String viewName = "jiugongge/back/wxActJiugonggeAwards-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActJiugonggeAwards wxActJiugonggeAwards){
	AjaxJson j = new AjaxJson();
	try {
		String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		//奖项值验重，统一jwid下不能重复
		Boolean repeat=wxActJiugonggeAwardsService.validReat(wxActJiugonggeAwards.getId(),wxActJiugonggeAwards.getAwardsValue(),jwid);	
		if(repeat){
			j.setSuccess(false);
			j.setMsg("奖项值重复，请重新设置");
		}else{			
			wxActJiugonggeAwardsService.doEdit(wxActJiugonggeAwards);
			j.setMsg("编辑成功");
		}	
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
			//判断奖项是否被使用
			Boolean used=wxActJiugonggeAwardsService.validUsed(id);
			if(used){
				j.setSuccess(false);
				j.setMsg("该奖项已经被活动使用，不能删除");
			}else{	
			wxActJiugonggeAwardsService.doDelete(id);
			j.setMsg("删除成功");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

