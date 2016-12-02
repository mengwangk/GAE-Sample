angular.module( 'crossover', [
  'templates-app',
  'templates-common',
  'crossover.home',
  'crossover.myspace',
  'crossover.admin',
  'ui.router',
  'ngResource', 
  'ui.bootstrap'
])

.config( function myAppConfig ( $stateProvider, $urlRouterProvider ) {
  $urlRouterProvider.otherwise( '/home' );
})

.run( function run () {
})

.controller( 'AppCtrl', function AppCtrl ($rootScope, $scope, $location ) {
	
  $rootScope.host = $location.protocol() + '://' + $location.host() + ':' + $location.port();
  
  $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
    if ( angular.isDefined( toState.data.pageTitle ) ) {
      $scope.pageTitle = toState.data.pageTitle + ' | crossover' ;
    }
  });
})

;

