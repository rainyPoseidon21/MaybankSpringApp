package com.maybank.demo.service.external;

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

    public HashMap<String, Object> fetchCatFact() {
        try {
            ResponseEntity<HashMap> response = restTemplate.getForEntity(externalAPIURI, HashMap.class);
            return response.getBody();
        } catch (Exception ex) {
            throw new RuntimeException("CatFactApiService::fetchCatFact - Failed to fetch resources from Cat API with error message: " + ex.getMessage());
        }
    }
}
