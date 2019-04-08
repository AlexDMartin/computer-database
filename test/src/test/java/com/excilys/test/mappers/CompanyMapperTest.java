package com.excilys.test.mappers;

import static org.junit.Assert.assertEquals;
import com.excilys.dao.mappers.CompanyMapper;
import com.excilys.dao.model.Company;
import com.excilys.dao.model.CompanyBuilder;
import com.excilys.dto.CompanyDto;
import com.excilys.dto.CompanyDtoBuilder;
import com.excilys.dto.Dto;
import com.excilys.exception.validation.company.CompanyValidationException;
import com.excilys.test.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CompanyMapperTest {
  
  @Autowired
  CompanyMapper mapper;
  
  @Test
  public void testMappedEntityIsCompanyDto() throws CompanyValidationException {
    Company company = new CompanyBuilder().addId(423).addName("test").build();
    
    Dto result = mapper.entityToDto(company);
    
    assertEquals(CompanyDto.class, result.getClass());
  }
  
  @Test
  public void testMappedCompanyDtoIsEntity() throws CompanyValidationException {
    CompanyDto companyDto = new CompanyDtoBuilder().addId("423").addName("test").build();
    
    Company result = mapper.dtoToEntity(companyDto);
    
    assertEquals(Company.class, result.getClass());
  }
  
  @Test
  public void testMappedEntityHasProperAttributes() throws CompanyValidationException {
    Company company = new CompanyBuilder().addId(423).addName("test").build();
    
    CompanyDto companyDto = (CompanyDto) mapper.entityToDto(company);
    
    assertEquals("423", companyDto.getId());
    assertEquals("test", companyDto.getName());
  }
  
  @Test(expected = CompanyValidationException.class)
  public void testInvalidEntityMappingThrowsException() throws CompanyValidationException {
    Company company = new CompanyBuilder().addId(423).build();
    
    mapper.entityToDto(company);
  }

}
