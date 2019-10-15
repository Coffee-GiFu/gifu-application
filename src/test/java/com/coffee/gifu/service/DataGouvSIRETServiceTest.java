package com.coffee.gifu.service;

import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Integration tests for {@link MailService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataGouvSIRETServiceTest {

    private DataGouvSIRETService siretService;

    @Mock
    private ApiExternalService apiExternalService;

    @Before
    public void setup() {
        siretService = new DataGouvSIRETService(apiExternalService);
    }

    @Test(expected = NotFoundException.class)
    public void callApi_should_throw_exeption_if_call_SIRET_api_throw_exeption() throws Exception {
        String fakeCode = "jkdsifjdhsfjhq";
        //Given
        when(apiExternalService.getUrlBody(any())).thenThrow(NotFoundException.class);
        //When
        siretService.callApi(fakeCode);
    }
}
