package presenter;

import dto.UnitPriceDTO;

public interface MainPresenterCallBack {
	
	/**
	 * 单价信息回调
	 * @param unitPrice 单价信息
	 * @param unitName 商品名称
	 */
	void unitPriceRecived(UnitPriceDTO unitPrice);
	
	/**
	 * 服务器处理请求出错回调
	 * @param failInfo 通信失败提示信息
	 */
	void messageFailed(String failInfo);//
	
	/**服务器处理请求成功
	 * @param successInfo 成功
	 */
	void messageSuccessed(String successInfo);
	
}
