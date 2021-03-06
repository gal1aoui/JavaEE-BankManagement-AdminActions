<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clients chercher</title>
<link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/fonts/simple-line-icons/style.min.css">
</head>
<style>
body,html{
	height:100%;
}
</style>
<body>
	<div style="position:absolute; top:5%; left:88%; right:0;">
<a href="consultation">    
    <button class="btn btn-outline-primary d-flex">
        <span style="margin-top: 3px" class="mr-1 spinner-border primary spinner-border-sm" role="status"></span>
        Retour
    </button>
</a>
</div>
<div class="container h-100">
 <div class="row justify-content-center align-items-center h-100">
 	<t:if test="${!empty Clients }">
 	<t:forEach items="${Clients }" var="c">
 		<div class="col-xl-3 col-sm-6 col-12">
        <div class="card">
        <a href="client?id=${c.id }">
          <div class="card-content">
            <div class="card-body">
              <div class="media d-flex justify-content-between">
                <div class="media-body text-left">
                  <h4 class="success">${c.name }</h4>
                  <span>${c.email }</span>
                </div>
                <div class="align-self-center">
                  <i class="icon-user" style="font-size: 36px"></i>
                </div>
              </div>
            </div>
          </div>
          </a>
        </div>
      </div>
 	</t:forEach>
 	</t:if>
 	<t:if test="${empty Clients}">
 	<h1> Pas de Client avec ce nom </h1>
 	</t:if>
 </div>
</div>
</body>
</html>