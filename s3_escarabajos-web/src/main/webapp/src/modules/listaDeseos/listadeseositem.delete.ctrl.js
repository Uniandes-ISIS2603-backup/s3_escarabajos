(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.constant("listadeseosContext", "api/carrito/2/items/");
    mod.controller('deleteListadeseosCtrl', ['$scope', '$http', 'listadeseosContext', '$state',
        function ($scope, $http, listadeseosContext, $state) {
            
            var itemId = $state.params.itemId;

                
                $http.delete(listadeseosContext + itemId).then(function (response) {
                    $state.go('listadeseos', {itemId: response.data.id}, {reload: true});
                });


        }
    ]);
}
)(window.angular);