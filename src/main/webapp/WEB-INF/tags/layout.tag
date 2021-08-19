<%@tag import="oct.soft.model.User"%>
<%@tag description="Template" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
</head>
<body>
	<div id="content">
		<div id="header">		
			<h1 style="padding-top: 15px;">
				<a href="${pageContext.servletContext.contextPath}/" title="Plastor">Agenda
					<span class="title">Telefonica</span>
				</a>

			</h1>
			<% User user = (User) session.getAttribute("user"); %>
			<% if(user != null) {%>
			<div align="right" style="font-size: 14px;">
				Utilizator curent: <b> <%= user.getUsername() %>
				</b>
			</div>
			<% }%>
		</div>

		<div id="tabs">
			<ul>		
			 <% if(user !=null) { %>	  
				<li><a
					href="${pageContext.servletContext.contextPath}/logout"
					accesskey="l"><span class="key">L</span>ogout</a></li>
					<% if(user.getIsadmin()==1) { %>				
				<li><a href="${pageContext.servletContext.contextPath}/user"
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
		<% if(user != null && user.getIsadmin()==1) { %>
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
				2021
				- <a href="mailto:it@plastor.ro"
					title="Servicul Tehnologia Informatiei">Servicul Tehnologia
					Informatiei</a>
			</p>
		</div>
	</div>
	<script type="text/javascript">
                        $(document).ready(function () {
                            $("#faq_search_input").focus();
                            $("#faq_search_input").livesearch({
                                searchCallback: function (term) {
                                    //browser compatibility                                 
                                    if ($.browser.msie || $.browser.webkit) {
                                        $("#faq_search_input").keydown(function (e) {
                                            if (e.keyCode == 8)
                                                $('.gbox').html("").show();
                                            else if (e.keyCode == 13)
                                                e.preventDefault();
                                        });
                                    } else {
                                        $("#faq_search_input").keypress(function (e) {
                                            if (e.keyCode == 8)
                                                $('.gbox').html("").show();
                                            else if (e.keyCode == 13)
                                                e.preventDefault();
                                        });
                                    }
                            //end browser compatibility
                                    if (term.length >= 2) {
                                        $.ajax({
                                            type: "POST",
                                            url: "${pageContext.servletContext.contextPath}/search",
                                            data: "keyword=" + term,
                                            success: function (response)
                                            {												
                                                $('.gbox').html(response).show();
                                            }
                                        });
                                    } else if (term.length == 1)
                                        $('.gbox').html("").show();
                                },

                                innerText: "",
                                queryDelay: 250,
                                minimumSearchLength: 2
                            });
                        });
                    </script>
</body>

</html>