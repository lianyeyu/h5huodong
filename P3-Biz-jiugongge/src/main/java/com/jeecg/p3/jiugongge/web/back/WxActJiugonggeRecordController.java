package com.jeecg.p3.jiugongge.web.back;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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
import com.jeecg.p3.jiugongge.entity.WxActJiugonggeRecord;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeAwardsService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeRecordService;
import com.jeecg.p3.jiugongge.service.WxActJiugonggeService;
import com.jeecg.p3.jiugongge.util.ContextHolderUtils;
import com.jeecg.p3.jiugongge.util.ExcelUtil;

 /**
 * 描述：</b>WxActJiugonggeRecordController<br>砍价帮砍记录表
 * @author pituo
 * @since：2015年12月17日 18时41分09秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/jiugongge/back/wxActJiugonggeRecord")
public class WxActJiugonggeRecordController extends BaseController{
  @Autowired
  private WxActJiugonggeRecordService wxActJiugonggeRecordService;
  @Autowired
  private WxActJiugonggeAwardsService wxActJiugonggeAwardsService;
  @Autowired
  private WxActJiugonggeService wxActJiugonggeService;
/**
  * 中奖列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActJiugonggeRecord query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActJiugonggeRecord> pageQuery = new PageQuery<WxActJiugonggeRecord>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	 	query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("wxActJiugonggeRecord",query);
		List<WxActJiugongge> acts = (List<WxActJiugongge>) wxActJiugonggeService.queryActs(jwid);//活动
		velocityContext.put("acts",acts);
		List<WxActJiugonggeAwards> awards = wxActJiugonggeAwardsService.queryAwards(jwid);//奖项
		velocityContext.put("awards",awards);
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActJiugonggeRecordService.queryPageList(pageQuery)));
		String viewName = "jiugongge/back/wxActJiugonggeRecord-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}
/**
 * 抽奖列表页面
 * @return
 */
@RequestMapping(value="listForJoin",method = {RequestMethod.GET,RequestMethod.POST})
public void listForJoin(@ModelAttribute WxActJiugonggeRecord query,HttpServletResponse response,HttpServletRequest request,
		@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
		@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	PageQuery<WxActJiugonggeRecord> pageQuery = new PageQuery<WxActJiugonggeRecord>();
	pageQuery.setPageNo(pageNo);
	pageQuery.setPageSize(pageSize);
	VelocityContext velocityContext = new VelocityContext();
	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	query.setJwid(jwid);
	pageQuery.setQuery(query);
	velocityContext.put("wxActJiugonggeRecord",query);
	List<WxActJiugongge> acts = (List<WxActJiugongge>) wxActJiugonggeService.queryActs(jwid);//活动
	velocityContext.put("acts",acts);
	List<WxActJiugonggeAwards> awards = wxActJiugonggeAwardsService.queryAwards(jwid);//奖项
	velocityContext.put("awards",awards);
	String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
	velocityContext.put("backurl",backurl);
	velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActJiugonggeRecordService.queryPageListForJoin(pageQuery)));
	String viewName = "jiugongge/back/wxActJiugonggeRecord-list.vm";
	ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActJiugonggeRecordDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/back/wxActJiugonggeRecord-detail.vm";
		WxActJiugonggeRecord wxActJiugonggeRecord = wxActJiugonggeRecordService.queryById(id);
		velocityContext.put("wxActJiugonggeRecord",wxActJiugonggeRecord);
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
	 String viewName = "jiugongge/back/wxActJiugonggeRecord-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActJiugonggeRecord wxActJiugonggeRecord){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggeRecordService.doAdd(wxActJiugonggeRecord);
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
		 WxActJiugonggeRecord wxActJiugonggeRecord = wxActJiugonggeRecordService.queryById(id);
		 velocityContext.put("wxActJiugonggeRecord",wxActJiugonggeRecord);
		 String viewName = "jiugongge/back/wxActJiugonggeRecord-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActJiugonggeRecord wxActJiugonggeRecord){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggeRecordService.doEdit(wxActJiugonggeRecord);
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
			wxActJiugonggeRecordService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * 导出
 * @return
 */
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
    	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
    	String actId =  ContextHolderUtils.getRequest().getParameter("actId");//返回时的url
        InputStream inputStream = wxActJiugonggeRecordService.exportExcel(actId,jwid); 
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

