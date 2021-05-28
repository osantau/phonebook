<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:layout title="Export">
	<jsp:attribute name="body_area">  
    
    <h3>Liste numere de telefon:</h3>
<ul style="list-style-type: disc; margin-left: 50px; padding: 10px;">
    <li>
			<a href="<c:url value="/export?type=interioare" />">Interioare Birouri</a>      
			</li>
 <li style="padding: 7px 0px;">
				<a href="<c:url value="/export?type=personal" />">Interioare Personal</a>
			</li>
 <li>
				<a href="<c:url value="/export?type=filiale" />">Filiale</a>
			</li>
</ul>                                         
    </jsp:attribute>

</t:layout>