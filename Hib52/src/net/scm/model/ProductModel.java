package net.scm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;
@Entity
@Table(name = "product")
public class ProductModel implements Serializable
{

	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue
		@Column(name="id")
		private int id;	

		@Column(name="prod_name")
		private String prodName;

		@Column(name="prod_price")
		private int prodPrice;	  

		@Column(name="prod_rating")
		private String prodRating;
		
		@Column(name="prod_make")
		private String prodMake;
		
		@Column(name="prod_country")
		private String prodCountry;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProdName() {
			return prodName;
		}


		public void setProdName(String prodName) {
			this.prodName = prodName;
		}

		public int getProdPrice() {
			return prodPrice;
		}
		
		public void setProdPrice(int prodPrice) {
			this.prodPrice = prodPrice;
		}
		

		public String getProdRating() {
			return prodRating;
		}
		
		public void setProdRating(String prodRating) {
			this.prodRating = prodRating;
		}
		
		
		public String getProdMake() {
			return prodMake;
		}
		
		public void setProdMake(String prodMake) {
			this.prodMake = prodMake;
		}
		
		
		public String getProdCountry() {
			return prodCountry;
		}
		
		public void setProdCountry(String prodCountry) {
			this.prodCountry = prodCountry;
		}
}