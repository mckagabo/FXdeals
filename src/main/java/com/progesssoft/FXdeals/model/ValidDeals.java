package com.progesssoft.FXdeals.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="valid_deals")
public class ValidDeals {

	
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
    private Date timestamp;
    
    @Column(name="amount")
    private float amount;
    
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
  
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
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
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
    
    
     
	
	    

}
