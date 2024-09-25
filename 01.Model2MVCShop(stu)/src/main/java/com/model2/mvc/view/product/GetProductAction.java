package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

//��ǰ �� ���� ��û
public class GetProductAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		ProductVO vo = service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);

		
		return "forward:/product/getProduct.jsp";
		
		/*
		//�� ��¥ �������;; --> �����ؼ�
		
		//HTTP ��û���� "prodNo"��� �̸��� �Ķ���͸� �����ͼ� ���ڿ��� ����. 
		//�� ���ڿ��� ������ ��ȯ�Ͽ� prodNo��� ������ ����.
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
	
		//ProductService �������̽��� ������ ProductServiceImpl Ŭ������ �ν��Ͻ��� ����.
		//service��� ������ �� �ν��Ͻ��� ����. �� ��ü�� ��ǰ�� ���õ� ����Ͻ� ������ ó��.
		ProductService service = new ProductServiceImpl();
		
		//service ��ü�� getProduct �޼��带 ȣ���Ͽ� prodNo�� �ش��ϴ� ��ǰ ������ ������.
		//�� ������ vo��� ������ ����. ProductVO�� ��ǰ ������ ��� ��ü.
		ProductVO vo = service.getProduct(prodNo);
		
		//vo ��ü���� ��ǰ ��ȣ(prodNo)�� ������ ���ڿ��� ��ȯ�� ��, 
		//���� ������("/")�� �߰��Ͽ� strPN��� ���ڿ� ������ ����.
		String strPN = String.valueOf(vo.getProdNo()); //+"/";

		//HTTP ��û���� Ŭ���̾�Ʈ�� ���� ��� ��Ű�� �迭�� ������.
		Cookie[] cookies = request.getCookies();
		
		//�� �ڵ�� ���������� strPN�̶�� �̸��� ��Ű�� ã�µ�,
		//���� �� ��Ű�� ������, ��Ű�� ���� strPN���� ������Ʈ��. 
		//��Ű�� ������, ���ο� strPN ��Ű�� �����ϰ� �̸� �������� �����Ͽ� �����ϰ� ��.
		//�߿��� ��: ���ڿ� �񱳸� �� ���� != ��� equals �޼��带 ����ϴ� ���� ����.
		// !=�� ���� �񱳸� �ϹǷ� ���ڿ� ���� �񱳰� �ƴ�.
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
		
		//vo ��ü�� ��û �Ӽ��� �����Ͽ�, ������ JSP ���������� ����� �� �ֵ��� ��. 
		//�̴� �ַ� JSP���� �����͸� ǥ���ϴ� �� ����.
		request.setAttribute("vo", vo);

		return "forward:/product/getProduct.jsp";
		*/
		
	}
}