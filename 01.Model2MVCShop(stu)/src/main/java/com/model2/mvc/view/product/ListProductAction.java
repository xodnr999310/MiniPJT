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

//상품 목록 조회 요청
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
		
		//진짜 개어려움;; --> 한줄해석
		
		//빈 문자열 role을 선언
		//이후에 이 변수에 "manage" 또는 "search"와 같은 역할(role)을 저장할 예정
		String role = "";
		
		//SearchVO 객체를 생성
		//검색 조건 및 페이지 정보를 저장하는 데 사용됨
		SearchVO searchVO = new SearchVO();
		
		//현재 요청의 세션을 가져옴
		//세션은 클라이언트와 서버 간의 상태를 유지하는 데 사용됨
		HttpSession session = request.getSession();
		
		
		//요청 파라미터에서 페이지 번호를 가져와서 page 변수에 저장, 기본값은 1.
		int page = 1;
		//"menu" 파라미터가 있는 경우, 그 값에 따라 role을 "manage" 또는 "search"로 설정하고 세션에 저장
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			if(request.getParameter("menu")==null){
				session.getAttribute("menuType");
			}
		} else {
			if(request.getParameter("menu")!=null){
				if(request.getParameter("menu").equals("manage")){
					role = "manage";
					System.out.println("상품 관리");
					
				} else {
					role = "search";
					System.out.println("상품 검색");
				}
				session.setAttribute("menuType", role);
			} else {
				//"menu" 파라미터가 없는 경우, "상품메뉴 parameter : null"을 콘솔에 출력
				System.out.println("상품메뉴 parameter : null");
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
