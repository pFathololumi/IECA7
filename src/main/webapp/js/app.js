/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (){
    var app = angular.module('stockmarket',[]);
    
    app.controller('MarketController',['$scope','$http',function ($scope,$http,$interval){
        var marketCtrl = this;
        $scope.session = null;
        this.symbolsQ = [];
        this.doLogin = function(){
            $http({
                method : 'GET',
                url :'getcustomer',
                params : {'id': $scope.enteredID}
            }).success(function(data,status, headers, config){
                alert('Success:' + data + " " + (typeof data));

                    console.log((typeof data));
                    if(typeof data !=='string')
                    $scope.session=data;
            }).error(function (data,status, headers, config){
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
            'IRANKH':{ 'price': 20000 },
            'SAIPA':{ 'price': 100000 },
            'BMW':{ 'price': 900000 },
            'BENZ':{ 'price': 800000 }
        };

        this.getDataSymbol = function () {
            alert("+");
            $http({
                method: 'GET',
                urt: 'getinstrument',
            }).success(function () {
                alert('Success:' + data + " " + (typeof data));
            }).error(function (data, status, headers, config) {
                alert('Error:' + data);
            });
        }
        var dataSymbol = [{
            "name":"Rana",
            "quantity": 200,
            "sellingOffers": [{ "id": 22, "quantity": 20, "price": 240, "type": "GTC" }, { "id": 22, "quantity": 20, "price": 240, "type": "GTC" }],
            "buyingOffers": [{ "id": 22, "quantity": 20, "price": 240, "type": "GTC" }, { "id": 22, "quantity": 20, "price": 240, "type": "GTC" }]
        },
        {
            "name": "Camaro",
            "quantity": 1000,
            "sellingOffers": [{ "id": 22, "quantity": 20, "price": 240, "type": "GTC" }, { "id": 22, "quantity": 20, "price": 240, "type": "GTC" }],
            "buyingOffers": [{ "id": 22, "quantity": 20, "price": 240, "type": "GTC" }, { "id": 22, "quantity": 20, "price": 240, "type": "GTC" }]
        }];
        this.symbolsQ = dataSymbol;
        this.updateSymbols = function(){
            alert('not implemented');
        }
//        $interval(function(){
//            alert('time');
        //        },1000*15);

        
        $scope.userRequests = [];

        this.createRequest = function (price, quantity, type, buyOrSell) {
            
            $scope.userRequests.push({ 'id': $scope.enteredID, 'instrument': $scope.symbolName, 'price': price, 'quantity': quantity, 'type': type, 'buyOrSell': buyOrSell });
            var len = $scope.userRequests.length - 1;
            
            alert("SUCCESS: "+$scope.userRequests[len].id + " " + $scope.userRequests[len].instrument + " " + $scope.userRequests[len].price +
                " " + $scope.userRequests[len].quantity + " " + $scope.userRequests[len].type + " " + $scope.userRequests[len].buyOrSell);
        }
    }]);

})();
