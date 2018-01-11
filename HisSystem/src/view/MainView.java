package view;

import dto.DocumentDTO;
import dto.UnitPriceDTO;

public interface MainView {
	/**更新单据药品单价信息
	 * @param unitPrice 药品单价
	 * @param unitName 药品名称
	 */
	void updateUnitPrice(UnitPriceDTO unitPrice);
	
	/**增加单据详情信息到单据信息表
	 * @param billDetail 单据详情信息
	 */
	void addDocument(DocumentDTO document);
	
	
	/**操作成功提示
	 * 
	 */
	void actionSuccessed(String msg);
}
