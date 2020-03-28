package net.scm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
@Entity
@Table(name = "bom")
public class BoMModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;	

	@Column(name="bom_prod_id")
	private String bomProdId;
	
	@Column(name="bom_prod_name")
	private String bomProdName;

	@Column(name="bom_part_id")
	private String  bomPartId;	
	
	@Column(name="bom_part_name")
	private String  bomPartName;	
	
	@Column(name="bom_part_qty")
	private int bomPartQty;
	
	//Generated ID set/get method 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//bomProdId set/get method
	public String getbomProdId() {
		return bomProdId;
	}		
	public void setbomProdId(String bomProdId) {
			this.bomProdId = bomProdId;
	}	
	
	public String getbomProdName() {
		return bomProdName;
	}		
	public void setbomProdName(String bomProdName) {
			this.bomProdName = bomProdName;
	}	
	
	public String getbomPartName() {
		return bomPartName;
	}		
	public void setbomPartName(String bomPartName) {
			this.bomPartName = bomPartName;
	}	
	
	//bomPartId set/get method
	public String getbomPartId() {
		return bomPartId;
	}
	public void setbomPartId(String bomPartId) {
		this.bomPartId = bomPartId;
	}
	
	//bomPartQty set/get method
	public int getbomPartQty() {
		return bomPartQty;
	}
	public void setbomPartQty(int bomPartQty) {
		this.bomPartQty = bomPartQty;
	}
}//End of CLass
