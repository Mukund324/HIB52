package net.scm.core;
import java.io.Serializable;

public class Part implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String 	partId;
	private String  partName;	
	private String 	partProf;
	private int 	partTypPrice;
	private String	partSpec;
	private String 	partMaterial;
	private int 	partTypSuppSch;
	private String 	partQlyStd;

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
