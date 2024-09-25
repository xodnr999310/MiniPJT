package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

//��ǰ ���� ������ ���� ȭ���û
public class UpdateProductViewAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String prodNo = (String)request.getParameter("prodNo");
		
		ProductService service = new ProductServiceImpl();
		ProductVO prodVO = service.getProduct(Integer.parseInt(prodNo));
		
		//.setAttribute("�Ӽ��� �̸�", �Ӽ���);
		request.setAttribute("productVO", prodVO);
		
		return "forward:/product/updateProductView.jsp";
	}
}