package net.scm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
@Entity
@Table(name = "vendor")
public class VendorModel implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;	

	@Column(name="vendor_id")
	private String vendId;

	@Column(name="vendor_name")
	private String  vendName;	  

	@Column(name="vendor_addr1")
	private String vendAddr1;
	
	@Column(name="vendor_addr2")
	private String vendAddr2;
	
	@Column(name="vendor_city")
	private String vendCity;

	@Column(name="vendor_country")
	private String vendCountry;
	
	@Column(name="vendor_pin")
	private String vendPin;
	
	@Column(name="vendor_prod_profile")
	private String vendProdprof;
	
	@Column(name="vendor_tin")
	private String vendTin;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getvendId() {
		return vendId;
	}
	public void setvendId(String vendId) {
		this.vendId = vendId;
	}

	//name
	public String getvendName() {
		return vendName;
	}		
	public void setvendName(String vendName) {
			this.vendName = vendName;
		}
	
	//Adress 1
	public String getvendAddr1() {
		return vendAddr1;
	}
	
	public void setvendAddr1(String vendAddr1 ) {
		this.vendAddr1 = vendAddr1;
	}
	
	//Adress 2
		
	public String getvendAddr2() {
	return vendAddr2;
		}
		
	public void setvendAddr2(String vendAddr2) {
	this.vendAddr2 = vendAddr2;
	}
		
	//city 
	public String getvendCity() {
	return vendCity;
		}
	public void setvendCity(String vendCity) {
	this.vendCity = vendCity;
	}
	
	//country
	public String getvendCountry() {
	return vendCountry;
	}
	public void setvendCountry(String vendCountry) {
	this.vendCountry = vendCountry;
	}
	//Pin
	public String getvendPin() {
	return vendPin;
	}
	public void setvendPin(String vendPin) {
	this.vendPin = vendPin;
	}
		
	//Vendor Product Profile
	public String getvendProdprof() {
	return vendProdprof;
	}
	public void setvendProdprof(String vendProdprof) {
	this.vendProdprof = vendProdprof;
	}
		
	//vendor Tin
	public String getvendTin() {
	return vendTin;
	}
	public void setvendTin(String vendTin) {
	this.vendTin= vendTin;
	}
}

