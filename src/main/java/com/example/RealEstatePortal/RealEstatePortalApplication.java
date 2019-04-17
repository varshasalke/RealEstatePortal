package com.example.RealEstatePortal;

import com.example.RealEstatePortal.fileProperty.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class RealEstatePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealEstatePortalApplication.class, args);
	}

}
