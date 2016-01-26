package com.jeecg.p3.jiugongge.web.back;

import java.io.File;
import java.io.PrintWriter;

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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecg.p3.jiugongge.entity.WxActJiugonggePrizes;
import com.jeecg.p3.jiugongge.service.WxActJiugonggePrizesService;
import com.jeecg.p3.jiugongge.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActJiugonggePrizesController<br>配置
 * @author junfeng.zhou
 * @since：2015年11月16日 11时07分12秒 星期一 
 * @version:1.0
 */
@Controller
@RequestMapping("/jiugongge/back/wxActJiugonggePrizes")
public class WxActJiugonggePrizesController extends BaseController{
  @Autowired
  private WxActJiugonggePrizesService wxActJiugonggePrizesService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActJiugonggePrizes query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActJiugonggePrizes> pageQuery = new PageQuery<WxActJiugonggePrizes>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
	 	String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();		
	 	query.setJwid(jwid);
		pageQuery.setQuery(query);
		velocityContext.put("query",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActJiugonggePrizesService.queryPageList(pageQuery)));
		velocityContext.put("jwid",jwid);
		String viewName = "jiugongge/back/wxActJiugonggePrizes-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActJiugonggePrizesDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/back/wxActJiugonggePrizes-detail.vm";
		WxActJiugonggePrizes wxActJiugonggePrizes = wxActJiugonggePrizesService.queryById(id);
		velocityContext.put("wxActJiugonggePrizes",wxActJiugonggePrizes);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 velocityContext.put("jwid",jwid);
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 String sessionId = ContextHolderUtils.getSession().getId();
	 velocityContext.put("sessionId",sessionId);
	 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
	 velocityContext.put("jwid",jwid);
	 String viewName = "jiugongge/back/wxActJiugonggePrizes-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActJiugonggePrizes wxActJiugonggePrizes){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggePrizesService.doAdd(wxActJiugonggePrizes);
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
		 WxActJiugonggePrizes wxActJiugonggePrizes = wxActJiugonggePrizesService.queryById(id);
		 velocityContext.put("wxActJiugonggePrizes",wxActJiugonggePrizes);
		 String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
		 velocityContext.put("jwid",jwid);
		 String viewName = "jiugongge/back/wxActJiugonggePrizes-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActJiugonggePrizes wxActJiugonggePrizes){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggePrizesService.doEdit(wxActJiugonggePrizes);
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
			//判断奖项是否被使用
			Boolean used=wxActJiugonggePrizesService.validUsed(id);
			if(used){
				j.setSuccess(false);
				j.setMsg("该奖品已经被活动使用，不能删除");
			}else{	
				wxActJiugonggePrizesService.doDelete(id);
			j.setMsg("删除成功");
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doUpload",method ={RequestMethod.POST})
@ResponseBody
public AjaxJson doUpload(MultipartHttpServletRequest request,HttpServletResponse response){
	AjaxJson j = new AjaxJson();
	try {
		MultipartFile uploadify = request.getFile("file");
        byte[] bytes = uploadify.getBytes();  
        String realFilename=uploadify.getOriginalFilename();
        String fileNoExtension = realFilename.substring(0,realFilename.lastIndexOf("."));
        String fileExtension = realFilename.substring(realFilename.lastIndexOf("."));
        String filename=fileNoExtension+System.currentTimeMillis()+fileExtension;
        String jwid =  ContextHolderUtils.getSession().getAttribute("jwid").toString();
        String uploadDir = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("upload/img/jiugongge/"+jwid);   
        File dirPath = new File(uploadDir);  
        if (!dirPath.exists()) {  
            dirPath.mkdirs();  
        }  
        String sep = System.getProperty("file.separator");  
        File uploadedFile = new File(uploadDir + sep  
                + filename);  
        FileCopyUtils.copy(bytes, uploadedFile);  
        j.setObj(filename);
        j.setSuccess(true);
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}


}

