package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.ArchaiusModel;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.sources.URLConfigurationSource;

@Component
public class ArchaiusService {
	
	

	public List<String> getKeys(ConcurrentMapConfiguration myConfiguration){
		
   
		Map<String,String> map=new HashMap<String,String>();  
		System.out.println("keys are "+myConfiguration.getKeys());
		List<String> keys=new ArrayList<String>();
     	Iterator<String> entries= myConfiguration.getKeys();
		while (entries.hasNext()) {       	   
			keys.add(entries.next().toString());   	        	  
       	}
     
		for(String Key: keys){    	 
			System.out.println("strings are  "+Key);    	 
		}		
		return keys;
     
	}
	
	
	public Map<String,String> getAllProperties(List<String> keys){
		
		Map<String,String> mapProps=new HashMap<>();
		for(int i=0;i<keys.size();i++){
		DynamicStringProperty props = DynamicPropertyFactory.getInstance().getStringProperty(keys.get(i),"magic");
		System.out.println("Archaius service has property as "+ props);
		mapProps.put(props.getName(), props.getValue());
		}
		return mapProps;
		
		
	}
	

}
