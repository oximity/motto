package demo.motto.util.validation;

import java.util.regex.Pattern;

/**
 * JSR 303 password validator.
 * 
 * @author hapke
 */
public class PasswordValidator extends PatternValidator<Password> {
	private static String REGEX = "^.{8,60}$";
	private static Pattern PATTERN = Pattern.compile(REGEX);

	public PasswordValidator() {
		super(PATTERN);
	}
}
