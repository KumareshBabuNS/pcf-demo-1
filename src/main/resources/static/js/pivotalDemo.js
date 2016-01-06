/**
 * Cloud Foundry application info - V1.0 01/06/2016 B.Prager
 */
/*global angular */
var pivotalDemo = angular.module('pivotalDemo', []);
/*global $scope, $http */
pivotalDemo.controller('appController', [ "$scope", "$http", function appController($scope, $http) {
    'use strict';
    $http.get('http://bprager-host.cfapps.io/appInfo').
        success(function (data) {
            $scope.app = data;
        });
}]);
