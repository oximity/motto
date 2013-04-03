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
package demo.motto.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;

import demo.motto.util.impl.WritablePropertyResourceBundle;


/**
 * Flexible and UTF-8 compatible representation of messages.properties.
 * 
 * UTF-8 stuff inspired by:
 * http://jdevelopment.nl/internationalization-jsf-utf8-encoded-properties-files/
 * 
 * ${key} replacements inspired by:
 * http://stackoverflow.com/questions/2605379/how-do-i-avoid-repetition-in-java-resourcebundle-strings
 *   and
 * https://code.google.com/p/reflectiveresourcebundle/source/browse/trunk/net/brockmatt/util/ResourceBundleUtil.java
 * 
 * @author hapke
 */
public class MessagesResourceBundle extends ResourceBundle {
	private static final Logger log = Logger.getLogger(MessagesResourceBundle.class.getName());
	
	protected Locale locale;

	/** Attention: this bundle may not be read without this class!!! Otherwise there are unwanted side effects. */
    protected static final String BUNDLE_NAME = "messages";
    protected static final String BUNDLE_EXTENSION = "properties";
    
    protected static final Control FLEXIBLE_AND_UTF8_CONTROL = new FlexibleAndUTF8Control();

    public MessagesResourceBundle() {
        this(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
    public MessagesResourceBundle(Locale locale) {
    	this.locale = locale;
    	log.info("construct bundle for locale="+locale);
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, locale, FLEXIBLE_AND_UTF8_CONTROL));
    }

    @Override
    protected Object handleGetObject(String key) {
    	log.finest("handleGetObject(key="+key+")");
        Object value = parent.getObject(key);
    	log.finest("handleGetObject(key="+key+")="+value);

    	return value;
    }

    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }
    
    protected static class FlexibleAndUTF8Control extends Control {
    	
    	private static final int MAX_RECURSION = 5;
    	private Pattern jspPattern = Pattern.compile("\\$\\{([\\w\\.\\-]+)\\}");
    	
        public ResourceBundle newBundle
            (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException
        {
            // The below code is copied from default Control#newBundle() implementation.
            // Only the PropertyResourceBundle line is changed to read the file as UTF-8.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
        	log.info("read locale="+locale+": bundle="+resourceName);
        	WritablePropertyResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                	log.info("read with stream!=null");
                    bundle = new WritablePropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            
            // now perform all replacements of JSP-like expressions
            for (String key : bundle.keySet()) {
            	log.info("  key/value: "+key+"="+bundle.getObject(key));
            	String translatedValue = translateMessage(bundle, key);
            	bundle.put(key, translatedValue);
            }
            
            return bundle;
        }
        
		/** replace ${key} expressions */
		private String translateMessage(ResourceBundle bundle, String key) {
			return translateMessage(bundle, key, MAX_RECURSION);
		}

		/** replace ${key} expressions */
		private String translateMessage(ResourceBundle bundle, String key, int iteration) {
			String message = bundle.getString(key);
			if (message != null) {
				StringBuffer sb = new StringBuffer();
				Matcher matcher = jspPattern.matcher(message);
				while (matcher.find() && iteration > 0) {
					// the magic
					matcher.appendReplacement(sb, translateMessage(bundle, matcher.group(1), iteration - 1));
				}
				matcher.appendTail(sb);
				return sb.toString();
			}
			return null;
		}
    }
}
