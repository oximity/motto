/*
 * Motto Web App Demo Application - oximity.com
 * Copyright (c) 2013 Oximity Limited
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses/>.
 */
package demo.motto.util.validation

import org.junit.Assert.assertEquals
import org.junit.Test

import demo.motto.util.validation.ValidatorLocalTestUtil.isValid

/**
 * Test of TextSingleLineValidator.
 * 
 * @author hapke
 */
class TextSinglelineValidatorLocalTest {
  val validator = new TextSingleLineValidator()

  @Test
  def testValidString() {
    isValid(validator, "", true)
    isValid(validator, "123abc", true)
    isValid(validator, "123हिन्दीabc", true)
    isValid(validator, "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~", true)
    isValid(validator, "Jetzt ist Schluss!", true)
  }

  @Test
  def testInvalidString() {
    isValid(validator, "123\n", false)
    isValid(validator, "123\r", false)
    isValid(validator, "123\t", false)
    isValid(validator, "123\u0000abc", false)
  }

  @Test
  def testStringTooLong() {
    val longSize = 260
    val longStr = new StringBuffer(longSize);
    for (i <- 1 to longSize) { longStr.append('a') }
    isValid(validator, longStr.toString(), false);
  }
}
