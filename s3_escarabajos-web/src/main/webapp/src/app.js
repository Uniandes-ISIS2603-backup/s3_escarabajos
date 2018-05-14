(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies       
        'moduloBicis',
        'moduloBicisUsadas',
        'CarritoMod',
        'reclamosModule',
        'ListadeseosMod',
        'AccesoriosMod',
        'clientesModule',
        'moduloFacturas',
        'vendedoresModule',
        'catalogoModule',
        'modelosModule',
        'adminModule'



    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
    app.run(['$rootScope', function ($rootScope){


            /**
             * @ngdoc function
             * @name isAuthenticated
             * @methodOf mainApp.module:mainApp
             * @description Esta función define si el usuario se encuentra
             * dentro de su cuenta.
             * @returns {Boolean} Verdadero si está dentro de su cuenta.
             */
            $rootScope.isAuthenticated = function () {

                if (sessionStorage.getItem("username") != null) {
                    $rootScope.currentUser = sessionStorage.getItem("name");
                    return true;
                } else {
                    return false;
                }
            };

            /**
             * @ngdoc function
             * @name hasPermissions
             * @methodOf mainApp.module:mainApp
             * @description Esta función define si el usuario tiene permisos
             * para acceder a la aplicación.
             * @returns {Boolean} Verdadero si el usuario tiene permisos.
             */
            $rootScope.hasPermissions = function () {
                if (($rootScope.isAuthenticated) && (roles.indexOf(sessionStorage.getItem("rol")) > -1)) {
                    return true;
                } else {
                    return false;
                }
            };
        }]);
})(window.angular);

