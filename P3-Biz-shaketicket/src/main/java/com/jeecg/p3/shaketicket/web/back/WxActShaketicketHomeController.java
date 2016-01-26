package com.jeecg.p3.shaketicket.web.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.util.SystemTools;
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

import com.jeecg.p3.shaketicket.entity.WxActShaketicketAward;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketConfig;
import com.jeecg.p3.shaketicket.entity.WxActShaketicketHome;
import com.jeecg.p3.shaketicket.service.WxActShaketicketAwardService;
import com.jeecg.p3.shaketicket.service.WxActShaketicketConfigService;
import com.jeecg.p3.shaketicket.service.WxActShaketicketHomeService;
import com.jeecg.p3.shaketicket.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActShaketicketHomeController<br>九宫格活动表
 * @author pituo
 * @since：2015年12月22日 19时03分50秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/shaketicket/back/wxActShaketicketHome")
public class WxActShaketicketHomeController extends BaseController{
  @Autowired
  private WxActShaketicketHomeService wxActShaketicketHomeService;
  @Autowired
  private WxActShaketicketAwardService wxActShaketicketAwardService;
  @Autowired
  private WxActShaketicketConfigService wxActShaketicketConfigService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActShaketicketHome query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActShaketicketHome> pageQuery = new PageQuery<WxActShaketicketHome>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	 	query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActShaketicketHome",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActShaketicketHomeService.queryPageList(pageQuery)));
		String viewName = "shaketicket/back/wxActShaketicketHome-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActShaketicketHomeDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "shaketicket/back/wxActShaketicketHome-detail.vm";
		WxActShaketicketHome wxActShaketicketHome = wxActShaketicketHomeService.queryById(id);
		velocityContext.put("wxActShaketicketHome",wxActShaketicketHome);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 List<WxActShaketicketAward> awards = wxActShaketicketAwardService.queryAwards(jwid);
		 velocityContext.put("awards",awards);
		 List<WxActShaketicketConfig> awarsDetailList=wxActShaketicketConfigService.queryByActId(id);
		 velocityContext.put("awarsDetailList",awarsDetailList);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	 List<WxActShaketicketAward> awards = wxActShaketicketAwardService.queryAwards(jwid);
	 velocityContext.put("awards",awards);
	 String viewName = "shaketicket/back/wxActShaketicketHome-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActShaketicketHome wxActShaketicketHome){
	AjaxJson j = new AjaxJson();
	try {
		wxActShaketicketHomeService.doAdd(wxActShaketicketHome);
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
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 WxActShaketicketHome wxActShaketicketHome = wxActShaketicketHomeService.queryById(id);
		 velocityContext.put("wxActShaketicketHome",wxActShaketicketHome);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 List<WxActShaketicketAward> awards = wxActShaketicketAwardService.queryAwards(jwid);
		 velocityContext.put("awards",awards);
		 List<WxActShaketicketConfig> awarsDetailList=wxActShaketicketConfigService.queryByActId(id);
		 velocityContext.put("awarsDetailList",awarsDetailList);
		 String viewName = "shaketicket/back/wxActShaketicketHome-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActShaketicketHome wxActShaketicketHome){
	AjaxJson j = new AjaxJson();
	try {
		wxActShaketicketHomeService.doEdit(wxActShaketicketHome);
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
			wxActShaketicketHomeService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

