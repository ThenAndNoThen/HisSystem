package model;

import dto.BillDTO;
import presenter.MainPresenterCallBack;

public interface MainModel {
	/**
	 * 设置回调
	 * @param dataReceivedCallBack 数据回调接口
	 */
	void setDataCallBack(MainPresenterCallBack CallBack);
	
	/**
	 * 从服务器获取单价信息
	 * @param unitName 商品名称
	 */
	void getUnitPrice(String unitName);
	
	/**
	 * 保存单据信息到服务器
	 * @param bill 单据
	 */
	void saveBillInfo(BillDTO bill);
}
