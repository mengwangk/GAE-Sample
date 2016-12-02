angular.module('resources.accounts', [ 'ngResource' ]);
angular.module('resources.accounts').factory(
		'Accounts',
		[
				'$rootScope',
				'$resource',
				'$location',
				'$http',
				function($rootScope, $resource, $location, $http) {
					var accountService = {};									
					accountService.getAllSavingsAccounts = function() {							
							var path = $rootScope.host + '/services/accounts/savings/';
							return $resource(path, {},
									{
										get : {
											method : 'GET',
											isArray : false
										}
									});
					};
					accountService.getSavingsAccountTransaction = function(accountNo) {							
							var path = $rootScope.host + '/services/accounts/savings/' + accountNo;
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