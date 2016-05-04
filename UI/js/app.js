/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (){
    var app = angular.module('stockmarket',[]);
    app.controller('MarketController',function ($scope){
        var marketCtrl = this;
        $scope.session = null;
        this.symbols=[];
        this.doLogin = function(){
            $scope.session= marketCtrl.users [$scope.enteredID];
        }
        this.users ={
            "123":{'name':'hamed','money':'2000'}
        };
        var dataSymbol = [{
            "name":"Rana",
            "quantity": 200,
            "sellingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}],
            "buyingOffers":[{"id":22,"quantity":20,"price":240,"type":"GTC"},{"id":22,"quantity":20,"price":240,"type":"GTC"}]
        }];
        this.symbols = dataSymbol;
        this.updateSymbols = function(){
            alert('not implemented');
        }
    });
})();