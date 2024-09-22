package com.searchEngine.searchEngine.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchEngine.searchEngine.entity.ApiKey;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.UserModel;
import com.searchEngine.searchEngine.repository.ApiKeyRepository;
import com.searchEngine.searchEngine.repository.UserRepository;
import com.searchEngine.searchEngine.service.ApiKeyService;
import com.searchEngine.searchEngine.service.RegisterService;
import com.searchEngine.searchEngine.staticClass.RandomUtil;

import jakarta.annotation.PostConstruct;

@Component
public class StartupSetupContext {
    @Autowired
    private ApiKeyService apiKeyService;
     @Autowired
    private RegisterService registerService;
    @Autowired
    private ApiKeyRepository apiKeyRepository;
 
    @PostConstruct
    public void init() throws Exception{
        if(apiKeyRepository.findByKey("test_key").isEmpty()){
            User user = setupUser();

            ApiKey apiKey = apiKeyService.createApiKey(user);
            apiKey.setKey("test_key");
    
            apiKeyRepository.save(apiKey);
        }
       
    }

    public User setupUser() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setBirthDate("2002-12-12");
        String email = RandomUtil.generateRandomString(6) + "@wp.pl";
        userModel.setEmail(email);
        userModel.setFirstName("firstname");
        userModel.setLastName("lastname");
        userModel.setPassword("test");
        userModel.setUsername("username");

        return registerService.registerUser(userModel);
    }
}
