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

import java.util.regex.Pattern;

/**
 * JSR 303 text validator (single line).
 * 
 * @author hapke
 */
public class TextSingleLineValidator extends PatternValidator<Password> {
	static String REGEX_VALID_PUNCTATIONS_CLASS = "[!\"#\\$%&'\\(\\)\\*\\+,-./:;<=>?@\\[\\\\\\]\\^_`{|}~]";
	static String REGEX_VALID_WHITESPACES_CLASS = "[ ]";
	static String REGEX_VALID_UNICODE_ALPHANUMERIC_CLASS = "[\\p{L}\\p{M}\\d]";
	static String REGEX_VALID_CHARACTER_CLASS =
			"["+
		    REGEX_VALID_PUNCTATIONS_CLASS+
			REGEX_VALID_WHITESPACES_CLASS+
			REGEX_VALID_UNICODE_ALPHANUMERIC_CLASS+
			"&&[^\\p{Cntrl}]]";
	private static String REGEX = "^"+REGEX_VALID_CHARACTER_CLASS+"{0,255}$";
	private static Pattern PATTERN = Pattern.compile(REGEX);

	public TextSingleLineValidator() {
		super(PATTERN);
	}
}
