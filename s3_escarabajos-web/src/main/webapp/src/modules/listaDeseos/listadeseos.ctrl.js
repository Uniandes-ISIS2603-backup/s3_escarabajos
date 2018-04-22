(function (ng) {
    var mod = ng.module("ListadeseosMod");
    mod.controller('listadeseosCtrl', ['$scope', '$http',
        function ($scope, $http) {
            $http.get('data/listadeseos.json').then(function (response) {
                $scope.listaDeseos = response.data;
                
                var lista2 = [];
                
                var lista = $scope.listaDeseos.items;
                
                for (i=0;i<lista.length; i++) {
                    
                    var repetido = false;
                    
                    for (j=0;j<lista2.length; j++) {
                        
                        if(lista[i].color===lista[j].color  && lista[i].modelo===lista[j].modelo && lista[i].precio===lista[j].precio){
                            
                            lista2[j].cantidad = lista2[j].cantidad + 1;
                            
                            repetido = true;
                        }
                    }
                    
                    if (repetido === false){
                        
                        lista[i].cantidad = 1;
                        
                        lista2.push(lista[i]);
                    }
                }
               
                
                $scope.listaDeseos.items = lista2;
                

            });
            
            
        }
    ]);
}
)(window.angular);