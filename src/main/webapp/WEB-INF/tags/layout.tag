<%@tag description="Template" pageEncoding="UTF-8"%>
<%@attribute name="title"%>
<%@attribute name="body_area" fragment="true" required="true"%>
<!doctype html>
<html>
<head>
<title>Agenda telefonica <%=title.length() != 0 ? " - " + title : ""%></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Agenda Telefonica Plastor">
<meta name="author" content="Octavian Santau">
<link rel="icon" href="${pageContext.servletContext.contextPath}/assets/favicon.ico">
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/assets/jquery/jquery-1.3.2.js"></script>
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/assets/jquery/ui/jquery-ui-1.7.3.custom.js"></script>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/style.css" type="text/css" />
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/assets/js/jquery.watermark.js"></script>
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/assets/jquery/jquery.livesearchnew.js"></script>
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/assets/jquery/jquery.multiselect.js"></script>
<script type="text/javascript"	src="${pageContext.servletContext.contextPath}/assets/js/search.js"></script>
</head>
<body>
	<div id="content">
		<div id="header">		
			<h1 style="padding-top: 15px;">
				<a href="${pageContext.servletContext.contextPath}/" title="Plastor">Agenda
					<span class="title">Telefonica</span>
				</a>

			</h1>
			<% if(session.getAttribute("username") != null) {%>
			<div align="right" style="font-size: 14px;">
				Logat ca: <b> <%= session.getAttribute("username") %>
				</b>
			</div>
			<% }%>
		</div>

		<div id="tabs">
			<ul>		
			 <% if(session.getAttribute("username") !=null) { %>	  
				<li><a
					href="${pageContext.servletContext.contextPath}/logout"
					accesskey="l"><span class="key">L</span>ogout</a></li>
					<% if(session.getAttribute("role").equals("admin")) { %>				
				<li><a href="${pageContext.servletContext.contextPath}/admin"
					accesskey="c"><span class="key">U</span>sers</a> <% } %>
				<li><a href="${pageContext.servletContext.contextPath}/person"
					accesskey="p"><span class="key">P</span>ersonal</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/branches"
					accesskey="f"><span class="key">F</span>iliale</a></li>
				<li><a href="${pageContext.servletContext.contextPath}/office"
					accesskey="b"><span class="key">B</span>irouri</a></li>
				<% } else { %>

				<li><a
					href="${pageContext.servletContext.contextPath}/login"
					accesskey="l"><span class="key">L</span>ogin</a></li>
				<% } %>
				<li><a
					href="${pageContext.servletContext.contextPath}/export"
					accesskey="l"><span class="key">E</span>xport liste</a></li>

			</ul>
			<div id="search">
				<form method="post" action="">
					<p>
						Cautare&nbsp;&nbsp;<input type="text" name="search" class="search"
							id="faq_search_input" />
					</p>
				</form>
			</div>
		</div>

		<div class="gboxtop"></div>
		<div class="gbox">
		<% if(session.getAttribute("username") != null && session.getAttribute("role").equals("admin")) { %>
			<a href="${pageContext.servletContext.contextPath}/add-user">Add User</a>
		<% } %>			
			<jsp:invoke fragment="body_area" />
			<p></p>
		</div>
		<div class="footer">
			<p>
				Pentru sugestii sau orice alte probleme contactati <a
					href="mailto:it@plastor.ro">it@plastor.ro</a>
			</p>
			<p>
				&copy;Copyright
				<?php echo date('Y');?>
				- <a href="mailto:it@plastor.ro"
					title="Servicul Tehnologia Informatiei">Servicul Tehnologia
					Informatiei</a>
			</p>
		</div>
	</div>
</body>

</html>