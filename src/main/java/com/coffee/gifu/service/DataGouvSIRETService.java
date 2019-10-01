package com.coffee.gifu.service;

import com.coffee.gifu.config.Constants;
import javassist.NotFoundException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataGouvSIRETService extends ApiExternalService {
    private ApiExternalService apiExternalService;

    public DataGouvSIRETService(ApiExternalService apiExternalService){
        this.apiExternalService =apiExternalService;
    }

    public JSONObject callApi(String siretCode) throws ParseException, IOException, InterruptedException, NotFoundException {
        String apiResult = apiExternalService.getUrlBody(Constants.SIRET_API_PATH + siretCode);
        return (JSONObject) new JSONParser().parse(apiResult);
    }

}
