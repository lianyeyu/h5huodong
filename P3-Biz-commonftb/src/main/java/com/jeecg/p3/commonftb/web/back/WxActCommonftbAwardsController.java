package com.jeecg.p3.commonftb.web.back;

import java.io.InputStream;
import java.io.OutputStream;

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

import com.jeecg.p3.commonftb.entity.WxActCommonftbAwards;
import com.jeecg.p3.commonftb.service.WxActCommonftbAwardsService;
import com.jeecg.p3.commonftb.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActCommonftbAwardsController<br>领取奖品记录表
 * @author pituo
 * @since：2016年01月06日 14时19分12秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/commonftb/back/wxActCommonftbAwards")
public class WxActCommonftbAwardsController extends BaseController{
  @Autowired
  private WxActCommonftbAwardsService wxActCommonftbAwardsService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActCommonftbAwards query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActCommonftbAwards> pageQuery = new PageQuery<WxActCommonftbAwards>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("wxActCommonftbAwards",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActCommonftbAwardsService.queryPageList(pageQuery)));
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
		String actName =  ContextHolderUtils.getRequest().getParameter("actName");
		velocityContext.put("actName",actName);
		String viewName = "commonftb/back/wxActCommonftbAwards-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActCommonftbAwardsDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonftb/back/wxActCommonftbAwards-detail.vm";
		WxActCommonftbAwards wxActCommonftbAwards = wxActCommonftbAwardsService.queryById(id);
		velocityContext.put("wxActCommonftbAwards",wxActCommonftbAwards);
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
	 String viewName = "commonftb/back/wxActCommonftbAwards-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActCommonftbAwards wxActCommonftbAwards){
	AjaxJson j = new AjaxJson();
	try {
		wxActCommonftbAwardsService.doAdd(wxActCommonftbAwards);
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
		 WxActCommonftbAwards wxActCommonftbAwards = wxActCommonftbAwardsService.queryById(id);
		 velocityContext.put("wxActCommonftbAwards",wxActCommonftbAwards);
		 String viewName = "commonftb/back/wxActCommonftbAwards-edit.vm";
		 String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
			velocityContext.put("backurl",backurl);
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActCommonftbAwards wxActCommonftbAwards){
	AjaxJson j = new AjaxJson();
	try {
		wxActCommonftbAwardsService.doEdit(wxActCommonftbAwards);
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
			wxActCommonftbAwardsService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/** 
 * 导出Excel 
 * @return 
 *  
 */ 
@RequestMapping(value = "/exportExcel")
public AjaxJson exportExcel(HttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	response.setCharacterEncoding("utf-8");
    response.setContentType("multipart/form-data");
    String fileName = "导出信息.xls";  
    try {  
    	response.setHeader("Content-disposition", "attachment; filename="  
				   + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
    	String actId =  ContextHolderUtils.getRequest().getParameter("actId");//返回时的url
        InputStream inputStream = wxActCommonftbAwardsService.exportExcel(actId); 
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[2048];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
         // 这里主要关闭。
        os.close();
        inputStream.close();
        j.setMsg("导出成功");
    } catch (Exception e) {  
        e.printStackTrace();  
       // logger.error(ExceptionUtil.getExceptionMessage(e));  
        j.setSuccess(false);
		j.setMsg("导出失败");
    }  
    return j;
}  
}

