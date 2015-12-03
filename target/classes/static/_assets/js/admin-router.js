
var App = angular.module('ebooking', [
    'ngRoute','ngAnimate','ui.bootstrap.datetimepicker','ui.bootstrap','ngFileUpload'
]).run(function() {
});

/**
 * Used for Routing in admin.html template
 */
App.config(['$routeProvider', '$controllerProvider', '$compileProvider', '$locationProvider',
    function($routeProvider, $controllerProvider, $compileProvider, $locationProvider, $routeParams) {
        App.registerCtrl = $controllerProvider.register;
        App.registerDirective = $compileProvider.directive;
        $routeProvider.
                /* Profile */
                when('/', {templateUrl: '_apps/public/admin_list.html'}).
                when('/list', {templateUrl: '_apps/admin/admin_list.html'}).
                when('/lookup', {templateUrl: '_apps/admin/lookup.html'}).
                when('/facility', {templateUrl: '_apps/admin/facility.html'}).

                otherwise({
                    redirectTo: '/404'
                });
        $locationProvider
                .html5Mode(true)
                .hashPrefix('!');
    }]);
