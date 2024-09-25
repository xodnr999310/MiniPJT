package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.dao.UserDAO;

public class PurchaseDAO {

	public PurchaseDAO() {
	}
	
	//���Ÿ� ���� DBMS ����
	public void insertPurchase(PurchaseVO purVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into transaction values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purVO.getBuyer().getUserId());
		stmt.setString(3,purVO.getPaymentOption());
		stmt.setString(4,purVO.getReceiverName());
		stmt.setString(5, purVO.getReceiverPhone());
		stmt.setString(6, purVO.getDivyAddr());
		stmt.setString(7, purVO.getDivyRequest());
		stmt.setString(8, purVO.getTranCode()); //???
		stmt.setString(9, purVO.getDivyDate());
		stmt.executeUpdate();
		
		con.close();
	}
	
	//�������� �� ��ȸ�� ���� DBMS ����
	public PurchaseVO findPurchase(int tranNo) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from transaction where tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);
		
		ResultSet rs = stmt.executeQuery();
		
		PurchaseVO purVO = null;
		while(rs.next()) {
			
			purVO = new PurchaseVO();
			purVO.setTranNo(rs.getInt("tran_no"));
			purVO.setPurchaseProd(new ProductDAO().findProduct(rs.getInt("prod_no")));
			purVO.setBuyer(new UserDAO().findUser(rs.getString("user_id")));
			purVO.setPaymentOption(rs.getString("payment_option"));
			purVO.setReceiverName(rs.getString("receiver_name"));
			purVO.setReceiverPhone(rs.getString("receiver_phone"));
			purVO.setDivyAddr(rs.getString("demailaddr"));
			purVO.setDivyRequest(rs.getString("dlvy_request"));
			purVO.setTranCode(rs.getString("tran_status_code"));
			purVO.setDivyDate(rs.getString("order_data"));
			
		}
		
		con.close();
		
		return purVO;
	}
	
	//���Ÿ�� ���⸦ ���� DBMS ����
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO) throws Exception{
		return null;	
	}
	
	//�ǸŸ�� ���⸦ ����DBMS ����
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception{
		return null;
	}
	
	//�������� ������ ���� DBMS ����
	public void updatePurchase(PurchaseVO purVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "update transaction set prod_no=?,buyer_id=?,payment_option=?,receiver_name=?,receiver_phone=?,demailaddr=?,dlvy_request=?,tran_status_code=?,dlvy_date=? where tran_no=?";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setInt(1, purVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purVO.getBuyer().getUserId());
		stmt.setString(3, purVO.getPaymentOption());
		stmt.setString(4, purVO.getReceiverName());
		stmt.setString(5, purVO.getReceiverPhone());
		stmt.setString(6, purVO.getDivyAddr());
		stmt.setString(7, purVO.getDivyRequest());
		stmt.setString(8, purVO.getTranCode()); // updateTranCode���� �ϴ°� �ƴѰ�;; ���⼭ �ϴ� ������ ����?
		stmt.setString(9, purVO.getDivyDate());
		stmt.setInt(10, purVO.getTranNo());
		
		con.close();
	}
	
	//���� ���� �ڵ� ������ ���� DBMS ����
	public void updateTranCode(PurchaseVO purVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		
	}
}
