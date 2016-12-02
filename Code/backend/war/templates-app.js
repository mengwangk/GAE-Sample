angular.module('templates-app', ['admin/admin.tpl.html', 'home/home.tpl.html', 'myspace/myspace.tpl.html']);

angular.module("admin/admin.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("admin/admin.tpl.html",
    "<div class=\"container\">\n" +
    "	<div class=\"row\">\n" +
    "		<div class=\"col-md-3\" id=\"sidebar\" ng-include=\"'assets/view/admin_sidebar.html'\"></div>\n" +
    "		\n" +
    "		\n" +
    "		<div class=\"col-md-9\" id=\"content\">\n" +
    "		\n" +
    "		<div class=\"alert alert-info\">\n" +
    "		<p>\n" +
    "			<h4 class=\"alert-heading\">Savings Accounts </h3>\n" +
    "			</p> <br/>				\n" +
    "			 <div class=\"gridStyle\" ng-grid=\"accountGrid\">\n" +
    "			</div>	\n" +
    "		</div>\n" +
    "	\n" +
    "	\n" +
    "		<div class=\"alert alert-warning\">\n" +
    "		<p>\n" +
    "			<h4 class=\"alert-heading\">Transactions </h3>\n" +
    "			</p> <br/>				\n" +
    "			 <div class=\"gridStyle\" ng-grid=\"transactionGrid\">\n" +
    "			</div>	\n" +
    "		</div>\n" +
    "	\n" +
    "		\n" +
    "		<div class=\"alert alert-danger\">\n" +
    "		<p>\n" +
    "		{{ status }}\n" +
    "		</p> 	\n" +
    "		</div>\n" +
    "	\n" +
    "			\n" +
    "		</div>\n" +
    "	</div>\n" +
    "	\n" +
    "	\n" +
    "</div>\n" +
    "					\n" +
    "	\n" +
    "		\n" +
    "		\n" +
    "	\n" +
    "");
}]);

angular.module("home/home.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("home/home.tpl.html",
    "<div class=\"marketing\">\n" +
    "  <div class=\"row\">\n" +
    "    <div class=\"col-xs-12 col-sm-6 col-md-4\">\n" +
    "      <h4><i class=\"fa fa-thumbs-up\"></i> Good to Go!</h4>\n" +
    "		 <p>\n" +
    "			 Login to view your accounts.\n" +
    "		 </p>\n" +
    "		 \n" +
    "		 <p>\n" +
    "		 	To login as administrator use the user name \"crossovertest256@gmail.com\" and password \"raspbian_1234\";\n" +
    "		 </p>\n" +
    "    </div>\n" +
    "  </div>\n" +
    " \n" +
    "</div>\n" +
    "\n" +
    "");
}]);

angular.module("myspace/myspace.tpl.html", []).run(["$templateCache", function($templateCache) {
  $templateCache.put("myspace/myspace.tpl.html",
    "<div class=\"container\">\n" +
    "	<div class=\"row\">\n" +
    "		<div class=\"col-md-3\" id=\"sidebar\" ng-include=\"'assets/view/account_sidebar.html'\"></div>\n" +
    "		\n" +
    "		\n" +
    "		<div class=\"col-md-9\" id=\"content\">\n" +
    "		\n" +
    "		<div class=\"alert alert-info\">\n" +
    "		<p>\n" +
    "			<h4 class=\"alert-heading\">Savings Accounts </h3>\n" +
    "			</p> <br/>				\n" +
    "			 <div class=\"gridStyle\" ng-grid=\"accountGrid\">\n" +
    "			</div>	\n" +
    "		</div>\n" +
    "	\n" +
    "		\n" +
    "		<div class=\"btn-group\" role=\"group\" >\n" +
    "			<p>\n" +
    "		  <button type=\"button\" class=\"btn btn-default\">Account Transfer (TBD)</button>\n" +
    "		  <button type=\"button\" class=\"btn btn-default\">Print Statement (TBD)</button>\n" +
    "		  </p><br/>\n" +
    "		</div>\n" +
    "\n" +
    "		\n" +
    "		<div class=\"alert alert-warning\">\n" +
    "		<p>\n" +
    "			<h4 class=\"alert-heading\">Transactions </h3>\n" +
    "			</p> <br/>				\n" +
    "			 <div class=\"gridStyle\" ng-grid=\"transactionGrid\">\n" +
    "			</div>	\n" +
    "		</div>\n" +
    "	\n" +
    "		\n" +
    "		<div class=\"alert alert-danger\">\n" +
    "		<p>\n" +
    "		{{ status }}\n" +
    "		</p> 	\n" +
    "		</div>\n" +
    "	\n" +
    "			\n" +
    "		</div>\n" +
    "	</div>\n" +
    "	\n" +
    "	\n" +
    "</div>\n" +
    "					\n" +
    "	\n" +
    "		\n" +
    "		\n" +
    "	\n" +
    "");
}]);
