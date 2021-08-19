<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Adauare / Editare Utilizator">
	<jsp:attribute name="body_area">
	<jsp:useBean id="user" scope="request"
			class="oct.soft.model.User" />
            <jsp:setProperty name="user" property="*" />	
	<form action="<c:url value="/user"/>" method="post">
	<input type="hidden" name="id" value="${user.id }">
	<c:if test="${not empty errors }">
<div style="color: red;">${errors}</div>
</c:if> 
	<table cellpadding="10px">

<tr>
<td>Nume: </td>
<td>
<input type="text" name="fname" value="${user.fname }"
					style="background-color: #A9F5A9;" />
</td>
</tr>
<tr>
<td>Prenume: </td>
<td>
<input type="text" name="lname" value="${user.lname }"
					style="background-color: #A9F5A9;" />
</td>
</tr>
<tr>
<td>Email: </td>
<td>
<input type="text" name="email" value="${user.email }"
					style="background-color: #A9F5A9;" />
</td>
</tr>
<tr>
<td>Parola: </td>
<td>
<input type="text" name="decodedPassword"
					value="${user.decodedPassword}"
					style="background-color: #A9F5A9;" />
</td>
</tr>

<tr>
<td>Nume utilizator: </td>
<td>
<input type="text" name="username" 
					value="${user.username}"
					style="background-color: #A9F5A9;" />
</td>
</tr>

<tr>
<td></td>
				<td align="right"><input type="submit" value="Adauga / Actualizeaza" />&nbsp;
        <a href="<c:url value="/user"/>"
					style="text-decoration: none;">
            <input type="button" value="Cancel" /> 
                 </a> </td>
</tr>
</table>   
</form>
	</jsp:attribute>
</t:layout>