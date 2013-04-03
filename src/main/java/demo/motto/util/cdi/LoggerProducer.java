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
package demo.motto.util.cdi;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * To get a logger:
 *   @Inject
 *   Logger logger;
 * 
 * Implementation based on
 *   http://docs.jboss.org/weld/reference/latest/en-US/html/injection.html
 * 
 * @author hapke
 */
public class LoggerProducer {
   @Produces
   public Logger createLogger(InjectionPoint injectionPoint) { 
      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName()); 
   }
}
