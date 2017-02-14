package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.neo4j.cypher.internal.helpers.Converge.iterateUntilConverged;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.model.ArchaiusModel;
import com.example.service.ArchaiusService;
import com.google.gson.Gson;
import com.netflix.config.ConcurrentCompositeConfiguration;
import com.netflix.config.ConcurrentMapConfiguration;
import com.netflix.config.ConfigurationManager;
import com.netflix.config.DeploymentContext;
import com.netflix.config.DynamicMapProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringMapProperty;
import com.netflix.config.DynamicStringProperty;
import com.netflix.config.sources.URLConfigurationSource;
import com.netflix.config.util.ConfigurationUtils;

@SpringBootApplication
@Component
@ImportResource("applicationContext.xml")
public class ArchaiusDemoApplication  {
	
	
	private static Logger logger=Logger.getLogger(ArchaiusDemoApplication.class);
	
	static {
        
    	 System.setProperty("archaius.configurationSource.defaultFileName", "config.properties");
    	 System.setProperty("archaius.configurationSource.additionalUrls", "file:/E:/MarsSpringWS/ArchaiusDemo/target/classes/config1.properties,file:/E:/MarsSpringWS/ArchaiusDemo/target/classes/config2.properties");
    	 System.setProperty("archaius.dynamicProperty.disableSystemConfig", "true");
    	 System.setProperty("archaius.dynamicProperty.disableEnvironmentConfig", "true");
    
    }
	
		 
	
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ArchaiusDemoApplication.class, args);      
    }
}
