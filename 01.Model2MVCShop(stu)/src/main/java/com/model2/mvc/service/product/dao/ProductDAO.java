package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {

	public ProductDAO() {
	}
	
	//��ǰ����� ���� DBMS ����
	public void insertProduct(ProductVO prodVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into product values (seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, prodVO.getProdName());
		stmt.setString(2, prodVO.getProdDetail());
		stmt.setString(3, prodVO.getManuDate());
		stmt.setInt(4, prodVO.getPrice());
		stmt.setString(5, prodVO.getFileName());
		
		stmt.executeUpdate();

		con.close();
	}
	
	//��ǰ ���� ��ȸ�� ���� DBMS ����
	public ProductVO findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from product where prod_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);		
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();
		
		ProductVO prodVO = null;
		while(rs.next()) {
			
			prodVO = new ProductVO();	
			prodVO.setProdNo(rs.getInt("prod_no"));
			prodVO.setProdName(rs.getString("prod_name"));
			prodVO.setProdDetail(rs.getString("prod_detail"));
			prodVO.setManuDate(rs.getString("manufacture_day"));
			prodVO.setPrice(rs.getInt("price"));
			prodVO.setFileName(rs.getString("image_file"));
			prodVO.setRegDate(rs.getDate("reg_date"));
			
		}
		
		con.close();
		
		return prodVO;
	}
	
	//�������;;
	//��ǰ��� ��ȸ�� ���� DBMS ����
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product ";
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no=" + searchVO.getSearchKeyword();
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name='" + searchVO.getSearchKeyword()
						+ "'";
			}else if (searchVO.getSearchCondition().equals("2")) {
				sql += " WHERE price=" + searchVO.getSearchKeyword();
			}
		}
		sql += " ORDER BY prod_no";
		
		/*
		StringBuffer sql = new StringBuffer();
		//StringBuilder sql = new StringBuilder();
		sql.append("select proj.rnum, prod.prod_no, prod.prod_name, prod.price, prod.reg_date, nvl2(tran.tran_status_code, tran.tran_status_code, 0) tran_code, proj.count ");
		sql.append("from product prod, transaction tran, (select prod_no, rownum rnum, count(*)over() count from product) proj ");
		sql.append("where proj.prod_no = prod.prod_no and prod.prod_no=tran.prod_no(+) and proj.rnum between ? and ? ");
		
		
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql.append(" and prod.prod_no="+searchVO.getSearchKeyword());
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql.append(" and prod.prod_name like'%"+ searchVO.getSearchKeyword()+"'");
			}else if (searchVO.getSearchCondition().equals("2")) {
				sql.append(" and price="+searchVO.getSearchKeyword());
			}
			sql.append(" order by prod_no");
		}
		*/
		/*
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += " where prod_no= " +searchVO.getSearchKeyword();
			}else if(searchVO.getSearchCondition().equals("1")) {
				sql += " where prod_name= '" +searchVO.getSearchKeyword()
						+ "'";
			}else if(searchVO.getSearchCondition().equals("2")) {
				sql += " where price=" +searchVO.getSearchKeyword();
			}
		}
		sql += " order by prod_no";
		*/
			
		// 1. ResultSet.TYPE_SCROLL_INSENSITIVE
		// rs.last()�� ����ϸ� ��������� ����� �� ����
		// ResultSet.TYPE_SCROLL_INSENSITIVE ��� �� ������ ��� �ٽ� ��밡��
		// Ŀ�� �̵��� �����Ӱ� �ϰ� ��ũ���� ���������� ����� ������ ���� X
		
		// 2. ResultSet.CONCUR_UPDATABLE
		// ������ ������ �����ϵ��� �Ѵ�.
		
		
		
		PreparedStatement stmt = con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);

		ResultSet rs = stmt.executeQuery();
		
		rs.last();
		int total = rs.getRow();
		System.out.println("�ο��� ��:" + total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("prod_no"));
				vo.setProdName(rs.getString("prod_name"));
				vo.setPrice(rs.getInt("price"));
				vo.setRegDate(rs.getDate("reg_date"));
				//vo.setProTranCode(rs.getString("tran_code").trim()); //trim() -> ���ڿ� ��������
				
				list.add(vo);
				if(!rs.next()) {
					break;
				}
			}
		}
		
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
		}
	
	
	//��ǰ���� ������ ���� DBMS ����
	public void updateProduct(ProductVO prodVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update product set prod_name=?,prod_detail=?,price=?,manufacture_day=?, image_file =? where prod_no=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, prodVO.getProdName());
		stmt.setString(2, prodVO.getProdDetail());
		stmt.setInt(3, prodVO.getPrice());
		stmt.setString(4, prodVO.getManuDate());
		stmt.setString(5, prodVO.getFileName());
		stmt.setInt(6, prodVO.getProdNo());
		stmt.executeUpdate();

		con.close();
		
	}
}