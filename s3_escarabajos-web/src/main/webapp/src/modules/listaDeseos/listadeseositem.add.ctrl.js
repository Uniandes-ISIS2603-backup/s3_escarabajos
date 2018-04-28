(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.constant("listaDeseosContext", "api/carrito/2/items/");
    mod.controller('addListaDeseosCtrl', ['$scope', '$http', 'listaDeseosContext', '$state',
        function ($scope, $http, listaDeseosContext, $state) {
            
            var itemId = $state.params.itemId;

                
                $http.post(listaDeseosContext + itemId).then(function (response) {
                    $state.go('listadeseos', {itemId: response.data.id}, {reload: true});
                });


        }
    ]);
}
)(window.angular);