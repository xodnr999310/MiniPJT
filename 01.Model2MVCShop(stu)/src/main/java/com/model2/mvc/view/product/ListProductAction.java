package com.model2.mvc.view.product;

//import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

//��ǰ ��� ��ȸ ��û
public class ListProductAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		SearchVO searchVO=new SearchVO();
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		ProductService service=new ProductServiceImpl();
		Map<String,Object> map=service.getProductList(searchVO);

		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/product/listProduct.jsp";
		*/
		
		//��¥ �������;; --> �����ؼ�
		
		//�� ���ڿ� role�� ����
		//���Ŀ� �� ������ "manage" �Ǵ� "search"�� ���� ����(role)�� ������ ����
		String role = "";
		
		//SearchVO ��ü�� ����
		//�˻� ���� �� ������ ������ �����ϴ� �� ����
		SearchVO searchVO = new SearchVO();
		
		//���� ��û�� ������ ������
		//������ Ŭ���̾�Ʈ�� ���� ���� ���¸� �����ϴ� �� ����
		HttpSession session = request.getSession();
		
		
		//��û �Ķ���Ϳ��� ������ ��ȣ�� �����ͼ� page ������ ����, �⺻���� 1.
		int page = 1;
		//"menu" �Ķ���Ͱ� �ִ� ���, �� ���� ���� role�� "manage" �Ǵ� "search"�� �����ϰ� ���ǿ� ����
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
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
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		searchVO.setPageUnit(Integer.parseInt(getServletContext().getInitParameter("pageSize")));
		
		ProductService service = new ProductServiceImpl();
		Map<String, Object> map = service.getProductList(searchVO);
		
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		
		return "forward:/product/listProduct.jsp";
		
	}
}
