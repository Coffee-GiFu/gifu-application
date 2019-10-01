package com.coffee.gifu.service;

import javassist.NotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import com.coffee.gifu.config.Constants;

import java.io.IOException;


@Service
public class DataGouvRNAService {
    private ApiExternalService apiExternalService;

    public DataGouvRNAService(ApiExternalService apiExternalService){
        this.apiExternalService =apiExternalService;
    }

    public JSONObject callApi(String rnaCode) throws ParseException, IOException, InterruptedException, NotFoundException {
        String apiResult = apiExternalService.getUrlBody(Constants.RNA_API_PATH + rnaCode);
        return (JSONObject) new JSONParser().parse(apiResult);
    }
}

