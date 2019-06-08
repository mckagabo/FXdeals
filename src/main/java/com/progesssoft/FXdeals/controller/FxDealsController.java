package com.progesssoft.FXdeals.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.progesssoft.FXdeals.model.InvalidDeals;
import com.progesssoft.FXdeals.model.ValidDeals;
import com.progesssoft.FXdeals.service.FxDealsService;

@Controller
public class FxDealsController {
     
	@Autowired
	private FxDealsService service;
	
	@GetMapping(value="/")
	public String home(Model model) {
		InvalidDeals invalidDeals=new InvalidDeals();
		model.addAttribute("deals", invalidDeals );
		List<ValidDeals> validDeals=service.allValidDeals();
		model.addAttribute("validDealz", validDeals);
		return "view/deals";
	}
	
   @PostMapping(value="/fileupload")
	public String uploadFile(@ModelAttribute InvalidDeals invalidDeals,RedirectAttributes redirectAttribute) {
		//boolean isFlag=service.saveDataFromUploadFile(invalidDeals.getFile());
	   // service.readDataFromCsvFile(invalidDeals.getFile());
	   if(service.isFileNameExist(invalidDeals.getFile())) {
		   redirectAttribute.addFlashAttribute("errormessage", "Sorry you cant upload from this file twice");
	   }else if(service.fileExtension(invalidDeals.getFile()).equalsIgnoreCase("csv")) {
		   redirectAttribute.addAttribute("infomessage", ""+service.readDataFromCsvFile(invalidDeals.getFile()));
		  
	   }else {
		   redirectAttribute.addFlashAttribute("errormessage", "Sorry only csv format is allowed");
	   }
	   redirectAttribute.addAttribute("infomessage", "Please wait while the system is reading the file!");
	   return "redirect:/";
	}
   
   @GetMapping(value="/invalid")
   public String invalidDeals(Model model) {
	   InvalidDeals invalidDeals=new InvalidDeals();
		model.addAttribute("deals", invalidDeals );
		List<InvalidDeals> invalidDealslist=service.allInvalidDeals();
		model.addAttribute("invalidDealz", invalidDealslist);
		return "view/invalid_deals";
   }
	// Return all validDeals as JSON
   @RequestMapping(value="/valid")
   public @ResponseBody List<ValidDeals> getValidDeals(Model model) {	
       return (List<ValidDeals>)service.allValidDeals();
   }
   
// Return all invalidDeals as JSON
   @RequestMapping(value="/invalidDeals")
   public @ResponseBody List<InvalidDeals> getInValidDeals(Model model) {	
       return (List<InvalidDeals>)service.allInvalidDeals();
   }
}
