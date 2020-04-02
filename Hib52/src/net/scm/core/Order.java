package net.scm.core;
import net.scm.model.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;
    
	private String 	partId;
	private String  partName;	
	private String  vendId;
	private String  vendName;	  
	private String  vendAddr1;
	private String  vendCity;
	private String  vendCountry;
	private String  vendPin;
	private String  vendTin;
	private String	vendSupplyClass;
	private int 	vendSupplyLeadTime;
	private int 	vendSupplyPrice;	
//	private Date    ordByLatestDt;

	public Order()
	{
		
	}
	
	public Order(String partId, String partName, String vendId, String vendName, String vendAddr1,
			String vendCity, String vendCountry, String vendPin, String vendTin, String vendSupplyClass,
			int vendSupplyLeadTime, int vendSupplyPrice) 
	{
		this.partId = partId;
		this.partName = partName;
		this.vendId=vendId;
		this.vendName=vendName;
		this.vendAddr1=vendAddr1;
		this.vendCity=vendCity;
		this.vendCountry=vendCountry;
		this.vendPin=vendPin;
		this.vendTin=vendTin;
		this.vendSupplyClass=vendSupplyClass;
		this.vendSupplyLeadTime=vendSupplyLeadTime;
		this.vendSupplyPrice=vendSupplyPrice;
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
	
	public String getvendTin() {
		return vendTin;
	}
	public void setvendTin(String vendTin) {
		this.vendTin = vendTin;
	}

	public String getvendSupplyClass() {
		return vendSupplyClass;
	}	
	public void setvendSupplyClass(String vendSupplyClass) {
		this.vendSupplyClass = vendSupplyClass;
	}
	
	public int getvendSupplyLeadTime() {
		return vendSupplyLeadTime;
	}
	public void setvendSupplyLeadTime(int vendSuppLeadTime ) {
		this.vendSupplyLeadTime = vendSuppLeadTime;
	}
		
	public int getvendSupplyPrice() {
		return vendSupplyPrice;
	}
	public void setvendSupplyPrice(int vendSuppPrice ) {
		this.vendSupplyPrice = vendSuppPrice;
	}
/*	
	//Pin
	public Date getordByLatestDt() {
		return ordByLatestDt;
	}
	public void setordByLatest(Date ordByLatestDt) {
		this.ordByLatestDt = ordByLatestDt;
	}
*/
	
	
	@Override
    public String toString()
	{
		return(
				"[pID="   +getpartId()            +", "+ 
				"vID="    +getvendId()            +", "+
			    "sClas="  +getvendSupplyClass()   +", "+
			    "sLdTm="  +getvendSupplyLeadTime()+", "+
			    "sPric="  +getvendSupplyPrice()   +", "+
				"pName="  +getpartName()          +", "+ 
			    "vName="  +getvendName()          +", "+
			    "vCity="  +getvendCity()          +", "+
			    "vCtry="  +getvendCountry()       +", "+
			    "vAddr="  +getvendAddr1()         +", "+
			    "vPin="   +getvendPin()           +", "+
			    "vTin="   +getvendTin()           +" ]"
			  );
	}
}//End of Class 