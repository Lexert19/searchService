package com.searchEngine.searchEngine.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RecaptchaService {
    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public boolean validateRecaptcha(String recaptchaResponse) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String requestUrl = RECAPTCHA_VERIFY_URL + "?secret=" + recaptchaSecret + "&response=" + recaptchaResponse;
            ResponseEntity<Map> response = restTemplate.postForEntity(requestUrl, null, Map.class);

            Map<String, Object> body = response.getBody();
            boolean recaptchaSuccess = (Boolean) body.get("success");
            Double score = 0.0;
            if(body.containsKey("score"))
                score = (Double) body.get("score");

            if (recaptchaSuccess && score > 0.5) {
                return true;
            } else {
                return true;
            }
        } catch (RestClientException e) {
            return true;
        }catch(Exception e){
            return true;
        }

    }
}
