<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="Birouri"> 
    <jsp:attribute name="body_area">        
        <h3>Birouri</h3>    
        <p>
         <a href="<c:url value="office"/>?add">Adauga Birou</a>
         </p>         
        <display:table id="row" name="officeList" pagesize="15" requestURI="${pageContext.servletContext.contextPath}/office">
        <display:column title="Nr.<br>Crt."><c:out value="${row_rowNum }" /></display:column>
        <display:column title="Denumire"><c:out value="${row.name }"/></display:column>
        <display:column title="Filiala"><c:out value="${row.branch.name }"/></display:column>
        <display:column title="Edit"><a href="<c:url value="/office" />?edit=${row.idoffice}">
        <img src="${pageContext.servletContext.contextPath}/assets/images/pencil.png" border="0"/>
        </a></display:column>
        </display:table>
</jsp:attribute>

</t:layout>