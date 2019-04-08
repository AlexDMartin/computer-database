package com.excilys.test.mappers;

import static org.junit.Assert.assertEquals;
import com.excilys.dao.mappers.ComputerMapper;
import com.excilys.dao.model.Computer;
import com.excilys.dao.model.ComputerBuilder;
import com.excilys.dto.ComputerDto;
import com.excilys.dto.Dto;
import com.excilys.exception.validation.computer.ComputerValidationException;
import com.excilys.test.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ComputerMapperTest {
  
  @Autowired
  ComputerMapper mapper;
  
  @Test
  public void testMappedEntityIsCompanyDto() throws ComputerValidationException {
    Computer computer = new ComputerBuilder().addId(423).addName("test").build();
    
    Dto result = mapper.entityToDto(computer);
    
    assertEquals(ComputerDto.class, result.getClass());
  }
  
  @Test
  public void testMappedEntityHasProperAttributes() throws ComputerValidationException {
    Computer computer = new ComputerBuilder().addId(423).addName("test").build();
    
    ComputerDto computerDto = (ComputerDto) mapper.entityToDto(computer);
    
    assertEquals("423", computerDto.getId());
    assertEquals("test", computerDto.getName());
  }
  
  @Test(expected = ComputerValidationException.class)
  public void testInvalidEntityMappingThrowsException() throws ComputerValidationException {
    Computer computer = new ComputerBuilder().addId(423).build();
    
    mapper.entityToDto(computer);
  }
}
