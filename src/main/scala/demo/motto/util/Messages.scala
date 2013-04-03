package demo.motto.util

import java.util.ResourceBundle
import javax.annotation.PostConstruct
import javax.enterprise.context.SessionScoped
import javax.faces.context.FacesContext
import javax.inject.Named
import javax.enterprise.context.ContextNotActiveException
import java.util.logging.Logger
import javax.inject.Inject

@Named
@SessionScoped
class Messages extends Serializable {
  @Inject
  var log: Logger = _
  
  var messagesResourceBundle: ResourceBundle = _

  def getResourceBundle() = messagesResourceBundle
  
  def getString(key: String): String = messagesResourceBundle.getString(key)
  def get(key: String): String = getString(key)
 
  
  
  @PostConstruct
  def init() = {
    /* path: src/main/resources/messages */
    messagesResourceBundle = getResourceBundle(/* resource-bundle var name from faces-config.xml:*/ "msg")
    log.info("messagesResourceBundle="+messagesResourceBundle)
  }

  private def getResourceBundle(resourceBundleName: String): ResourceBundle = {
    // alternative, but with path under src/main/resources/messages instead of resourceBundleName:
    //   messagesResourceBundle = BundleFactory.getBundle(resourceBundleName)

    val facesContext = FacesContext.getCurrentInstance();
    if (facesContext == null) {
       throw new ContextNotActiveException("FacesContext is not active")
    }
    facesContext.getApplication().getResourceBundle(facesContext, resourceBundleName)
  }
}
