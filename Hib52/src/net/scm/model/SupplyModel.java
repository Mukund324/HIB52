package net.scm.model;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "supply")
public class SupplyModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;	
	
	@Column(name="part_name")
	private String  partName;	
	
	@Column(name="part_id")
	private String partId;

	@Column(name="vendor_name")
	private String vendName;	  

	@Column(name="vendor_id")
	private String vendId;
	
	@Column(name="vendor_supply_class")
	private String vendSupplyClass;
	
	@Column(name="vendor_supply_ldtime")
	private int vendSupplyLeadTime;
	
	@Column(name="vendor_supply_price")
	private int vendSupplyPrice;
	
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
	
	@Override
    public String toString()
	{
		return(
				"[ PartName="     +getpartName()          +", "+ 
				"PartID="         +getpartId()            +", "+ 
			    "VendorName="     +getvendId()            +", "+
				"VendorID="       +getvendName()          +", "+
			    "SupplyClass="    +getvendSupplyClass()   +", "+
			    "SupplyLeadTime=" +getvendSupplyLeadTime()+", "+
			    "SupplyPrice="    +getvendSupplyPrice()   +" ]"
			  );
	}
}//End of Class 

 