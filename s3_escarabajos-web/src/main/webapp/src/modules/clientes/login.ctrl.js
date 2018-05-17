(function (ng) {
    var mod = ng.module("clientesModule");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name login.controller:loginCtrl
         * @description
         * Definición del controlador de Angular del módulo Login. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definido
         * para toda la aplicación.
         */
        function ($scope, $http, $state, $rootScope) {

            $scope.user = {};
            $scope.data = $state.data;

            $http.get('data/usuarios.json').then(function (response) {
                $scope.users = response.data;
            });


            /**
             * @ngdoc function
             * @name autenticar
             * @methodOf login.controller:loginCtrl
             * @description
             * Esta función procesa el inicio de sesión usando los datos del
             * $scope.
             */
            $scope.autenticar = function () {
                $scope.flag = false;
                for (var item in $scope.users) {

                    if ($scope.users[item].user === $scope.data.username && $scope.users[item].password === $scope.data.password && $scope.users[item].rol === $scope.data.rol)
                    {
                        $scope.flag = true;
                        $scope.user = $scope.users[item];
                        sessionStorage.token = $scope.user.token;
                        sessionStorage.setItem("username", $scope.user.user);
                        sessionStorage.setItem("rol", $scope.data.rol);
                        sessionStorage.setItem("id", $scope.user.id);
                        sessionStorage.setItem("name", $scope.user.name);

                        $rootScope.currentUser = $scope.user;
                        $state.go('principal', {pagina: 1}, {reload: true});
                        break;
                    }
                }
            };
        }
    ]);
}
)(window.angular);