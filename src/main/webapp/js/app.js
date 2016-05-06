/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    var app = angular.module('stockmarket', []);

    app.controller('MarketController', ['$scope', '$http', function ($scope, $http, $interval) {

        //var dataSymbol;
        $scope.symbolsQ = [];

        $scope.init = function () {
            $http({
                method: 'GET',
                url: 'getinstrument'

            }).success(function (data, status, headers, config) {

                //dataSymbol = data;
                $scope.symbolsQ = data;
                alert('SUCCESS' + data);
                

            }).error(function (data, status, headers, config) {
                alert('Error:' + data);
            });
        }

        $scope.init();
        

        var marketCtrl = this;
        $scope.session = null;
        $scope.notification = null;
        this.symbolsQ = [];
        this.closeNotificationBar = function () {
            $scope.notification = null;
        }
        
        this.doLogin = function () {
            $http({
                method: 'GET',
                url: 'getcustomer',
                params: { 'id': $scope.enteredID }
            }).success(function (data, status, headers, config) {
                console.log((typeof data));
                if (typeof data !== 'string') {
                    $scope.session = data;
                    //$scope.getDataSymbol();
                }
                else
                    $scope.notification = data;

            }).error(function (data, status, headers, config) {
                alert('Error:' + data);
            });
        }


        $scope.symbolName = null;
        $scope.symbolPrice = null;
        this.select = function (value) {
            $scope.symbolName = value;
            $scope.symbolPrice = marketCtrl.symbolList[value];
        }

        $scope.symbols = [
           { 'name': 'IRANKH' },
           { 'name': 'SAIPA' },
           { 'name': 'BMW' },
           { 'name': 'BENZ' }
        ];

        this.symbolList = {
            'IRANKH': { 'price': 20000 },
            'SAIPA': { 'price': 100000 },
            'BMW': { 'price': 900000 },
            'BENZ': { 'price': 800000 }
        };

       
        
        this.getDataSymbol = function () {
            $http({
                method: 'GET',
                url: 'getinstrument'
                
            }).success(function (data, status, headers, config) {
                
                dataSymbol = data;
                $scope.symbolsQ = dataSymbol;
                alert('SUCCESS');
                
            }).error(function (data, status, headers, config) {
                alert('Error:' + data);
            });
        }
        
        //this.symbolsQ = dataSymbol;
        
        this.updateSymbols = function () {
            alert('not implemented');
        }
                //$interval(function(){
                //    $scope.init();
                //},1000*15);


        $scope.userRequests = [];

        this.createRequest = function (price, quantity, type, buyOrSell) {

            $scope.userRequests.push({ 'id': $scope.enteredID, 'instrument': $scope.symbolName, 'price': price, 'quantity': quantity, 'type': type, 'buyOrSell': buyOrSell });
            var len = $scope.userRequests.length - 1;

            alert("SUCCESS: " + $scope.userRequests[len].id + " " + $scope.userRequests[len].instrument + " " + $scope.userRequests[len].price +
                " " + $scope.userRequests[len].quantity + " " + $scope.userRequests[len].type + " " + $scope.userRequests[len].buyOrSell);
        }
    }]);

})();