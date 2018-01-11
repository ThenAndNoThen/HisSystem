package dto;

import java.io.Serializable;
import java.util.List;

public class BillDTO implements Serializable{
	private String patName;
	private int patSex;
	private String docName;
	private String docDepartment;
	private List<DocumentDTO> documentList;
	private List<DocumentDetailDTO> documentDetailList;
	
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public int getPatSex() {
		return patSex;
	}
	public void setPatSex(int patSex) {
		this.patSex = patSex;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocDepartment() {
		return docDepartment;
	}
	public void setDocDepartment(String docDepartment) {
		this.docDepartment = docDepartment;
	}
	public List<DocumentDTO> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<DocumentDTO> documentList) {
		this.documentList = documentList;
	}
	public List<DocumentDetailDTO> getDocumentDetailList() {
		return documentDetailList;
	}
	public void setDocumentDetailList(List<DocumentDetailDTO> documentDetailList) {
		this.documentDetailList = documentDetailList;
	}
	
	
}
