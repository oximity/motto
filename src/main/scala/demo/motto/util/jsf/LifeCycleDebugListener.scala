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
package demo.motto.util.jsf

import javax.faces.event.PhaseId
import javax.faces.event.PhaseListener
import javax.faces.event.PhaseEvent
import java.util.logging.Logger
import javax.ejb.SessionContext
import javax.naming.InitialContext
import java.util.logging.Level
import javax.naming.NamingException

/**
 * Log the JSF phases.
 * 
 * @author hapke
 */
class LifeCycleDebugListener extends PhaseListener {
  val log: Logger = Logger.getLogger(classOf[LifeCycleDebugListener].getName())

  def getPhaseId(): PhaseId = {
    PhaseId.ANY_PHASE
  }

  def beforePhase(event: PhaseEvent) {
    log.fine("START PHASE " + event.getPhaseId())
  }

  def afterPhase(event: PhaseEvent) {
    log.fine("END PHASE " + event.getPhaseId())
  }
}
