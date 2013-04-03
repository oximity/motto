package demo.motto.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.validation.MessageInterpolator;

import demo.motto.util.impl.ResourceBundleLocator;
import demo.motto.util.impl.ResourceBundleMessageInterpolator;


/**
 * ResourceBundleMessageInterpolator that is based on MessagesResourceBundle
 * to read validation messages from the central messages.properties.
 * 
 * Referenced in validation.xml
 * 
 * @author hapke
 */
public class ValidationMessageInterpolator extends ResourceBundleMessageInterpolator implements MessageInterpolator {
	public ValidationMessageInterpolator() {
		super(new ResourceBundleLocator() {
			@Override
			public ResourceBundle getResourceBundle(Locale locale) {
				return new MessagesResourceBundle(locale);
			}
		});
	}
}
