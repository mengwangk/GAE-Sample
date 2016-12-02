angular.module( 'crossover.myspace', [
  'ui.router',
  'ui.router',
  'placeholders',
  'ui.bootstrap',
  'ngGrid',
  'resources.accounts'
])

/**
 * Each section or module of the site can also have its own routes. AngularJS
 * will handle ensuring they are all available at run-time, but splitting it
 * this way makes each module more "self-contained".
 */
.config(function config( $stateProvider ) {
  $stateProvider.state( 'myspace', {
    url: '/myspace',
    views: {
      "main": {
        controller: 'MyspaceCtrl',
        templateUrl: 'myspace/myspace.tpl.html'
      }
    },
    data:{ pageTitle: 'Myspace' }
  });
})

/**
 * And of course we define a controller for our route.
 */
.controller( 'MyspaceCtrl', function HomeController( $rootScope, $scope, Accounts, $window, $timeout, $modal, $http, $location, $anchorScroll ) {

	$scope.selectedAccount = [];
	$scope.selectedTransaction = [];
	
	var isNotDefined = function(val) { 
		return (angular.isUndefined(val) || val === null);
	};	
	
	function formatDate(date) {
		var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();
		
		if (month.length < 2) {
			month = '0' + month;
		}
		if (day.length < 2) {
			day = '0' + day;
		}
		return [year, month, day].join('-');
	}
	
	var getSavingsAccounts = function(){
		Accounts.getAllSavingsAccounts().get().$promise.then(
				function success(resp, headers) {						
					$scope.accountResponse = resp;
					$scope.accounts = $scope.accountResponse.savingsAccounts;	
					
					if ($scope.accounts.length > 0) {
						getTransactions($scope.accounts[0].accountNo);
					}
				}, function err(httpResponse) {
					$scope.status = httpResponse.status;
				});
	};	
	
	var getTransactions = function(accountNo) {
		Accounts.getSavingsAccountTransaction(accountNo).get().$promise.then(
				function success(resp, headers) {	
					$scope.transactionResponse = resp;
					$scope.transactions = $scope.transactionResponse.transactions;		
					
					angular.forEach($scope.transactions, function (transaction) {
						if (transaction.transType == 1) {
							transaction.transType = 'DEPOSIT';
						} else {
							transaction.transType = 'WITHDRAWAL';
						}
						transaction.transDate = formatDate(transaction.transDate);
					});
				}, function err(httpResponse) {
					$scope.status = httpResponse.status;
				});
	};
	
	$scope.accountGrid = { 
			data: 'accounts',
			enableRowSelection: true,
			enableCellEditOnFocus: false,
			showGroupPanel: true,
			multiSelect: false, 
			showColumnMenu:true,
			showFilter:false,
			enableColumnResize:true,
			columnDefs: [
			{field: 'customerNo', displayName: 'Customer No'},
			{field: 'accountNo', displayName: 'Account No'},
			{field: 'balance', displayName: 'Balance'}			
			],
			selectedItems : $scope.selectedAccount,
			afterSelectionChange: function(rowItem) {
				if (rowItem.selected) {
					// Get transactions related to this account
					var accountNo = $scope.selectedAccount[0].accountNo;
					$scope.status = 'Getting transactions for accountNo ' + accountNo;
					getTransactions(accountNo);
				}
			}
		};
	
	$scope.transactionGrid = { 
			data: 'transactions',
			enableRowSelection: true,
			enableCellEditOnFocus: false,
			showGroupPanel: true,
			multiSelect: false, 
			showColumnMenu:true,
			showFilter:false,
			enableColumnResize:true,
			columnDefs: [
			{field: 'id', displayName: 'ID'},
			{field: 'transDate', displayName: 'Date'},
			{field: 'amount', displayName: 'Amount'},
			{field: 'transType', displayName: 'Type'}			
			],
			selectedItems : $scope.selectedTransaction,
			afterSelectionChange: function(rowItem) {
				if (rowItem.selected) {
				}
			}
		};
	
	
	getSavingsAccounts();
	
})

;

