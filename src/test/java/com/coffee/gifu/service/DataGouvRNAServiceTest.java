package com.coffee.gifu.service;

import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Integration tests for {@link MailService}.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataGouvRNAServiceTest {

    private DataGouvRNAService rnaService;

    @Mock
    private ApiExternalService apiExternalService;

    @Before
    public void setup() {
        rnaService = new DataGouvRNAService(apiExternalService);
    }

    @Test(expected = NotFoundException.class)
    public void callApi_should_throw_exeption_if_call_RNA_api_throw_exeption() throws Exception {
        String fakeCode = "jkdsifjdhsfjhq";
        //Given
        when(apiExternalService.getUrlBody(any())).thenThrow(NotFoundException.class);
        //When
        rnaService.callApi(fakeCode);
    }

}
