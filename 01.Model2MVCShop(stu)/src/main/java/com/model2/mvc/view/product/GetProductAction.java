package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

//상품 상세 보기 요청
public class GetProductAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		ProductVO vo = service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);

		
		return "forward:/product/getProduct.jsp";
		
		/*
		//와 진짜 개어려움;; --> 한줄해석
		
		//HTTP 요청에서 "prodNo"라는 이름의 파라미터를 가져와서 문자열로 받음. 
		//그 문자열을 정수로 변환하여 prodNo라는 변수에 저장.
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
	
		//ProductService 인터페이스를 구현한 ProductServiceImpl 클래스의 인스턴스를 생성.
		//service라는 변수에 이 인스턴스를 저장. 이 객체는 상품에 관련된 비즈니스 로직을 처리.
		ProductService service = new ProductServiceImpl();
		
		//service 객체의 getProduct 메서드를 호출하여 prodNo에 해당하는 상품 정보를 가져옴.
		//이 정보를 vo라는 변수에 저장. ProductVO는 상품 정보를 담는 객체.
		ProductVO vo = service.getProduct(prodNo);
		
		//vo 객체에서 상품 번호(prodNo)를 가져와 문자열로 변환한 후, 
		//끝에 슬래시("/")를 추가하여 strPN라는 문자열 변수에 저장.
		String strPN = String.valueOf(vo.getProdNo()); //+"/";

		//HTTP 요청에서 클라이언트가 보낸 모든 쿠키를 배열로 가져옴.
		Cookie[] cookies = request.getCookies();
		
		//이 코드는 브라우저에서 strPN이라는 이름의 쿠키를 찾는데,
		//만약 이 쿠키가 있으면, 쿠키의 값을 strPN으로 업데이트함. 
		//쿠키가 없으면, 새로운 strPN 쿠키를 생성하고 이를 브라우저에 전송하여 저장하게 함.
		//중요한 점: 문자열 비교를 할 때는 != 대신 equals 메서드를 사용하는 것이 좋다.
		// !=는 참조 비교를 하므로 문자열 내용 비교가 아님.
		for(Cookie c : cookies) {
			if (c.getName().equals("strPN")) {
				String check = c.getValue();
				if(check != strPN) {
					c.setValue(check.concat(strPN));
					response.addCookie(c);
				} 
			} else {
				response.addCookie(new Cookie("strPN", strPN));
			}
		}
		
		//vo 객체를 요청 속성에 저장하여, 이후의 JSP 페이지에서 사용할 수 있도록 함. 
		//이는 주로 JSP에서 데이터를 표시하는 데 사용됨.
		request.setAttribute("vo", vo);

		return "forward:/product/getProduct.jsp";
		*/
		
	}
}