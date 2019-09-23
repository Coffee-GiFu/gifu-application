package com.coffee.gifu.service;

import com.coffee.gifu.domain.Recuperator;
import com.coffee.gifu.repository.RecuperatorRepository;
import com.coffee.gifu.service.dto.RecuperatorDTO;
import com.coffee.gifu.service.impl.RecuperatorServiceImpl;
import com.coffee.gifu.service.mapper.RecuperatorMapper;
import com.coffee.gifu.service.mapper.RecuperatorMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecuperatorServiceTest {

    @Mock
    RecuperatorRepository recuperatorRepository;

    private RecuperatorService recuperatorService;

    private RecuperatorMapper recuperatorMapper;

    @Before
    public void setup() {
        recuperatorMapper = new  RecuperatorMapperImpl();
        recuperatorService = new RecuperatorServiceImpl(recuperatorRepository, recuperatorMapper);
    }

    @Test
    public void testSave() {
        //Given
        RecuperatorDTO recuperatorDTO = new RecuperatorDTO();
        recuperatorDTO.setName("Toot");
        recuperatorDTO.setPhoneNumber("1432625233");
        Recuperator recuperator = recuperatorMapper.toEntity(recuperatorDTO);
        when(recuperatorRepository.save(any(Recuperator.class))).thenReturn(recuperator);

        //When
        RecuperatorDTO actualResult = recuperatorService.save(recuperatorDTO);

        //Then
        verify(recuperatorRepository, times(1)).save(any(Recuperator.class));
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getName()).isEqualTo(recuperatorDTO.getName());
        assertThat(actualResult.getPhoneNumber()).isEqualTo(recuperatorDTO.getPhoneNumber());
    }
}
