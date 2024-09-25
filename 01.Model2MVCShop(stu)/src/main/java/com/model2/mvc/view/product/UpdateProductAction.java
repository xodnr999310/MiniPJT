package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

//상품 정보 수정 요청
public class UpdateProductAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String prodNo = request.getParameter("prodNo");
		
		ProductVO prodVO = new ProductVO();
		prodVO.setProdNo(Integer.parseInt(prodNo));
		prodVO.setProdName(request.getParameter("prodName"));
		prodVO.setProdDetail(request.getParameter("prodDetail"));
		prodVO.setPrice(Integer.parseInt(request.getParameter("price")));
		prodVO.setFileName(request.getParameter("fileName"));
		prodVO.setManuDate(request.getParameter("manuDate"));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(prodVO);
		
		return "redirect:/getProduct.do?prodNo="+prodNo;
	}

}