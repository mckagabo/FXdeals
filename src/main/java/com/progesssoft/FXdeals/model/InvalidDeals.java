package com.progesssoft.FXdeals.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "invalid_deals")
public class InvalidDeals {

	 
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	 
	    @Column(name="deal_id")
	    private String dealId;

	    @Column(name="ordering_currency")
	    private String fromCurrencyISOCode;

	    @Column(name="selling_currency")
	    private String toCurrencyISOCode;


	    @Column(name="timestamp")
	    private String timestamp;
	    
	    @Column(name="amount")
	    private String amount;
	    
	    public String getDealId() {
			return dealId;
		}

		public void setDealId(String dealId) {
			this.dealId = dealId;
		}

		@Column(name="file_name")
	    private String fileName;
	    
	    @Transient
	    private MultipartFile file;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFromCurrencyISOCode() {
			return fromCurrencyISOCode;
		}

		public void setFromCurrencyISOCode(String fromCurrencyISOCode) {
			this.fromCurrencyISOCode = fromCurrencyISOCode;
		}

		public String getToCurrencyISOCode() {
			return toCurrencyISOCode;
		}

		public void setToCurrencyISOCode(String toCurrencyISOCode) {
			this.toCurrencyISOCode = toCurrencyISOCode;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

	   

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public MultipartFile getFile() {
			return file;
		}

		public void setFile(MultipartFile file) {
			this.file = file;
		}
	    
	    
	    
}
