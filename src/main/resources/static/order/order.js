angular.module('app').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.createOrder= function(){
    $http({
        url: contextPath + 'api/v1/orders/create',
        method: 'POST',
        params: {
            phone: $scope.newOrder.phone,
            address: $scope.newOrder.address
        }
    })
        .then(function (response) {
            console.log("OK");
            $scope.newOrder=null;
            $scope.cartContentRequest();

        });

    };
}