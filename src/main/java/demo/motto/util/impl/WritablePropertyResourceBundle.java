package demo.motto.util.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;



/**
 * Based on  java.util.PropertyResourceBundle (openjdk 7),
 * but with put method.
 * 
 * @author master
 */
public class WritablePropertyResourceBundle extends ResourceBundle {
    private Map<String,Object> lookup;

    /**
     * Creates a property resource bundle from a {@link java.io.Reader
     * Reader}.  Unlike the constructor
     * {@link #PropertyResourceBundle(java.io.InputStream) PropertyResourceBundle(InputStream)},
     * there is no limitation as to the encoding of the input property file.
     *
     * @param reader a Reader that represents a property file to
     *        read from.
     * @throws IOException if an I/O error occurs
     * @throws NullPointerException if <code>reader</code> is null
     * @since 1.6
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public WritablePropertyResourceBundle (Reader reader) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        lookup = new HashMap(properties);
    }

    // Implements java.util.ResourceBundle.handleGetObject; inherits javadoc specification.
    public Object handleGetObject(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return lookup.get(key);
    }

    public void put(String key, Object value) {
    	lookup.put(key, value);
    }
    
    /**
     * Returns an <code>Enumeration</code> of the keys contained in
     * this <code>ResourceBundle</code> and its parent bundles.
     *
     * @return an <code>Enumeration</code> of the keys contained in
     *         this <code>ResourceBundle</code> and its parent bundles.
     * @see #keySet()
     */
    public Enumeration<String> getKeys() {
        ResourceBundle parent = this.parent;
        return new ResourceBundleEnumeration(lookup.keySet(),
                (parent != null) ? parent.getKeys() : null);
    }

    /**
     * Returns a <code>Set</code> of the keys contained
     * <em>only</em> in this <code>ResourceBundle</code>.
     *
     * @return a <code>Set</code> of the keys contained only in this
     *         <code>ResourceBundle</code>
     * @since 1.6
     * @see #keySet()
     */
    protected Set<String> handleKeySet() {
        return lookup.keySet();
    }

}
