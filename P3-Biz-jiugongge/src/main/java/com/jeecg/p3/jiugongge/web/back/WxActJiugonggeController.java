package com.jeecg.p3.jiugongge.web.back;

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

import com.jeecg.p3.jiugongge.entity.WxActJiugongge;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeAwards;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggePrizes;
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRelation;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeAwardsService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggePrizesService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRelationService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeService;
import com.jeecg.p3.jiugongge.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActJiugonggeController<br>配置
 * @author junfeng.zhou
 * @since：2015年11月16日 11时07分11秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/jiugongge/back/wxActJiugongge")
public class WxActJiugonggeController extends BaseController{
  @Autowired
  private WxActJiugonggeService wxActJiugonggeService;
  @Autowired
  private WxActJiugonggeAwardsService wxActJiugonggeAwardsService;
  @Autowired
  private WxActJiugonggePrizesService wxActJiugonggePrizesService;
  @Autowired
  private WxActJiugonggeRelationService wxActJiugonggeRelationService;

  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActJiugongge query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActJiugongge> pageQuery = new PageQuery<WxActJiugongge>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	 	query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActJiugongge",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActJiugonggeService.queryPageList(pageQuery)));
		String viewName = "jiugongge/back/wxActJiugongge-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActJiugonggeDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/back/wxActJiugongge-detail.vm";
		WxActJiugongge wxActJiugongge = wxActJiugonggeService.queryById(id);
		velocityContext.put("wxActJiugongge",wxActJiugongge);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 List<WxActJiugonggeRelation> awarsDetailList=wxActJiugonggeRelationService.queryByActIdAndJwid(id,jwid);
		 velocityContext.put("awarsDetailList",awarsDetailList);
			List<WxActJiugonggeAwards> awards = wxActJiugonggeAwardsService.queryAwards(jwid);
			velocityContext.put("awards",awards);
			List<WxActJiugonggePrizes> prizes = wxActJiugonggePrizesService.queryPrizes(jwid);
			velocityContext.put("prizes",prizes);
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
		List<WxActJiugonggeAwards> awards = wxActJiugonggeAwardsService.queryAwards(jwid);
		velocityContext.put("awards",awards);
		List<WxActJiugonggePrizes> prizes = wxActJiugonggePrizesService.queryPrizes(jwid);
		velocityContext.put("prizes",prizes);
	 String viewName = "jiugongge/back/wxActJiugongge-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActJiugongge wxActJiugongge){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggeService.doAdd(wxActJiugongge);	
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
		 WxActJiugongge wxActJiugongge = wxActJiugonggeService.queryById(id);
		 velocityContext.put("wxActJiugongge",wxActJiugongge);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 List<WxActJiugonggeRelation> awarsDetailList=wxActJiugonggeRelationService.queryByActIdAndJwid(id,jwid);
		 velocityContext.put("awarsDetailList",awarsDetailList);
			List<WxActJiugonggeAwards> awards = wxActJiugonggeAwardsService.queryAwards(jwid);
			velocityContext.put("awards",awards);
			List<WxActJiugonggePrizes> prizes = wxActJiugonggePrizesService.queryPrizes(jwid);
			velocityContext.put("prizes",prizes);
		 String viewName = "jiugongge/back/wxActJiugongge-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActJiugongge wxActJiugongge){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggeService.doEdit(wxActJiugongge);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		e.printStackTrace();
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
			wxActJiugonggeService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

