(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.controller('listadeseosCtrl', ['$scope', '$http',
        function ($scope, $http) {
            // cambiar el 1 de la ruta por el id del cliente que este logeado
            $http.get('api/clientes/' + 1 + '/listadeseos').then(function (response) {

                ruta = 'api/listadeseos/' + response.data.id + '/items';
                
                                
                $http.get(ruta).then(function (response) {
                    $scope.listadeseos = response.data;

                    var lista2 = [];

                    var lista = $scope.listadeseos;

                    var total = 0;

                    for (i = 0; i < lista.length; i++) {

                        total = total + lista[i].precio;

                        var repetido = false;

                        for (j = 0; j < lista2.length; j++) {

                            if (lista[i].color === lista[j].color && lista[i].modelo === lista[j].modelo && lista[i].precio === lista[j].precio && lista[i].tipo === lista[j].tipo && lista[i].referencia === lista[j].referencia) {

                                lista2[j].cantidad = lista2[j].cantidad + 1;

                                repetido = true;
                            }
                        }

                        if (repetido === false) {

                            lista[i].cantidad = 1;

                            lista2.push(lista[i]);
                        }
                    }

                    $scope.total = total;

                    $scope.listadeseos = lista2;

                });

            });

        }
    ]);
}
)(window.angular);