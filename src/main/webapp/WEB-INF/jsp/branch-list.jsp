<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Filiale"> 
    <jsp:attribute name="body_area">        
        <h3>Filiale</h3>    
        <p>
         <a href="<c:url value="branches"/>?add">Adauga Filiala</a>
         </p>         
        <display:table id="row" name="branchList" pagesize="10" requestURI="${pageContext.servletContext.contextPath}/branches">
        <display:column title="Nr.<br>Crt."><c:out value="${row_rowNum }" /></display:column>
        <display:column title="Denumire"><c:out value="${row.name }"/></display:column>
        <display:column title="Edit"><a href="<c:url value="/branches" />?edit=${row.idoffice}">
        <img src="${pageContext.servletContext.contextPath}/assets/images/pencil.png" border="0"/>
        </a></display:column>
        </display:table>
</jsp:attribute>

</t:layout>