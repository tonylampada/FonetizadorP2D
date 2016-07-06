<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Remember: you SHOULD put the line above in all jsps if you want to use Expression Language
(${thisKindOfStuff}), web.xml configurations won't work on GAE --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apoio Total Web</title>
</head>
<body>
	<p>Contador de Letras</p>

<br>
<br>
<c:out value="${strOriginal}" /> contém <c:out value="${strQtdLetras}" /> letras!
		<br>
		<table width="100%" border="1" align="center">
         <c:forEach items="${arrLetras}" var="current">
          <tr>
            <td width="45%">
              <div align="right"></div>
            </td>
            
            <td width="55%">
              <div align="left">${current}</div>
            </td>
          </tr>
          </c:forEach>
        </table>
<br>		
        <br>

</body>
</html>