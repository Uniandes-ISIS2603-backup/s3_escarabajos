(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('carritoCtrl', ['$scope', '$http',
        function ($scope, $http) {
            $http.get('api/carrito/'+1+'/items').then(function (response) {
                $scope.carrito = response.data;
                
                var lista2 = [];
                
                var lista = $scope.carrito;
                
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
               
                
                $scope.carrito = lista2;
                

            });
            
            
        }
    ]);
}
)(window.angular);