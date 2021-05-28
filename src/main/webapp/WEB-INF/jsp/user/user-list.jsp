<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Lista utilizatori">
	<jsp:attribute name="body_area">
	<p><a href="<c:url value="/user"/>?action=add">Adauga user</a></p>
	<table cellspacing="5px">
    <thead>
    <th>Nume</th>
    <th>Prenume</th>
    <th>Email</th>
    <th>Username</th>
    <th>Parola</th>
    <th>&nbsp;</th>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="u">        
        <tr>
            <td>${u.fname}</td>
            <td>${u.lname}</td>
            <td>${u.email}</td>
            <td>${u.username}</td>
            <td>${u.decodedPassword}</td>
            <td>           
                <a
							href="<c:url value="/user"/>?action=update&id=${u.id}">Modifica</a>&nbsp;               
                <a
							href=" <c:url value="/user"/>?action=delete&id=${u.id}"
							onclick="return confirm('Stergeti inregistrarea?');">Sterge</a>				
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>	  
	</jsp:attribute>
</t:layout>