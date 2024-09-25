package com.model2.mvc.view.product;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

//��ǰ ��� ��ȸ ��û
public class ListProductAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String role = "";
		
		Search search=new Search();
		
		HttpSession session = request.getSession();
		
		
		//��û �Ķ���Ϳ��� ������ ��ȣ�� �����ͼ� page ������ ����, �⺻���� 1.
		int currentPage = 1;
		System.out.println(request.getParameter("currentPage"));
		//"menu" �Ķ���Ͱ� �ִ� ���, �� ���� ���� role�� "manage" �Ǵ� "search"�� �����ϰ� ���ǿ� ����
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			if(request.getParameter("menu")==null){
				session.getAttribute("menuType");
			}
		} else {
			if(request.getParameter("menu")!=null){
				if(request.getParameter("menu").equals("manage")){
					role = "manage";
					System.out.println("��ǰ ����");
					
				} else {
					role = "search";
					System.out.println("��ǰ �˻�");
				}
				session.setAttribute("menuType", role);
			} else {
				//"menu" �Ķ���Ͱ� ���� ���, "��ǰ�޴� parameter : null"�� �ֿܼ� ���
				System.out.println("��ǰ�޴� parameter : null");
			}
			
		}
		
		/*
		int currentPage=1;

		if(request.getParameter("currentPage") != null){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		*/
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		
		// web.xml  meta-data �� ���� ��� ���� 
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		// Business logic ����
		ProductService productService=new ProductServiceImpl();
		Map<String , Object> map=productService.getProductList(search);
		
		Page resultPage	= 
					new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListProductAction ::"+resultPage);
		
		// Model �� View ����
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
}