package demo.motto.util

import java.util.ResourceBundle
import javax.annotation.PostConstruct
import javax.faces.context.FacesContext
import javax.inject.Named
import javax.enterprise.context.ContextNotActiveException
import java.util.logging.Logger
import javax.inject.Inject
import javax.enterprise.context.ApplicationScoped
import java.util.Locale

@Named
@ApplicationScoped
class DefaultMessages extends Serializable {
  @Inject
  var log: Logger = _
  
  var messagesResourceBundle: ResourceBundle = _

  def getResourceBundle() = messagesResourceBundle
  
  def getString(key: String): String = messagesResourceBundle.getString(key)
  def get(key: String): String = getString(key)
 
  
  
  @PostConstruct
  def init() = {
    /* path: src/main/resources/messages */
    messagesResourceBundle = ResourceBundle.getBundle("messages", Locale.US)
  }
}
