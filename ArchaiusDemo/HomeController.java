package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.ArchaiusModel;
import com.example.service.ArchaiusService;
import com.google.gson.Gson;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.sources.URLConfigurationSource;

@RestController
public class HomeController {
	
	private ArchaiusService  archaiusService;
	
	private Gson gson=null;
	
	private ArrayList<ArchaiusModel> models=null;
	
	private Map<String,String> mapProps=null;
		
		
	@Autowired
	public void setArchaiusService(ArchaiusService archaiusService) {
		this.archaiusService = archaiusService;
	}
	
	


	@RequestMapping("/*")
	public String  welcome(){		
		
		return "Welcome to Dynamic properties configuration";
	}
	
	@RequestMapping("/get")
	public String getProperties(){	
		
			this.gson=new Gson();        
			this.models=new ArrayList<ArchaiusModel>();
			
			//below  DynamicPropertyFactory.getInstance can be ignored
		 	DynamicPropertyFactory.getInstance();
		 	
			ConcurrentMapConfiguration myConfiguration = (ConcurrentMapConfiguration) DynamicPropertyFactory.getBackingConfigurationSource();
			System.out.println("My Configuration is"+myConfiguration);
			//URLConfiguration used to check how the urls to the files are loaded
			URLConfigurationSource config=new URLConfigurationSource();				   
			System.out.println("config URLS are "+config.getConfigUrls());
		   
			//calling this function to get the keys separately from the configuration instance
			List<String> keys=archaiusService.getKeys(myConfiguration);
		    	     			
			
			//calling this function to get the values from keys and mapProps contains key value pairs
			this.mapProps=archaiusService.getAllProperties(keys);
			
			
			//Printing the values from the keys obtained
			for (Map.Entry<String, String> entry : mapProps.entrySet()) {
        	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        	    //ignore gson, used to map it to dummy archaius model for testing 
        	    ArchaiusModel archaiusModel=gson.fromJson(entry.getValue(), ArchaiusModel.class);
        	    models.add(archaiusModel);
        
			}
         
			for(int i=0;i<models.size();i++){
        	
				System.out.println("models Id"+models.get(i).getId());
				System.out.println("models Name"+
				models.get(i).getName());
				System.out.println("models Company"+models.get(i).getCompany());
			}
      
		
		
			return "Welcome to Properties";
	}
	
	
	//Below logic for another scenario with different implementation 
	@RequestMapping("/getmore")
	public String getMoreProperties() throws IOException, ConfigurationException{

        ConfigurationManager.loadCascadedPropertiesFromResources("config");
        	
		ConcurrentMapConfiguration myConfiguration=(ConcurrentMapConfiguration)DynamicPropertyFactory.getInstance().getBackingConfigurationSource();
		String env1=DynamicPropertyFactory.getInstance().getStringProperty("Environment", "barath").toString();
		System.out.println("environemnt is "+env1);
		System.out.println("Configuration is " +myConfiguration.getKeys());
		List<String> keys=archaiusService.getKeys(myConfiguration);
		
		
		
		return "Welcome to Get More Properties";
	}

}
