/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (){
    var app = angular.module('stockmarket',[]);
    app.controller('MarketController',function ($scope){
        var marketCtrl = this;
        this.session = null;
        this.symbols=[];
        this.doLogin = function(){
            $scope.session= marketCtrl.users [$scope.enteredID];
        }
        this.users ={
            "123":{'name':'hamed','money':'2000'}
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

        //var dataSymbol = [{
        //    "name":"Rana",
        //    "quantity": 200,
        //    "sellingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}],
        //    "buyingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}]
        //}];
        //this.symbols = dataSymbol;
        //this.updateSymbols = function(){
        //    alert('not implemented');
        //}

    });
})();