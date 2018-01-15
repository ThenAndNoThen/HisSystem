package presenter;

import java.util.LinkedList;
import java.util.List;

import dto.BillDTO;
import dto.DocumentDTO;
import dto.DocumentDetailDTO;
import dto.UnitPriceDTO;
import model.MainModel;
import model.ModelFactory;
import view.MainView;

public class MainPresenter implements MainPresenterCallBack {
	MainView mainView;
	MainModel mainModel;
	List<DocumentDTO> documentDTOList;
	List<DocumentDetailDTO> documentDetailDTOList;
	
	public MainPresenter(MainView mainView) {
		this.mainView = mainView;
		this.mainModel = ModelFactory.createMainModel();
		documentDTOList = new LinkedList();
		documentDetailDTOList = new LinkedList();
		mainModel.setDataCallBack(this);
	}

	public void getUnitPrice(String unitName) {
		mainModel.getUnitPrice(unitName);
	}
	
	public void saveBillInfo(BillDTO bill) {
		bill.setDocumentList(documentDTOList);
		bill.setDocumentDetailList(documentDetailDTOList);
		mainModel.saveBillInfo(bill);
	}
	
	public void addDocumentDetail(List<DocumentDetailDTO> docDeailList,String depName,String docName) {
		documentDetailDTOList.addAll(docDeailList);
		DocumentDTO doc = new DocumentDTO();
		doc.setDoctorDepartmentName(depName);
		doc.setDoctorName(docName);
		doc.setComplyName(docDeailList.get(0).getComplyName());
		double money = 0;
		for(DocumentDetailDTO docDtl : docDeailList) {
			money += docDtl.getMoney();
		}
		doc.setMoney(money);
		documentDTOList.add(doc);
		this.mainView.addDocument(doc);
	}

	@Override
	public void messageFailed(String failInfo) {
		// TODO Auto-generated method stub
		mainView.actionFailed(failInfo);
		
	}


	@Override
	public void unitPriceRecived(UnitPriceDTO unitPrice) {
		// TODO Auto-generated method stub
		mainView.updateUnitPrice(unitPrice);
		
	}

	@Override
	public void messageSuccessed(String successInfo) {
		mainView.actionSuccessed("操作成功！");
		// TODO Auto-generated method stub
		
	}
}
