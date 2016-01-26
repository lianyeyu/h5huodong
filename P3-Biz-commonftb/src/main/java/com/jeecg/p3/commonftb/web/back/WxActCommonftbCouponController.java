package com.jeecg.p3.commonftb.web.back;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jeecg.p3.commonftb.entity.WxActCommonftbCoupon;
import com.jeecg.p3.commonftb.service.WxActCommonftbCouponService;
import com.jeecg.p3.commonftb.util.ContextHolderUtils;

 /**
 * 描述：</b>WxActCommonftbCouponController<br>卡券配置表
 * @author pituo
 * @since：2016年01月07日 15时56分15秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/commonftb/back/wxActCommonftbCoupon")
public class WxActCommonftbCouponController extends BaseController{
  @Autowired
  private WxActCommonftbCouponService wxActCommonftbCouponService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActCommonftbCoupon query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActCommonftbCoupon> pageQuery = new PageQuery<WxActCommonftbCoupon>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("wxActCommonftbCoupon",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActCommonftbCouponService.queryPageList(pageQuery)));
		String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
		velocityContext.put("backurl",backurl);
		String actName =  ContextHolderUtils.getRequest().getParameter("actName");//返回时的url
		velocityContext.put("actName",actName);
		String viewName = "commonftb/back/wxActCommonftbCoupon-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActCommonftbCouponDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "commonftb/back/wxActCommonftbCoupon-detail.vm";
		WxActCommonftbCoupon wxActCommonftbCoupon = wxActCommonftbCouponService.queryById(id);
		velocityContext.put("wxActCommonftbCoupon",wxActCommonftbCoupon);
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
	 String viewName = "commonftb/back/wxActCommonftbCoupon-add.vm";
	 String backurl =  ContextHolderUtils.getRequest().getParameter("backurl");//返回时的url
	 velocityContext.put("backurl",backurl);
	 String actId =  ContextHolderUtils.getRequest().getParameter("actId");//返回时的url
	 velocityContext.put("actId",actId);
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActCommonftbCoupon wxActCommonftbCoupon){
	AjaxJson j = new AjaxJson();
	try {
		 String actId =  ContextHolderUtils.getRequest().getParameter("actId");//返回时的url
		 wxActCommonftbCoupon.setActId(actId);
		wxActCommonftbCouponService.doAdd(wxActCommonftbCoupon);
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
		 WxActCommonftbCoupon wxActCommonftbCoupon = wxActCommonftbCouponService.queryById(id);
		 velocityContext.put("wxActCommonftbCoupon",wxActCommonftbCoupon);
		 String viewName = "commonftb/back/wxActCommonftbCoupon-edit.vm";
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
public AjaxJson doEdit(@ModelAttribute WxActCommonftbCoupon wxActCommonftbCoupon){
	AjaxJson j = new AjaxJson();
	try {
		wxActCommonftbCouponService.doEdit(wxActCommonftbCoupon);
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
			wxActCommonftbCouponService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}
/**
 * 导入
 * @return
 */
@RequestMapping(value = "/doImportExcel",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doImportExcel(WxActCommonftbCoupon wxActCommonftbCoupon){
	AjaxJson j = new AjaxJson();
	try {
		 MultipartFile  multipartFile = wxActCommonftbCoupon.getFiledata();
	        CommonsMultipartFile cf= (CommonsMultipartFile)multipartFile; 
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	        File f = fi.getStoreLocation();
		wxActCommonftbCouponService.importExcel(wxActCommonftbCoupon.getFiledataFileName(), f,wxActCommonftbCoupon.getActId()); 
		j.setMsg("导入成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("请检查卡券id是否重复");
	}
	return j;
}
/**
 * 模版下载
 * @return
 */
@RequestMapping(value = "/downloadTemplate")
public String downloadTemplate( HttpServletRequest request,
        HttpServletResponse response){
	response.setCharacterEncoding("utf-8");
    response.setContentType("multipart/form-data");
    String realName="卡券导入模版.xls";
		   try {
			   response.setHeader("Content-disposition", "attachment; filename="  
					   + new String(realName.getBytes("utf-8"), "ISO8859-1"));
			   String path = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("template/couponTemplate.xls");
			   InputStream inputStream = new FileInputStream(new File(path));
	            OutputStream os = response.getOutputStream();
	            byte[] b = new byte[2048];
	            int length;
	            while ((length = inputStream.read(b)) > 0) {
	                os.write(b, 0, length);
	            }
	             // 这里主要关闭。
	            os.close();
	            inputStream.close();
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
}
//@RequestMapping(value = "/downloadTemplate")
//@ResponseBody
//public ResponseEntity<byte[]> downloadTemplate(){
//	try {
//		String path = ContextHolderUtils.getRequest().getSession().getServletContext().getRealPath("template/couponTemplate.xls");
//		File file=new File(path);  
//		HttpHeaders headers = new HttpHeaders();    
//		String fileName=new String("卡券导入模版.xls".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
//		headers.setContentDispositionFormData("attachment", fileName);   
//		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
//		
//		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
//	}catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//		return null;
//	} 
//	
//}


}

