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
package demo.motto.util.webserver

import java.util.logging.Logger

import javax.inject.Inject
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener


/**
 * Log web requests.
 * 
 * Inspired by
 *   http://smokeandice.blogspot.de/2010/01/organize-your-logs-with-cool-java-ee-6.html
 *   
 * @author hapke
 */
//@WebFilter(Array("/*")): configured in web.xml to define execution order
@WebListener
class RequestLoggingFilter extends Filter with HttpSessionListener {
  val REQUEST_CONTEXT = "request-context"
  val SESSION_CONTEXT = "session-context"
 
  @Inject
  var log: Logger = _
 
  override def init(fc: FilterConfig) {
  }
 
  override def doFilter(req: ServletRequest, resp: ServletResponse, chain: FilterChain) {
      val txt = new StringBuilder();
      if(req.isInstanceOf[HttpServletRequest]) {
          val httpRequest = req.asInstanceOf[HttpServletRequest];
          val session = httpRequest.getSession(false);
 
          //Build Detailed Message
          txt.append("Starting ");
          txt.append(httpRequest.getMethod());
          txt.append(" request for URL '");
          txt.append(httpRequest.getRequestURL());
          if(httpRequest.getMethod().equalsIgnoreCase("get") && httpRequest.getQueryString() != null) {
              txt.append('?');
              txt.append(httpRequest.getQueryString());
          }
          txt.append("'.");
      }
 
      if(txt.length() == 0) {
          txt.append("Starting new request for Server '");
          txt.append(req.getScheme());
          txt.append(":\\");
          txt.append(req.getServerName());
          txt.append(':');
          txt.append(req.getServerPort());
          txt.append('/');
      }
 
      log.fine(txt.toString());
      val startTime = System.currentTimeMillis();
     
      chain.doFilter(req, resp);
 
      txt.setLength(0);
      txt.append("Request processing complete.  Time Elapsed -- ");
      txt.append(System.currentTimeMillis() - startTime);
      txt.append(" ms.");
      log.fine(txt.toString());
  }
 
  override def destroy() { }
 
  override def sessionCreated(hse: HttpSessionEvent) {
  }
 
  override def sessionDestroyed(hse: HttpSessionEvent) {     
  }
}
