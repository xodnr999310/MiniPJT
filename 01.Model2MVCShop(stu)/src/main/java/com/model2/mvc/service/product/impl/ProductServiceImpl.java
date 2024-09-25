package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO = new ProductDAO();
	}
	
	public void addProduct(ProductVO prodVO) throws Exception{
		productDAO.insertProduct(prodVO);

	}
	
	public ProductVO getProduct(int prod) throws Exception{
		return productDAO.findProduct(prod);
	}
	
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}
	
	public void updateProduct(ProductVO prodVO) throws Exception{
		productDAO.updateProduct(prodVO);

	}
	
	
	
	

}
