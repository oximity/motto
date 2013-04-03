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
package demo.motto.web.mottoweb.content

import java.util.logging.Logger
import scala.reflect.BeanProperty
import com.ocpsoft.pretty.faces.annotation.URLMapping
import demo.motto.model.content.Motto
import demo.motto.service.content.MottoService
import javax.annotation.PostConstruct
import javax.ejb.LocalBean
import javax.ejb.Stateless
import javax.enterprise.context.RequestScoped
import javax.faces.context.FacesContext
import javax.inject.Inject
import javax.inject.Named
import javax.persistence.Entity
import javax.persistence.Table
import javax.enterprise.context.SessionScoped

/**
 * JSF backing bean for mottoShow.xhtml
 * 
 * @author hapke
 */
@Named
@RequestScoped
@URLMapping(id="mottoShow", pattern="/motto/#{/.+/ mid}", viewId="/mottoweb/content/mottoShow.xhtml")
class MottoShowPage {

  @Inject
  var log: Logger = _
  @Inject
  var mottoService: MottoService = _
  
  @BeanProperty
  var motto: Motto = _

  @PostConstruct
  def init() {
    var params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
    var mottoId = params.get("mid").toLong
    log.fine("MottoShow: mottoId="+mottoId)
    
    // read motto
    motto = mottoService.getMottoById(mottoId).getOrElse(null)
  }
}
