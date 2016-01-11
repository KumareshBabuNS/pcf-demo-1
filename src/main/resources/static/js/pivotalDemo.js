/**
 * Cloud Foundry application info - V1.0 01/06/2016 B.Prager
 */
/*global angular, $scope, $http, console */
var pivotalDemo = angular.module('pivotalDemo', ['timer'])
    .controller('appController', [ "$scope", "$http", function appController($scope, $http) {
        'use strict';
        $scope.loading = true;
        $http.get('/appInfo').
            success(function (data) {
                data.loaded = true;
                $scope.app = data;
            });
        $http.get('/upTime').
            success(function (data) {
                data.loaded = true;
                $scope.uptime = data;
                $scope.loading = false;
            });
    }]);
