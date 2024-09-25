package com.maybank.demo.service.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class CatFactApiService {

    @Autowired
    private RestTemplate restTemplate;
    private final String externalAPIURI = "https://catfact.ninja/fact";

    private final Logger logger = LoggerFactory.getLogger(CatFactApiService.class);

    public HashMap<String, Object> fetchCatFact() {
        try {
            logger.info("CatFactApiService::fetchCatFact - Making request to cat API.");
            ResponseEntity<HashMap> response = restTemplate.getForEntity(externalAPIURI, HashMap.class);
            return response.getBody();
        } catch (Exception ex) {
            logger.error("CatFactApiService::fetchCatFact - Failed to fetch resources from Cat API, error message: {}", ex.getMessage());
            throw new RuntimeException("CatFactApiService::fetchCatFact - Failed to fetch resources from Cat API with error message: " + ex.getMessage());
        }
    }
}
