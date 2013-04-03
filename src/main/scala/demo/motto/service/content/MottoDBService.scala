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

import java.lang.{Long => JLong}
import java.util.logging.Logger

import demo.motto.model.content.Motto
import javax.ejb.LocalBean
import javax.ejb.Stateless
import javax.inject.Inject
import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.NoResultException
import javax.persistence.PersistenceContext
import javax.persistence.Table

/**
 * Access the database through JPA.
 * 
 * @author hapke
 */
@Stateless
@LocalBean
class MottoDBService {

  @PersistenceContext(unitName = "dbMotto")
  protected var em: EntityManager = _
  @Inject
  var log: Logger = _
  
  
  //
  // Access Motto
  //

  /** load a Motto object from DB */
  def getMottoById(mottoId: Long): Option[Motto] = {
    try {
      Option(em.find(classOf[Motto], mottoId))
    } catch {
      case ex: NoResultException => { None }
    }
  }
  
  /** determine the max id */
  def getMaxMottoId(): Long = {
    try {
      val u = em.createQuery("SELECT max(m.mottoId) FROM Motto m" /*WHERE m.author=:author"*/).
    		//setParameter("author", author).
    		getSingleResult().asInstanceOf[JLong]
      u
    } catch {
      case ex: NoResultException => { 0 }
    }
  }

  /** write a Motto object to DB; return the updated version */
  def writeMotto(m: Motto): Motto = {
    log.fine("writeMotto m="+m)
    
    em.merge(m)
  }
}
