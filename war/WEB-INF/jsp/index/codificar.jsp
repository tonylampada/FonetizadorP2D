<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Remember: you SHOULD put the line above in all jsps if you want to use Expression Language
(${thisKindOfStuff}), web.xml configurations won't work on GAE --%>
<!-- <fonetizado palavra=""><c:out value="${strFonetizada}" /> </fonetizado>
 --> 
<c:out value="${strCodificada}" />