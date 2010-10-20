/**
 * Copyright (C) 2009-2010 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fusesource.scalate
package ssp


class WhitespaceTest extends TemplateTestSupport {

  showOutput = true

  test("regular ssp directive") {
    assertSspOutput("""
  person: James
  person: Hiram
""", """
<% val people = List("James", "Hiram") %>
<% for (p <- people) { %>
  person: ${p}
<% } %>
""")
  }

  test("velocity style ssp directive") {
    assertSspOutput("""
  person: James
  person: Hiram
""", """
<% val people = List("James", "Hiram") %>
#for(p <- people)
  person: ${p}
#end
""")
  }

  test("ssp ${...} expression whitespace") {
    assertSspOutput("""
  Copyright 2010 MyCompany
""", """
<% val year = "2010" %>
  Copyright ${year} MyCompany
""")
  }

  test("ssp <%=...%> expression whitespace") {
    assertSspOutput("""
  Copyright 2010 MyCompany
""", """
<% val year = "2010" %>
  Copyright <%=year%> MyCompany
""")
  }

  test("ssp <% if(...) { %>...<% } %> mid-line expression whitespace") {
    assertSspOutput("""
  <tr class="featured">
""", """
<% val featured = true %>
  <tr<% if(featured) { %> class="featured"<% } %>>
""")
  }

  test("ssp <% ... %> expression end-of-line whitespace (without +)") {
    assertSspOutput("""
  LINE 1
  featured  LINE 2
""", """
  LINE 1
<% val featured = true %>
  <% if(featured) { %>featured<% } %>
  LINE 2
""")
  }

  test("ssp <% ... %> expression end-of-line whitespace (with +)") {
    assertSspOutput("""
  LINE 1
  featured
  LINE 2
""", """
  LINE 1
<% val featured = true %>
  <% if(featured) { %>featured<% } +%>
  LINE 2
""")
  }
}