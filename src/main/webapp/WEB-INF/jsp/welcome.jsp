<%-- 
    Document   : newjsp
    Created on : 29.11.2017, 14:04:56
    Author     : istorozhev
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<META HTTP-EQUIV="Refresh" CONTENT="10">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    
    <style>
td {
   border: 1px #d0d0d0 dashed;
}

    .status_online {
        background: #ffff00;
    }
    .status_offline {
        background: #d0d0d0;
    }
    .status_locked {
        background: #d0f0d0;
    }
    .status_active {
        background: #ffffff;
    }
    .status_inactive {
        background: #d0d0d0;
    }
    
    



</style>
    
    
   <body>
        
	<c:url value="/resources/text.txt" var="url"/>
	<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
	
	<br>
	
        
        <table>
        <c:forEach items="${workers}" var="worker">     
            <td class="${worker.status_class}">
                ${worker.fio}
            </td>
                
                <c:forEach items="${worker.last_issues}" var="issue">    
                    <tr>
                        <td>
                            <fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value = "${issue.last_timer_tm.getTime()}" />
                        </td>
                        <td class="${issue.status_class}>
                        ${issue.subject} 
                        </td>
                        <td>
                        ${issue.timer_value_readable()}
                        </td>
                    </tr>
                </c:forEach>
               
        </c:forEach>
        </table>
        
</body>
</html>
