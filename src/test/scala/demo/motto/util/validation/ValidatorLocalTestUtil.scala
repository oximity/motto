package demo.motto.util.validation

import org.junit.Assert.assertEquals
import org.junit.Test
import javax.validation.ConstraintValidator


/**
 * Test helper(s).
 * 
 * @author hapke
 */
object ValidatorLocalTestUtil {
  def isValid(validator: ConstraintValidator[_, String], text: String, expectedResult: Boolean) {
    assertEquals("wrong validation of \""+text+"\":", expectedResult, validator.isValid(text, null))
  }
}
