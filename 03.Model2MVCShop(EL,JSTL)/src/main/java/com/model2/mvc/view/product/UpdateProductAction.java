package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action{

	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		String prodNo = request.getParameter("prodNo");
		//int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Product product= new Product();
		product.setProdNo(Integer.parseInt(prodNo));
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		product.setManuDate(request.getParameter("manuDate").replaceAll("-", ""));
		
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(product);
		
		/*
		HttpSession session=request.getSession();
		int sessionId=((Product)session.getAttribute("product")).getProdNo();
		
		if(sessionId==prodNo){
			session.setAttribute("product", product);
		}
		*/
		
		//return "forward:/updateProduct.jsp";
		return "redirect:/getProduct.do?prodNo="+prodNo;
	}
	
}
