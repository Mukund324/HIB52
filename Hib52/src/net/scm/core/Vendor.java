package net.scm.core;

import java.io.Serializable;

public class Vendor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String vendId;
	private String vendName;	  
	private String vendAddr1;
	private String vendAddr2;
	private String vendCity;
	private String vendCountry;
	private String vendPin;
	private String vendProdprof;
	private String vendTin;
	
	public String getvendId() {
		return vendId;
	}
	public void setvendId(String vendId) {
		this.vendId = vendId;
	}

	public String getvendName() {
		return vendName;
	}		
	public void setvendName(String vendName) {
		this.vendName = vendName;
	}

	public String getvendAddr1() {
		return vendAddr1;
	}	
	public void setvendAddr1(String vendAddr1 ) {
		this.vendAddr1 = vendAddr1;
	}
		
	public String getvendAddr2() {
		return vendAddr2;
	}		
	public void setvendAddr2(String vendAddr2) {
		this.vendAddr2 = vendAddr2;
	}

	public String getvendCity() {
		return vendCity;
	}
	public void setvendCity(String vendCity) {
	this.vendCity = vendCity;
	}

	public String getvendCountry() {
		return vendCountry;
	}
	public void setvendCountry(String vendCountry) {
		this.vendCountry = vendCountry;
	}

	public String getvendPin() {
		return vendPin;
	}
	public void setvendPin(String vendPin) {
		this.vendPin = vendPin;
	}

	public String getvendProdprof() {
		return vendProdprof;
	}
	public void setvendProdprof(String vendProdprof) {
		this.vendProdprof = vendProdprof;
	}

	public String getvendTin() {
		return vendTin;
	}
	public void setvendTin(String vendTin) {
		this.vendTin= vendTin;
	}

}
