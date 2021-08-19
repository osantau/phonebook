<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Personal"> 
    <jsp:attribute name="body_area">        
        <h3>Personal</h3>    
        <p>
         <a href="<c:url value="person"/>?action=add">Adauga Persoana</a>
         </p>         
        <display:table id="row" name="personList" pagesize="15" requestURI="${pageContext.servletContext.contextPath}/person">
        <display:column title="Nr.<br>Crt."><c:out value="${row_rowNum }" /></display:column>
        <display:column title="Nume"><c:out value="${row.fname }"/></display:column>
        <display:column title="Prenume"><c:out value="${row.lname }"/></display:column>
        <display:column title="Birou"><c:out value="${row.birou }"/></display:column>
        <display:column title="Filiala"><c:out value="${row.filiala }"/></display:column>
        <display:column title="Edit"><a href="<c:url value="/person" />?action=edit&idperson=${row.idperson}">
        <img src="${pageContext.servletContext.contextPath}/assets/images/pencil.png" border="0"/>
        <a href="<c:url value="/person" />?action=delete&idperson=${row.idperson}">
        <img src="${pageContext.servletContext.contextPath}/assets/images/delete.png" border="0"/>
        </a></display:column>
        </display:table>
</jsp:attribute>

</t:layout>