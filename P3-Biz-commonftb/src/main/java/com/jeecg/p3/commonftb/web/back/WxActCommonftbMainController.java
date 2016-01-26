package com.jeecg.p3.commonftb.web.back;

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

import com.jeecg.p3.commonftb.entity.WxActCommonftb;
import com.jeecg.p3.commonftb.entity.WxActCommonftbMain;
import com.jeecg.p3.commonftb.entity.WxActCommonftbRelation;
import com.jeecg.p3.commonftb.service.WxActCommonftbMainService;
import com.jeecg.p3.commonftb.service.WxActCommonftbRelationService;
import com.jeecg.p3.commonftb.service.WxActCommonftbService;
import com.jeecg.p3.commonftb.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActCommonftbMainController<br>砍价主活动表
 * @author pituo
 * @since：2016年01月05日 10时52分01秒 星期二 
 * @version:1.0
 */
@Controller
@RequestMapping("/commonftb/back/wxActCommonftbMain")
public class WxActCommonftbMainController extends BaseController{
  @Autowired
  private WxActCommonftbMainService wxActCommonftbMainService;
  @Autowired
  private WxActCommonftbService wxActCommonftbService;
  @Autowired
  private WxActCommonftbRelationService wxActCommonftbRelationService;
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActCommonftbMain query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActCommonftbMain> pageQuery = new PageQuery<WxActCommonftbMain>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	 	query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActCommonftbMain",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActCommonftbMainService.queryPageList(pageQuery)));
		String viewName = "commonftb/back/wxActCommonftbMain-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActCommonftbMainDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonftb/back/wxActCommonftbMain-detail.vm";
		WxActCommonftbMain wxActCommonftbMain = wxActCommonftbMainService.queryById(id);
		velocityContext.put("wxActCommonftbMain",wxActCommonftbMain);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 List<WxActCommonftb> ftbs = wxActCommonftbService.queryCommonftb(jwid);
		 velocityContext.put("ftbs",ftbs);
		 List<WxActCommonftbRelation> actDetailList=wxActCommonftbRelationService.queryByMainActId(id);
		 velocityContext.put("actDetailList",actDetailList);
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
	 List<WxActCommonftb> ftbs = wxActCommonftbService.queryCommonftb(jwid);
	 velocityContext.put("ftbs",ftbs);
	 String viewName = "commonftb/back/wxActCommonftbMain-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActCommonftbMain wxActCommonftbMain){
	AjaxJson j = new AjaxJson();
	try {
		wxActCommonftbMainService.doAdd(wxActCommonftbMain);
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
		 WxActCommonftbMain wxActCommonftbMain = wxActCommonftbMainService.queryById(id);
		 velocityContext.put("wxActCommonftbMain",wxActCommonftbMain);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 List<WxActCommonftb> ftbs = wxActCommonftbService.queryCommonftb(jwid);
		 velocityContext.put("ftbs",ftbs);
		 List<WxActCommonftbRelation> actDetailList=wxActCommonftbRelationService.queryByMainActId(id);
		 velocityContext.put("actDetailList",actDetailList);
		 String viewName = "commonftb/back/wxActCommonftbMain-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActCommonftbMain wxActCommonftbMain){
	AjaxJson j = new AjaxJson();
	try {
		wxActCommonftbMainService.doEdit(wxActCommonftbMain);
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
			wxActCommonftbMainService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

