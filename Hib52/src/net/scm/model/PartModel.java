package net.scm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
@Entity
@Table(name = "part")
public class PartModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;	

	@Column(name="part_id")
	private String partId;

	@Column(name="part_name")
	private String  partName;	
	
	@Column(name="part_profile")
	private String partProf;

	@Column(name="part_typ_price")
	private int partTypPrice;
	
	@Column(name="part_spec")
	private String partSpec;
	
	@Column(name="part_material")
	private String partMaterial;
	
	@Column(name="part_typ_supp_sch")
	private int partTypSuppSch;

	@Column(name="part_qly_std")
	private String partQlyStd;
	//Generated ID set/get method 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//partID set/get method
	public String getpartId() {
		return partId;
	}
	public void setpartId(String partId) {
		this.partId = partId;
	}
	
	//partName set/get method
	public String getpartName() {
		return partName;
	}		
	public void setpartName(String partName) {
			this.partName = partName;
	}	
	
	//partProf set/get method
	public String getpartProf() {
		return partProf;
	}
	public void setpartProf(String partProf ) {
		this.partProf = partProf;
	}
	
	//part Price set/get method	
	public int getpartTypPrice() {
		return partTypPrice;
	}		
	public void setpartTypPrice(int partTypPrice) {
		this.partTypPrice = partTypPrice;
	}
		
	//partSpec set/get method
	public String getpartSpec() {
		return partSpec;
	}
	public void setpartSpec(String partSpec) {
		this.partSpec = partSpec;
	}
	
	//partSpec set/get method
	public String getpartMaterial() {
		return partMaterial;
	}
	public void setpartMaterial(String partMaterial) {
		this.partMaterial = partMaterial;
	}
	
	//country
	public int getpartTypSuppSch() {
		return partTypSuppSch;
	}
	public void setpartTypSuppSch(int partTypSuppSch) {
		this.partTypSuppSch = partTypSuppSch;
	}
	
	//Pin
	public String getpartQlyStd() {
		return partQlyStd;
	}
	public void setpartQlyStd(String partQlyStd) {
		this.partQlyStd = partQlyStd;
	}
}//End of Class 
