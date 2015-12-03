var App = angular.module('ebooking', [
    'ngRoute','ngAnimate','ui.calendar','ui.bootstrap.datetimepicker','ui.bootstrap','ngFileUpload'
]).run(function() {
});
/**
 * Used for routing in public.html template page
 */
App.config(['$routeProvider', '$controllerProvider', '$compileProvider', '$locationProvider',
    function($routeProvider, $controllerProvider, $compileProvider, $locationProvider, $routeParams) {
        App.registerCtrl = $controllerProvider.register;
        App.registerDirective = $compileProvider.directive;
        $routeProvider.
                /* Profile */
                when('/', {templateUrl: '_apps/public/set_start.html'}).
                when('/index', {templateUrl: '_apps/public/set_start.html'}).
                when('/category/:id', {templateUrl: '_apps/public/set_facility.html'}).
                when('/facility/:id', {templateUrl: '_apps/public/set_date.html'}).
                when('/booking/add/:facilityId/:startDate/:endDate', {templateUrl: '_apps/public/set_booking.html'}).
                when('/booking/success/:id', {templateUrl: '_apps/public/success.html'}).
                when('/admin/list', {templateUrl: '_apps/admin/admin_list.html'}).
                when('/admin/lookup', {templateUrl: '_apps/admin/lookup.html'}).
                when('/admin/facility', {templateUrl: '_apps/admin/facility.html'}).
                when('/contact', {templateUrl: '_apps/public/contact.html'}).
                when('/about', {templateUrl: '_apps/public/about.html'}).

                otherwise({
                    redirectTo: '/404'
                });
        $locationProvider
                .html5Mode(true)
                .hashPrefix('!');
    }]);


App.directive('fallbackSrc', function () {
    var fallbackSrc = {
        link: function postLink(scope, iElement, iAttrs) {
            iElement.bind('error', function() {
                angular.element(this).attr("src", iAttrs.fallbackSrc);
            });
        }
    }
    return fallbackSrc;
});