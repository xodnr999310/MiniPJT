package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {

	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception;
		
	public PurchaseVO getPurchase(int tranNo) throws Exception;
	
	public Map<String, Object> getPurchaseList(SearchVO searchVO,String buyer) throws Exception;
	
	public Map<String, Object> getSaleList(SearchVO searchVO) throws Exception;
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception;
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception;
	
}
