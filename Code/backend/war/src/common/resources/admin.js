angular.module('resources.admin', [ 'ngResource' ]);
angular.module('resources.admin').factory(
		'Admin',
		[
				'$rootScope',
				'$resource',
				'$location',
				'$http',
				function($rootScope, $resource, $location, $http) {
					var accountService = {};									
					accountService.getAllSavingsAccounts = function() {							
							var path = $rootScope.host + '/services/admin/savings/';
							return $resource(path, {},
									{
										get : {
											method : 'GET',
											isArray : false
										}
									});
					};
					accountService.getSavingsAccountTransaction = function(accountNo) {							
							var path = $rootScope.host + '/services/admin/savings/' + accountNo;
							return $resource(path, {},
									{
										get : {
											method : 'GET',
											isArray : false
										}
									});
					};
					return accountService;

		} ]);