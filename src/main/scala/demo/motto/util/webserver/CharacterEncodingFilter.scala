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

import javax.servlet.ServletResponse
import javax.servlet.FilterChain
import javax.servlet.Filter
import javax.servlet.ServletRequest
import javax.servlet.annotation.WebFilter
import javax.servlet.FilterConfig

/**
 * Fix UTF-8 POST requst parameter parsing problem of Primefaces
 * Details: 
 *   http://stackoverflow.com/questions/9634230/typing-chinese-with-primefaces-peditor-component/9839362#9839362
 */
//@WebFilter(Array("/*")): configured in web.xml to define execution order
class CharacterEncodingFilter extends Filter {

    override def doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain)
    		/*throws ServletException, IOException*/ = {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    override def init(arg0: FilterConfig) = {}

    override def destroy() = {}
}
