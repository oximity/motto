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
package demo.motto.util.validation;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
/**
 * Base class of JSR 303 regex-based custom validators.
 * 
 * @author hapke
 */
abstract class PatternValidator<A extends Annotation> implements ConstraintValidator<A, String> {
	protected Pattern pattern;
	protected boolean isEmptyValid;

	PatternValidator(Pattern pattern, boolean isEmptyValid) {
		this.pattern = pattern;
		this.isEmptyValid = isEmptyValid;
	}
	PatternValidator(Pattern pattern) {
		this.pattern = pattern;
		this.isEmptyValid = true;
	}
	/**
	 * Configure the constraint validator based on the elements specified at the
	 * time it was defined.
	 * 
	 * See JSR 303 Section 2.4.1 for sample implementation.
	 * 
	 * @param annotation    the constraint definition
	 */
	@Override
	public void initialize(A annotation) {
		//
	}

	/**
	 * Validate a specified value. returns false if the specified value does not
	 * conform to the definition
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.length() == 0) {
			return isEmptyValid;
		}

		// check pattern
		return pattern.matcher(value).matches();
	}
}
