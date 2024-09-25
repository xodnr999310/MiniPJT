package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;

//��ǰ ���� ������ ���� ȭ���û
public class UpdateProductViewAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String prodNo = (String)request.getParameter("prodNo");
		
		ProductService service = new ProductServiceImpl();
		Product product = service.getProduct(Integer.parseInt(prodNo));
		
		//.setAttribute("�Ӽ��� �̸�", �Ӽ���);
		request.setAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
}