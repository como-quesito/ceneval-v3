/**
 * Created by campitos on 4/08/15.
 */
angular.module('hello',['ngRoute','ngResource','ngFileUpload'])
    .config(function($routeProvider,$httpProvider){
        $routeProvider.when('/',{
            templateUrl:'home.html',
            controller:'home'
        }).when('/ingresar',{
            templateUrl:'ingresar.html',
            controller:'navigation'
        }).when('/guias',{
            templateUrl:'guias.html',
            controller:'guias',

        }). when('/reactivos',{
            templateUrl:'reactivos.html',
            controller:'reactivos'
        }). when('/profesores',{
            templateUrl:'profesores.html',
            controller:'profesores'
        }).when('/tema-reactivos',{
            templateUrl:'tema-reactivos.html',
            controller:'tema-reactivos'
        }).
         otherwise('/');
        $httpProvider.defaults.headers.common['X-Requested-With']='XMLHttpRequest';
    })
.controller('home', function($scope, $http){
        $scope.mensaje='';
        $http.get('recurso').success(function(data) {
            $scope.saludo = data;
            console.log('Ya ha sido wacheado por hoy');
        })


    })
.controller('navigation',function($rootScope,$scope,$http,$location,$route){
        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };
        $rootScope.temita;
        $rootScope.loading=false;
        var authenticate=function(credentials, callback){

            var headers=credentials ? { authorization:"Basic "
            +btoa(credentials.username+ ":"+credentials.password)
            }:{};
            $http.get('user',{headers:headers}).success(function(data){
                if(data.username){
                    $rootScope.loading = false;
                    console.log("mis orejas:"+data.autoridad);
                    $rootScope.esprofesor=false;
                    $rootScope.haypreguntas=false;
                    $rootScope.esadministrador=false;
                    $rootScope.mitemaexamen='';
                    $rootScope.authenticated=true;
                    $rootScope.reactivostema=[];
                    $rootScope.indice=0;
                    $rootScope.titulo1;
                    $rootScope.titulo2;
                    $rootScope.titulo3;
                    $rootScope.titulo4;
                    $rootScope.mostratboton=false;

                    //El siguiente es para que sin ahcer click aparezca el menu en los reactivos
                    /*
                    ESTE ESTA MEGA INTERESANTE POR QUE LOS LI DE DAS EL PODER DE VER CUAL SELECCIONAS Y ASI TAMBIEN ASIGNAR VALORES ESPECIFICOS
                    A UN MODELO.
                     */
                    $rootScope.propsA = [{label: "A1", tema:" Diagnóstico del problema"},{label: "A2", tema:" Modelado de los requerimientos"}];
                    $rootScope.propsB = [{label: "B1", tema:" Diseño de la solución del problema de TI"},{label: "B2", tema:" Desarrollo de sistemas"},
                        {label:"B3", tema:" Implantación de sistemas"},{label:"B4", tema:" Aplicación de modelos matemáticos"}];
                    $rootScope.propsC = [{label: "C1", tema:" Administración de proyectos de TI"},{label: "C2", tema:" Control de calidad de proyectos de TI"}];
                    $rootScope.propsD = [{label: "D1", tema:" Gestión de redes de datos"},{label: "D2", tema:" Gestión de bases de datos"},
                        {label:"D3", tema:" Gestión de sistemas operativos o lenguajes de prog."}];
                    $rootScope.seleccionarTemasA=function(prop){
                        console.log("hay me oprimiste:"+prop.label);
                        $rootScope.mitemaexamen=prop.label;
                        procesarExamenTemita( $rootScope.mitemaexamen);
                    }
                    $rootScope.seleccionarTemasB=function(prop){
                        console.log("hay me oprimiste:"+prop.label);
                        $rootScope.mitemaexamen=prop.label;
                        procesarExamenTemita( $rootScope.mitemaexamen);
                    }
                    $rootScope.seleccionarTemasC=function(prop){
                        console.log("hay me oprimiste:"+prop.label);
                        $rootScope.mitemaexamen=prop.label;
                        procesarExamenTemita( $rootScope.mitemaexamen);
                    }
                    $rootScope.seleccionarTemasD=function(prop){
                        console.log("hay me oprimiste:"+prop.label);
                        $rootScope.mitemaexamen=prop.label;
                        procesarExamenTemita( $rootScope.mitemaexamen);
                    }


                    procesarExamenTemita=function(temita) {
                        $http.get('examen/'+temita).success(function (data) {

                            console.log("haaaaaayyyy mis nachas!!");
                            $rootScope.preguntas=data.length;
                            $rootScope.tieneimagen=false;
                            $scope.numeropregunta='';
                            $rootScope.indice=0;
                            $rootScope.mostrarboton=false;
                            $rootScope.reactivostema=data;
                            if(data.length>0)$rootScope.haypreguntas=true;
                            if(data.length===0)$rootScope.haypreguntas=false;
                            console.log($rootScope.haypreguntas);
                        });
                        };
                    console.log("el nombre es este:"+credentials.username);
                    if(data.autoridad==='Profesor')$rootScope.esprofesor=true;
                    if(data.autoridad=='Administrador')$rootScope.esadministrador=true;
                    console.log("El password es este:"+credentials.password);
                    $rootScope.passwordactual=credentials.password;
                    if(data.name==='campitos'){
                        $rootScope.supercan=true;
                    }else{
                        $rootScope.supercan=false;
                    }

                }else{
                    $rootScope.authenticated=false;

                }
                callback && callback();
            }).error(function(){
                $rootScope.authenticated=false;
                callback && callback();
            });
        }
        authenticate();
        $scope.credentials={};
        $scope.login=function(){
            $rootScope.loading=true;
            authenticate($scope.credentials,function(){
                if($rootScope.authenticated){
                    $location.path("/")
                    $scope.error=false;
                    $rootScope.loading=false;
                }else{
                    $location.path("/ingresar");
                    $scope.error=true;
                    $rootScope.loading=false;
                }
            })
        }

        $scope.logout=function(){
            $http.post('logout',{}).success(function(){
                $rootScope.authenticated=false;
                $location.path("/");
            }).error(function(data){
                $rootScope.authenticated=false;
            });
        }
        $scope.cambiarAGuias=function(){
            $scope.mensaje="guias.html";
            console.log("hhhhhhhaaaaddfdffdfa");
        }


        $rootScope.generarExamenPorTema=function(){
            $rootScope.tema="tema1";
        }


    })
    .controller('nada',function($scope){
        $scope.mensaje="";
        $scope.cambiarAGuias=function(){
            $scope.mensaje="guias.html";
            console.log("hhhhhhhaaaaddfdffdfa");
        }
    }).controller('reactivos',function($rootScope,$scope,Upload, $timeout,$rootScope, $http,$resource,$log){
        $scope.hola="hola desde los reactivos";
        //Si quieres usar el parseInt en  DOM (html), deberas u definir la linea que sigue
        $scope.parseInt = parseInt;
        //ESTA ES LA RUTA QUE SE DEBE DE GUARDAR EN LA PRIPIEDAD DE REACTIVOS
         $rootScope.rutaimagen='';
        $scope.titulo='';
        $scope.miradio=parseInt($scope.miradio);
        /*
        $scope.misradiesitos=[];
        $scope.misradiesitos.push({'titulo':$rootScope.titulo1, "acierto":false});

        console.log("ahhhhhhhhhhhh:"+$scope.misradiesitos[0].titulo);
        */
        $rootScope.miruta='leer-imagen/dia250segundo933reactivo.jpg'
        $scope.uploadFiles = function(file) {
            $scope.f = file;
            if (file && !file.$error) {
                file.upload = Upload.upload({
                   // url: 'https://angular-file-upload-cors-srv.appspot.com/upload',
                    url:'cargar-mongo1',
                    file: file
                });

                file.upload.then(function (response) {
                    $timeout(function () {
                        file.result = response.data;
                        $rootScope.rutaimagen=response.data;
                        console.log("HAY MIS HIJOOOOS"+response.data);
                        console.log("hay mis hijos password es:"+$rootScope.passwordactual);
                    });
                }, function (response) {
                    console.log("HAY MIS HIJOOOOS RESPUESTA:"+response);
                    if (response.status > 0)
                        $scope.errorMsg = response.status + ': ' + response.data;
                });

                file.upload.progress(function (evt) {
                    file.progress = Math.min(100, parseInt(100.0 *
                        evt.loaded / evt.total));
                });
            }
        }



        var Profesor=$resource('reactivo/:id',{id:'@id'},{crear:{method:'POST'},
            actualizar:{method:'PUT'}, borrar:{method:'DELETE'}});


/*

        $rootScope.setSelected = function(prop){
            $rootScope.selectedprop = prop;
            console.log(prop.label);
        };
        $rootScope.props = [{label: "1.", tema:"Introduccion"},{label: "2.", tema:"bases"},{label: "3.", tema:"conclusiones"}];

        $rootScope.agarrar=function(){

        }


        //GET Todos

            $scope.reactivo=Reactivo.query(function(){
                console.log($scope.reactivo.length);
            })


        //Get por id
        $scope.buscarPorId=function(){

            $scope.usu=Usuario.get({id:1

            }, function(){
                console.log("Usuario obtenido:"+$scope.usu.nombre);
            });
        }


//UPDATE
        $scope.actualizarUsuario=function(){
            console.log("antes del evento update");
            var usuario = new Usuario();
            usuario.login = $scope.login;
            usuario.password = $scope.password;
            usuario.email =$scope.email;
            usuario.$actualizar(function (data) {
                console.log(data.nombre);
            });
        }

        //DELETE
        $scope.borrarUsuario=function(){
            var usuario=new Usuario();
            usuario.id=5;
            usuario.$borrar();
            console.log("si se borro");
        }
*/
        //POST
        $scope.guardarProfesorReactivo=function() {
            $scope.pregunta;


            console.log("antes del eventooo:"+$rootScope.passwordactual);
            //La siguiemnte nos da la opcion verdadera que se selecciono
            console.log("Este radio"+$scope.miradio);
          var  valores=[];
            $scope.misradiesitos=[];

            for(var i=0;i<4;i++){
                if(i+''===$scope.miradio) {
                    valores[i] = true;


                }
                else{
                    valores[i]=false;}
                console.log(i +" El valor es:"+valores[i]);

            }


           console.log("tamaño de los radiesitos:"+ $scope.misradiesitos.length);

            var profesor = new Profesor({
                'password': $rootScope.passwordactual,
                'reactivos':[
                    {
                        'pregunta':$scope.pregunta,
                        'urlimagen':$rootScope.rutaimagen,
                        'tema':$scope.tema,
                        'retroalimentacion':$scope.retroalimentacion,
                        'opciones':[
                                  {
                                'titulo':$scope.titulo1,
                                 'acierto':valores[0]
                                   },
                            {
                                'titulo':$scope.titulo2,
                                'acierto':valores[1]
                            },
                            {
                                'titulo':$scope.titulo3,
                                'acierto':valores[2]
                            },
                            {
                                'titulo':$scope.titulo4,
                                'acierto':valores[3]
                            }
                        ]
                    }
                ]
            });
          //  profesor.password = $rootScope.passwordactual;




            profesor.$crear(function (data) {
                console.log(data.password);
                console.log(data.reactivos[0].pregunta);
                $rootScope.rutaimagen='';
            });






        };// Termina boton guardarUsuario()


    }).controller("guias",function($scope){

    }).controller('ctrlGuias',function($scope,$http){
        $scope.mensajeGuias='Bienvenido a las guias!!';
        console.log('holaaaaaaaaa');


    }).controller('profesores',function($scope,$http,$resource){
         console.log('EStas en el controlador de profesores');
        var Profesor=$resource('profesor/:id',{id:'@id'},{crear:{method:'POST'},
            actualizar:{method:'PUT'}, borrar:{method:'DELETE'}});

        //POST
        $scope.guardarProfesor=function() {

            console.log("antes del evento");
            var profesor = new Profesor();
            profesor.nombre = $scope.nombre;
            profesor.login=$scope.login;
            profesor.autoridad=$scope.autoridad;
            profesor.paterno = $scope.paterno;
            profesor.password =$scope.password;

            profesor.$crear(function (data) {
                console.log(data.nombre);
                $scope.nombre='';
                $scope.paterno='';
                $scope.password='';
            });

            //CARGAR IMAGEN DE REACTIVO







        }//termina boton guardar profesor


    }).controller('tema-reactivos',function($scope,$rootScope, $http, $resource){
        $scope.misreactivos='bienvenidos a los pequeñas examenes';
        $scope.retroalimentacion;
        $scope.evaluar='';
        $scope.habilitar='false';
        //Tenemos que definir asi a los radio buttons como json, si los declaras como una fdato escalar no funcionara
        $scope.radiesin = {
            name: 'blue'
        };
        $scope.validar=false;
  $scope.mostrarPregunta=function(){
      $scope.habilitar='';
      $scope.evaluar='';
      $scope.correcta='';
      $rootScope.mostrarboton=true;
   var i=$rootScope.indice;
      $scope.numeropregunta=i+1;
      $scope.numeropregunta='Pregunta no. '+$scope.numeropregunta;
     $scope.validar=false;
      $scope.titulo=$rootScope.reactivostema[i].pregunta;
      $scope.urlimagen=$rootScope.reactivostema[i].urlimagen;
      if($scope.urlimagen!=''){
          console.log('Tiene imagen');
          $rootScope.tieneimagen=true;
      }
      $scope.opciones=$rootScope.reactivostema[i].opciones;
      $scope.retroalimentacion=$rootScope.reactivostema[i].retroalimentacion;
      $rootScope.indice=$rootScope.indice+1;;
     if($rootScope.indice>=$rootScope.reactivostema.length)$rootScope.indice=0;

  }
        var evaluar=function(){

        }
        $scope.checarRespuesta=function(){
            $scope.validar=true;
            $scope.habilitar='true';
            for(var j=0;j<4;j++){
                if($scope.opciones[j].acierto===true){
                    $scope.correcta=$scope.opciones[j].titulo;
                }
            }
            console.log("Respuesta Correcta:"+$scope.correcta);
         console.log("SEleccionastessses"+   $scope.radiesin.name);
            if($scope.radiesin.name===$scope.correcta)$scope.evaluar="CORRECTO";
            else $scope.evaluar="MAL!!!"
            console.log($scope.evaluar);

        }

    });

