package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

//상품 등록 요청
public class AddProductAction extends Action{
	
	public String execute(HttpServletRequest request,
												HttpServletResponse response) throws Exception{

		Product product = new Product();
		product.setProdName(request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		// .replaceAll() -> 대상 문자열을 원하는 문자 값으로 변환하는 함수
		// ~.replaceAll("변환하고자 하는 대상","변환할 문자 값");
		product.setManuDate(request.getParameter("manuDate").replaceAll("-","")); 
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		product.setFileName(request.getParameter("fileName"));
		
		System.out.println("AddProductAction :: "+product);
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(product);
		
		request.setAttribute("product", product);
		
		return "forward:/product/addProduct.jsp";
		//return "redirect:/product/addProduct.jsp";
	}
	

}
