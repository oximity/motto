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
package demo.motto.service.jobs

import java.util.logging.Logger
import demo.motto.service.content.MottoService
import javax.ejb.EJB
import javax.ejb.LocalBean
import javax.ejb.Schedule
import javax.ejb.Singleton
import javax.ejb.Stateless
import javax.inject.Inject
import javax.annotation.PostConstruct
import scala.beans.BeanProperty
import demo.motto.model.content.Motto

/**
 * A simple job to set and to change the currentMotto.
 * 
 * @author hapke
 */
@Singleton
@LocalBean
class MottoChangerJobService {

  @Inject
  var log: Logger = _
  @EJB
  var mottoService: MottoService = _

  @BeanProperty
  var currentMotto: Motto = _
  

  @PostConstruct
  def init() {
    log.info("initial set of the motto")
    setRandomCurrentMotto()
  }
  
  @Schedule(persistent=false, second="0", minute="*", hour="*", dayOfMonth="*", month="*", year="*")
  def setMottoOfTheMinute() {
    log.info("scheduled change of the motto")
    setRandomCurrentMotto()
  }
  
  protected def setRandomCurrentMotto() {
    currentMotto = mottoService.getRandomMotto()
    log.info("new currentMotto="+currentMotto)
  }
}
