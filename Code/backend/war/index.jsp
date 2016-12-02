<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.crossover.Constants" %>
<%@ page import="com.crossover.Constants" %>
<%@ page import="com.crossover.entity.Customer" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();
Customer customer = (Customer)request.getSession().getAttribute(Constants.ATTRIBUTE_CUSTOMER);
%>
<!DOCTYPE html>
<html ng-app="crossover" ng-controller="AppCtrl">
  <head>
    <title ng-bind="pageTitle"></title>

    <!-- social media tags -->
    <meta name="twitter:card" content="summary">
    <meta name="twitter:site" content="@mengwangk">
    <meta name="twitter:title" content="crossover">
    <meta name="twitter:description" content="Crossover sample code">
    <meta name="twitter:creator" content="@mengwangk">
    <meta property="og:title" content="crossover" />
    <meta property="og:type" content="website" />
    <meta property="og:description" content="Crossover sample code.">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- font awesome from BootstrapCDN -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

    <!-- compiled CSS -->
    <link rel="stylesheet" type="text/css" href="vendor/angular-grid/ng-grid.min.css" />
    <link rel="stylesheet" type="text/css" href="assets/Crossover-0.0.1.css" />

    <!-- compiled JavaScript -->
    <script type="text/javascript" src="vendor/angular/angular.js"></script>
    <script type="text/javascript" src="vendor/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="vendor/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
    <script type="text/javascript" src="vendor/placeholders/angular-placeholders-0.0.1-SNAPSHOT.min.js"></script>
    <script type="text/javascript" src="vendor/angular-ui-router/release/angular-ui-router.js"></script>
    <script type="text/javascript" src="vendor/angular-resource/angular-resource.min.js"></script>
    <script type="text/javascript" src="vendor/angular-grid/build/ng-grid.min.js"></script>
    <script type="text/javascript" src="src/app/admin/admin.js"></script>
    <script type="text/javascript" src="src/app/app.js"></script>
    <script type="text/javascript" src="src/app/home/home.js"></script>
    <script type="text/javascript" src="src/app/myspace/myspace.js"></script>
    <script type="text/javascript" src="src/common/resources/accounts.js"></script>
    <script type="text/javascript" src="src/common/resources/admin.js"></script>
    <script type="text/javascript" src="templates-common.js"></script>
    <script type="text/javascript" src="templates-app.js"></script>

  </head>
  <body>
    <div class="container">
      <div class="navbar navbar-default">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" ng-init="menuCollapsed = true"
            ng-click="menuCollapsed = ! menuCollapsed">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <div class="navbar-brand">
            Crossover
          </div>
        </div>
        <div class="collapse navbar-collapse" collapse="menuCollapsed">
          <ul class="nav navbar-nav">
            <li ui-sref-active="active">
              <a href ui-sref="home">
                <i class="fa fa-home"></i>
                Home
              </a>
            </li>
            
            <% if (user != null) { %>
             
             <li ui-sref-active="active">
              <a href ui-sref="myspace">
               <i class="fa fa-asterisk"></i>
                Myspace
              </a>
            </li>
            
               	 <% if (customer != null && customer.isAdmin()) { %>
		            <li ui-sref-active="active">
		              <a href ui-sref="admin">
		               <i class="fa fa-square"></i>
		                Admin Panel
		              </a>
		            </li>
            
            	  <% } %>

             <% } %>

          </ul>
          
		<ul class="nav navbar-nav navbar-right">
			<%
			    if (user != null) {
			%>
			<li> <br/>
			 <i class="fa fa-user">&nbsp;Hello <%= user.getNickname() %>&nbsp;&nbsp;</i>
			</li>
          	<li> 
          		<a href="<%=userService.createLogoutURL(request.getRequestURI()) %>" class="btn btn-default btn-small">
      			<i class="fa fa-sign-in"></i>&nbsp;&nbsp;Logout</a>
      		</li>
			<%
			    } else {
			%>
				<li> 
          		<a href="<%=userService.createLoginURL(request.getRequestURI()) %>" class="btn btn-default btn-small">
      			<i class="fa fa-sign-in"></i>&nbsp;&nbsp;Login</a>
      		</li>
            <%
			    }
			%>
		</ul>
        </div>
      </div>
    </div>

    <div class="container" ui-view="main"></div>

    <footer class="footer">
      <div class="container">
        <div class="footer-inner">
          <p>
            (c) 2015 <a href="http://www.twit88.com">Koh Meng Wang</a> -  <small> v0.0.1</small>
          </p>
        </div>
      </div>
    </footer>
  </body>
</html>
