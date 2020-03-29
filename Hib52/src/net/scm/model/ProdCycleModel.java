package net.scm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "prodcycle")
public class ProdCycleModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;	

	@Column(name="prodcyc_id")
	private String prodcycId;
	
	@Column(name="prodcyc_name")
	private String prodcycName;

	@Column(name="prodcyc_prod")
	private String  prodcycProd;	
	
	@Column(name="prodcyc_prod_id")
	private String  prodcycProdId;	
	
	@Column(name="prodcyc_class")
	private String  prodcycClass;	
	
	@Temporal(TemporalType.DATE)
	@Column(name="prodcyc_start_date")
	private Date prodcycStDt;	
	
	@Column(name="prodcyc_cap_price")
	private int  prodcycCapPrice;	
	
	//Generated ID set/get method 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//bomProdId set/get method
	public String getprodcycId() {
		return prodcycId;
	}		
	public void setprodcycId(String prodcycId) {
			this.prodcycId = prodcycId;
	}	
	
	//bomProdId set/get method
	public String getprodcycName() {
		return prodcycName;
	}		
	public void setprodcycName(String prodcycName) {
			this.prodcycName = prodcycName;
	}	
	
	public String getprodcycProd() {
		return prodcycProd;
	}		
	public void setprodcycProd(String prodcycProd) {
			this.prodcycProd = prodcycProd;
	}	
	
	public String getprodcycProdId() {
		return prodcycProdId;
	}		
	public void setprodcycProdId(String prodcycProdId) {
			this.prodcycProdId = prodcycProdId;
	}	
	
	//bomPartId set/get method
	public String getprodcycClass() {
		return prodcycClass;
	}
	public void setprodcycClass(String prodcycClass) {
		this.prodcycClass = prodcycClass;
	}
	
	//bomPartQty set/get method
	public Date getprodcycStDt() {
		return prodcycStDt;
	}
	public void setprodcycStDt(Date prodcycStDt) {
		this.prodcycStDt = prodcycStDt;
	}
		
	//bomPartQty set/get method
	public int getprodcycCapPrice() {
		return prodcycCapPrice;
	}
	public void setprodcycCapPrice(int prodcycCapPrice) {
		this.prodcycCapPrice = prodcycCapPrice;
	}
}//End of CLass