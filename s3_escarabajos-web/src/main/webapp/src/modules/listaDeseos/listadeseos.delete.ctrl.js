(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.constant("listadeseosContext", "api/carrito/2/items");
    mod.controller('vaciarListadeseosCtrl', ['$scope', '$http', 'listadeseosContext', '$state',
        function ($scope, $http, listadeseosContext, $state) {


                
                $http.delete(listadeseosContext).then(function (response) {
                    $state.go('listadeseos', {reload: true});
                });


        }
    ]);
}
)(window.angular);