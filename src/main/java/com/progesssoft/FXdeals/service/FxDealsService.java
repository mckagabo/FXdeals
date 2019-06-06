package com.progesssoft.FXdeals.service;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.progesssoft.FXdeals.DAO.InvalidDealsDAO;
import com.progesssoft.FXdeals.DAO.ValidDealsDAO;
import com.progesssoft.FXdeals.model.InvalidDeals;
import com.progesssoft.FXdeals.model.ValidDeals;

@Service
public class FxDealsService {
	
	@Autowired
	private ValidDealsDAO validDao;
	@Autowired
	private InvalidDealsDAO invalidDao;
	
	public List<ValidDeals> allValidDeals(){
		return validDao.findAll();
	}
	public List<InvalidDeals> allInvalidDeals(){
		 return invalidDao.findAll();
	}
	public void saveValidDeals(ValidDeals valid) {
		 validDao.save(valid);
	}
	
	public void saveInvalidDeals(InvalidDeals invalid) {
		invalidDao.save(invalid);
	}
  
	/*public boolean saveDataFromUploadFile(MultipartFile file) {
		//boolean isFlag=false;
		String fileName=file.getOriginalFilename();
		String extension=FilenameUtils.getExtension(fileName);
		if(extension.equalsIgnoreCase("csv")) {
			readDataFromCsvFile(file);
		}else {
		return false;	
		}
		
	}*/
	
	public String readDataFromCsvFile(MultipartFile file) {
	  SimpleDateFormat sdf=new SimpleDateFormat("m/d/yyyy");
	   String  message="";
		String fileName=file.getOriginalFilename();
		
		try {
			InputStreamReader reader=new InputStreamReader(file.getInputStream());
			CSVReader csvReader=new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows=csvReader.readAll();
			for(String[] row:rows) {
				
	 if(row[0].isEmpty()||isNotCurrency(row[1])||isNotCurrency(row[2])||isInvalidDate(row[3])||isString(row[4])) {
					InvalidDeals deal=new InvalidDeals();
					deal.setDealId(row[0]);
					deal.setFromCurrencyISOCode(row[1]);
					deal.setToCurrencyISOCode(row[2]);
					deal.setTimestamp(row[3]);
					deal.setAmount(row[4]);
					deal.setFileName(fileName);
					invalidDao.save(deal);
			         
					message="Invalid deals inserted";
				}else {
					ValidDeals deal=new ValidDeals();
					deal.setDealId(row[0]);
					deal.setFromCurrencyISOCode(row[1]);
					deal.setToCurrencyISOCode(row[2]);
					deal.setTimestamp(sdf.parse(row[3]));
					deal.setAmount(Float.parseFloat(row[4]));
					deal.setFileName(fileName);
					validDao.save(deal);
					message= "Valid deals successfully inserted";
				}
				
				
			}
			
		} catch (Exception e) {
		  System.out.println("Dore ibibazo:"+e.toString()+"and"+e.getMessage());
		  message= ""+e;;
		}
		
		return message;
		
	}
	
	public boolean isString(String value) {
		if(value==null) {
			return true;
		}
		try {
			Float.parseFloat(value);
			return false;
		} catch (Exception e) {
			return true;
		}
	}
	public boolean isInvalidDate(String time) {
		if(time==null) {
			return true;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("m/d/yyyy HH:SS");
		try {
		Date date=sdf.parse(time);
		 
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return true;
		}
	}
	
	public boolean isNotCurrency(String currency) {
		if(currency.length()==3) {
			return false;
		    }
	    
		if(currency=="") {
			return true;
		}
	     return true;  
	}
	public boolean isFileNameExist(MultipartFile file) {
		boolean exist=false;
		 String fileName=file.getOriginalFilename();
	   if(validDao.existsByName(fileName)>0) {
		  exist=true;
	   }
	   return exist;
	}
	
   public String fileExtension(MultipartFile file) {
	 
	   String fileName=file.getOriginalFilename();
		String extension=FilenameUtils.getExtension(fileName);
		return extension;
		
   }
}
