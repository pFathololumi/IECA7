/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (){
    var app = angular.module('stockmarket',[]);
    var symbolSession = null;
    app.controller('MarketController',function ($scope,$interval){
        var marketCtrl = this;
        $scope.session = null;
        this.symbolsQ=[];
        this.doLogin = function(){
            $scope.session= marketCtrl.users [$scope.enteredID];
        }
        this.users ={
            "123":{'name':'hamed','money':'2000'},
            "80":{'name':'parisa','money':'2000'}
        };

        $scope.symbolSession = null;
        this.select = function () {
            $scope.symbol = marketCtrl.symbolList[$scope.selectSymbol];
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

        var dataSymbol = [{
            "name":"Rana",
            "quantity": 200,
            "sellingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}],
            "buyingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}]
        },
        {
            "name":"Camaro",
            "quantity": 1000,
            "sellingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}],
            "buyingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}]
        }];
        this.symbolsQ = dataSymbol;
        this.updateSymbols = function(){
            alert('not implemented');
        }
//        $interval(function(){
//            alert('time');
//        },1000*15);
    });
//    app.controller('mycontroller', ['$scope','$modal', function ($scope, $modal) {
//        this.load = function () {
//            alert(symbolSession.price);
//            return symbolSession.price;
//        }
//    }]);
})();
