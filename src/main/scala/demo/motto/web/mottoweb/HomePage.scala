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
package demo.motto.web.mottoweb

import java.util.logging.Logger

import scala.reflect.BeanProperty

import demo.motto.model.content.Motto
import demo.motto.service.jobs.MottoChangerJobService
import javax.annotation.PostConstruct
import javax.ejb.LocalBean
import javax.ejb.Singleton
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.inject.Named
import javax.persistence.Entity
import javax.persistence.Table

/**
 * JSF backing bean for home.xhtml
 * 
 * @author hapke
 */
@Named
@RequestScoped
class HomePage {

  @Inject
  var log: Logger = _
  @Inject
  var mottoChangerJobService: MottoChangerJobService = _
  
  @BeanProperty
  var motto: Motto = _

  @PostConstruct
  def init() {
    // read random motto
    motto = mottoChangerJobService.currentMotto
    log.fine("current motto="+motto)
  }
}
