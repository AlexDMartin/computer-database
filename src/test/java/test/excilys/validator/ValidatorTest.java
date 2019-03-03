package test.excilys.validator;

import static org.junit.Assert.assertTrue;

import com.excilys.dto.CompanyDto;
import com.excilys.dto.CompanyDtoBuilder;
import com.excilys.validator.Validator;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatorTest {
  @Rule
  public final ExpectedException exception = ExpectedException.none();
  
  Validator validator = Validator.getInstance();
  
  // Ids
  @Test
  public void testShouldAllowNullIds() throws Exception {
    assertTrue(validator.validateId(null));
  }
  
  @Test
  public void testShouldAllowPositiveIds() throws Exception {
    assertTrue(validator.validateId("9"));
  }

  @Test
  public void testShouldDenyNegativeIds() throws Exception {
    exception.expect(ValidationException.class); 
    validator.validateId("-5");
  }
  
  @Test
  public void testShouldDenyNonNumberIds() throws Exception {
    exception.expect(ValidationException.class);
    validator.validateId("test");
  }
  
  // Names
  @Test
  public void testShouldDenyEmptyNames() throws Exception {
    exception.expect(ValidationException.class);
    validator.validateName("");
  }
  
  @Test
  public void testShouldDenyNullNames() throws Exception {
    exception.expect(ValidationException.class);
    validator.validateName(null);
  }
  
  @Test
  public void testShouldAllowAlphaNumericNames() throws Exception {
    assertTrue(validator.validateName("t3st1234"));
  }
  
  // Dates
  @Test
  public void testShouldAllowFormattedDates() throws Exception {
    assertTrue(validator.validateDate("2019-03-03"));
  }
  
  @Test
  public void testShouldAllowNullDates() throws Exception {
    assertTrue(validator.validateDate(null));
  }
  
  @Test
  public void testShouldAllowEmptyDates() throws Exception {
    assertTrue(validator.validateDate(""));
  }
  
  @Test
  public void testShouldDenyWrongFormatDates() throws Exception {
    exception.expect(ValidationException.class);
    validator.validateDate("03/03/2019");
  }
  
  @Test
  public void testShouldAllowIntroductionDatesOlderThanDiscontinuation() throws Exception {
    Date introduced = new SimpleDateFormat("yyyy-MM-dd").parse("2019-05-10");
    Date discontinued = new SimpleDateFormat("yyyy-MM-dd").parse("2019-06-15");
    
    assertTrue(validator.validatePrecedence(introduced, discontinued));
  }
  
  @Test
  public void testShouldAllowThatOnlyOneDateIsProvided() throws Exception {
    Date date = new Date();
   
    assertTrue(validator.validatePrecedence(date, null));
    assertTrue(validator.validatePrecedence(null, date));
    assertTrue(validator.validatePrecedence(null, null));
  }
  
  @Test
  public void testShouldDenyIntroductionDatesMoreRecentThanDiscontinuation() throws Exception {
    Date introduced = new SimpleDateFormat("yyyy-MM-dd").parse("2019-06-15");
    Date discontinued = new SimpleDateFormat("yyyy-MM-dd").parse("2019-05-10");
    
    exception.expect(ValidationException.class);
    validator.validatePrecedence(introduced, discontinued);
  }
  
  // companies
  @Test 
  public void testShouldAllowNullCompanies() throws Exception {
    assertTrue(validator.validateCompany(null));
  }
  
  @Test 
  public void testShouldAllowValidCompanies() throws Exception {
    CompanyDto companyDto = new CompanyDtoBuilder()
        .addId("5")
        .addName("test")
        .build();
    
    assertTrue(validator.validateCompany(companyDto));
  }
  
  @Test
  public void testShouldDenyCompaniesWithInvalidIds() throws Exception { 
    
    CompanyDto companyDto = new CompanyDtoBuilder()
        .addId("-4")
        .addName("test")
        .build();
   
    exception.expect(ValidationException.class);
    validator.validateCompany(companyDto);
  }
  
  @Test
  public void testShouldDenyCompaniesWithInvalidNames() throws Exception { 
    
    CompanyDto companyDto = new CompanyDtoBuilder()
        .addId("5")
        .addName("")
        .build();
   
    exception.expect(ValidationException.class);
    validator.validateCompany(companyDto);
  }
  
  @Test
  public void testShouldDenyEmptyCompanies() throws Exception { 
    
    CompanyDto companyDto = new CompanyDtoBuilder()
        .addId(null)
        .addName(null)
        .build();
   
    exception.expect(ValidationException.class);
    validator.validateCompany(companyDto);
  }
}
