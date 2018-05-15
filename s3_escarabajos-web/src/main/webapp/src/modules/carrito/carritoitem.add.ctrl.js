(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('addCarritoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            
            
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito').then(function (response) {

                var ruta = 'api/carrito/' + response.data.id + '/items/';
            
                var itemId = $state.params.itemId;
                
                $http.post(ruta + itemId).then(function (response) {
                                        
                    $state.go('carrito', {itemId: response.data.id}, {reload: true});
                }, function(response){
                    
                    if(response.status === 500){
                        $state.go('yaEsta', {itemId: response.data.id}, {reload: true});
                    }
                });


            });
        }
    ]);
}
)(window.angular);