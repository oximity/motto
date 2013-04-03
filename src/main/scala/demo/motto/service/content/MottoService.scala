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
package demo.motto.service.content

import java.net.URLEncoder
import java.text.MessageFormat
import java.util.logging.Logger
import java.util.Date
import javax.ejb.EJB
import javax.ejb.LocalBean
import javax.ejb.Stateless
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.inject.Named
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.Table
import java.util.logging.Level
import demo.motto.model.content.Motto
import demo.motto.util.DefaultMessages

/**
 * Services for mottos.
 * 
 * @author hapke
 */
@Stateless
@LocalBean
class MottoService {
  @Inject
  var log: Logger = _
  @EJB
  var mottoDb: MottoDBService = _
  @Inject
  var msg: DefaultMessages = _
 
  val UTF8 = "UTF-8"
  
    
  //
  // Motto activities
  //
  
  /** always return a motto */
  def getRandomMotto(): Motto = {
    val maxMottoId = mottoDb.getMaxMottoId()
    val randomMottoId = (Math.random()*(maxMottoId+1)).toLong
    mottoDb.getMottoById(randomMottoId) match {
      case Some(motto) => motto
      case _           => getDefaultMotto()
    }
  }
  
  def getDefaultMotto(): Motto = {
    val m = new Motto()
    m.content = msg.get("service.mottoService.defaultMotto.content") 
    m.author = msg.get("service.mottoService.defaultMotto.author") 
    m.created = new Date()
    m
  }
  
  def getMottoById(mottoId: Long): Option[Motto] = mottoDb.getMottoById(mottoId)
  
  def writeMotto(m: Motto): Unit = mottoDb.writeMotto(m)
 }
