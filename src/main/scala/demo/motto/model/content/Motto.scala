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
package demo.motto.model.content

import java.util.Date
import java.lang.{Long => JLong}
import scala.reflect.BeanProperty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.GenerationType

/**
 * An object of this type represents a row in the database.
 * 
 * @author hapke
 */
@Entity
@Table(name="motto")
class Motto {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="motto_id") @BeanProperty
  var mottoId: JLong = _
  
  @Column @BeanProperty
  var content: String = _
  
  @Column @BeanProperty
  var author: String = _
  
  @Column @BeanProperty
  var rating: JLong = _
  
  @Column @BeanProperty
  var created: Date = _
  
  override def toString = 
    "Motto["+super.toString()+"]"+
    "(mottoId="+mottoId+
    ",content="+content+
    ",author="+author+
    ",rating="+rating+
    ",created="+created+
    ")"
}
