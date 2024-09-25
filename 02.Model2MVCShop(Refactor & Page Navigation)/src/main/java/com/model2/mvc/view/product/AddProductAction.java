package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

//��ǰ ��� ��û
public class AddProductAction extends Action{
	
	public String execute(HttpServletRequest request,
												HttpServletResponse response) throws Exception{

		Product prod = new Product();
		prod.setProdName(request.getParameter("prodName"));
		prod.setProdDetail(request.getParameter("prodDetail"));
		// .replaceAll() -> ��� ���ڿ��� ���ϴ� ���� ������ ��ȯ�ϴ� �Լ�
		// ~.replaceAll("��ȯ�ϰ��� �ϴ� ���","��ȯ�� ���� ��");
		prod.setManuDate(request.getParameter("manuDate").replaceAll("-","")); 
		prod.setPrice(Integer.parseInt(request.getParameter("price")));
		prod.setFileName(request.getParameter("fileName"));
		
		System.out.println("AddProductAction :: "+prod);
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(prod);
		
		request.setAttribute("prod", prod);
		
		return "forward:/product/addProduct.jsp";
		//return "redirect:/product/addProduct.jsp";
	}
	

}
