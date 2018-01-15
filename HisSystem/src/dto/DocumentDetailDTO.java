package dto;



import java.io.Serializable;


public class DocumentDetailDTO implements Serializable {
//    private Integer documentDetailId;
//    private Integer documentId;
//    private Integer medicineId;
//    private Integer storeHouseId;
    private Double number;
    private Double price;
    private Double money;
//    private Integer companyId;
    private String complyName;
    private String medicineName;

    public DocumentDetailDTO() {
    }

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getComplyName() {
		return complyName;
	}

	public void setComplyName(String complyName) {
		this.complyName = complyName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	

    
    
//    @Override
//    public String toString() {
//        return "P_DocumentDetail{" +
//                "documentDetailId=" + documentDetailId +
//                ", documentId=" + documentId +
//                ", medicineId=" + medicineId +
//                ", storeHouseId=" + storeHouseId +
//                ", number=" + number +
//                ", price=" + price +
//                ", money=" + money +
//                ", companyId=" + companyId +
//                '}';
//    }
}
