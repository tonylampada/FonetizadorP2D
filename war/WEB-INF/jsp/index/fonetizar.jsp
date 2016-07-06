<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Remember: you SHOULD put the line above in all jsps if you want to use Expression Language
(${thisKindOfStuff}), web.xml configurations won't work on GAE --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <fonetizar palavra="<c:out value="${strOriginal}" />"><c:out value="${strFonetizada}" /> </fonetizar>
 