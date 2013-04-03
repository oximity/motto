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
package demo.motto.web.jsf_component

import javax.faces.component.FacesComponent
import javax.faces.component.UINamingContainer

/**
 * Bean of component "outputFormatMultiline.xhtml.
 * 
 * @author hapke
 */
// To be specified in componentType attribute:
@FacesComponent(value="outputFormatMultilineComponent") 
class OutputFormatMultilineComponent extends UINamingContainer {

  def getLines(): Array[String] = {
    getValueStr().split("""[\r\n]""").filter(line => line.trim().length()>0)
  }

  /** Don't make this method public! Ends otherwise in an infinite loop calling itself. */
  private def getValueStr(): String = {
    return getAttributes().get("value").asInstanceOf[String];
  }
}
