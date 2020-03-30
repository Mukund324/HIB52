package net.scm.core;

import java.io.Serializable;

public class Order extends Vendor implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String 	OpartId;
	private String  OpartName;	
	private String 	OpartProf;
	private int 	OpartTypPrice;
	private String	OpartSpec;
	private String 	OpartMaterial;
	private int 	OpartTypSuppSch;
	private String 	OpartQlyStd;

	//partID set/get method
	public String getOpartId() {
		return OpartId;
	}
	public void setOpartId(String OpartId) {
		this.OpartId = OpartId;
	}
	
	//partName set/get method
	public String getOpartName() {
		return OpartName;
	}		
	public void setOpartName(String OpartName) {
			this.OpartName = OpartName;
	}	
	
	//partProf set/get method
	public String getOpartProf() {
		return OpartProf;
	}
	public void setOpartProf(String OpartProf ) {
		this.OpartProf = OpartProf;
	}
	
	//part Price set/get method	
	public int getOpartTypPrice() {
		return OpartTypPrice;
	}		
	public void setOpartTypPrice(int OpartTypPrice) {
		this.OpartTypPrice = OpartTypPrice;
	}
		
	//partSpec set/get method
	public String getOpartSpec() {
		return OpartSpec;
	}
	public void setOpartSpec(String OpartSpec) {
		this.OpartSpec = OpartSpec;
	}
	
	//partSpec set/get method
	public String getOpartMaterial() {
		return OpartMaterial;
	}
	public void setOpartMaterial(String OpartMaterial) {
		this.OpartMaterial = OpartMaterial;
	}
	
	//country
	public int getOpartTypSuppSch() {
		return OpartTypSuppSch;
	}
	public void setOpartTypSuppSch(int OpartTypSuppSch) {
		this.OpartTypSuppSch = OpartTypSuppSch;
	}
	
	//Pin
	public String getOpartQlyStd() {
		return OpartQlyStd;
	}
	public void setpartQlyStd(String OpartQlyStd) {
		this.OpartQlyStd = OpartQlyStd;
	}
}//End of Class 