package com.barclouds.model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.barclouds.dao.impl.DataDao;
import com.barclouds.dao.impl.UserDao;
import com.barclouds.entity.User;
import com.barclouds.service.DataService;
import com.barclouds.service.UserService;
import com.barclouds.util.IModel;

/**
 * 实现统一规定的模型
 * 
 * @author Sugood
 * 
 */
public class DeleteDataAction implements IModel {
	// 获得数据库操作的DAO
	private DataDao dataDao;
	// 获取日期操作类
	private String gotoUrl="";
	
	public DeleteDataAction() {
		dataDao = new DataDao();
	}

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
        try{
    		// 接收参数
    		String id = request.getParameter("id");
    		String flag = request.getParameter("flag");
    		String uid = request.getParameter("uid");
    		
    		// 调用业务层处理
    		DataService cs = new DataService();
    		if(flag.equals("1")){
//    			System.out.println("删除所有数据，uid="+uid);
    			// 删除当前用户所有的数据
	    		if(cs.deleteByUid(uid)){
	    			// 删除成功了
	    			// 再去访问dataListByPage
	    			//这样访问体验不是很好
	    			gotoUrl ="system/dataListByPage.jsp";
	//    			response.sendRedirect(request.getContextPath()+"/dataListByPage.do?actionName=dataListByPageAction&pc=1");
	    		}
    		}else if(flag.equals("0")){
	    		// 删除当前数据
	    		if(cs.deleteByid(id)){
	    			// 删除成功了
	    			// 再去访问dataListByPage
	    			//这样访问体验不是很好
	    			gotoUrl ="system/dataListByPage.jsp";
	//    			response.sendRedirect(request.getContextPath()+"/dataListByPage.do?actionName=dataListByPageAction&pc=1");
	    		}
    		}

        }catch(Exception e){
        	e.printStackTrace();
    		gotoUrl ="system/dataListByPage.jsp";
        }
        return gotoUrl;
	}
}
