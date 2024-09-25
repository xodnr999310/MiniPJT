package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

//��ǰ ��� ��û
public class AddProductAction extends Action{
	
	public String execute(HttpServletRequest request,
												HttpServletResponse response) throws Exception{

		ProductVO prodVO = new ProductVO();
		prodVO.setProdName(request.getParameter("prodName"));
		prodVO.setProdDetail(request.getParameter("prodDetail"));
		// .replaceAll() -> ��� ���ڿ��� ���ϴ� ���� ������ ��ȯ�ϴ� �Լ�
		// ~.replaceAll("��ȯ�ϰ��� �ϴ� ���","��ȯ�� ���� ��");
		prodVO.setManuDate(request.getParameter("manuDate").replaceAll("-","")); 
		prodVO.setPrice(Integer.parseInt(request.getParameter("price")));
		prodVO.setFileName(request.getParameter("fileName"));
		
		System.out.println(prodVO);
		
		ProductService service = new ProductServiceImpl();
		service.addProduct(prodVO);
		
		request.setAttribute("vo", prodVO);
		
		return "forward:/product/getProduct.jsp";
	}
	

}
