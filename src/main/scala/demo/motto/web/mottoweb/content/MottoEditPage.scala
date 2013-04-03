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

import java.util.Date
import java.util.logging.Logger

import scala.reflect.BeanProperty

import demo.motto.model.content.Motto
import demo.motto.service.content.MottoService
import demo.motto.util.Messages
import demo.motto.util.validation.TextSingleLine
import javax.ejb.LocalBean
import javax.ejb.Stateless
import javax.enterprise.context.RequestScoped
import javax.enterprise.context.SessionScoped
import javax.faces.context.FacesContext
import javax.inject.Inject
import javax.inject.Named
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.Size

/**
 * JSF backing bean for mottoEdit.xhtml
 * 
 * @author hapke
 */
@Named
@RequestScoped
class MottoEditPage {
  @Inject
  var log: Logger = _
  @Inject
  var msg: Messages = _
  @Inject
  var mottoService: MottoService = _
  
  //
  // form fields
  //
  @TextSingleLine @Size(min = 2, max = 255, message="{content.mottoEditPage.pleaseProvideValidContent}") @BeanProperty
  var content: String = _

  @TextSingleLine @Size(min = 2, max = 60, message="{content.mottoEditPage.pleaseProvideValidAuthor}") @BeanProperty
  var author: String = _
  

  /** action */
  def createNewMotto(): String = {
    log.fine("createNewMotto()")

    // copy form data
    val m = new Motto()
    m.content = content
    m.author = author
    m.created = new Date()
    
    // save user data
    mottoService.writeMotto(m)

    // the end: go to home page
    "/mottoweb/home?faces-redirect=true"
  }
}
