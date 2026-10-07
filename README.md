# Entrevista
Nombre: Fabricio Medina Viscarra


1. Una Activity es un componente de Android que proporciona un punto de entrada a la interfaz con la que interactúa el usuario. Gestiona su ciclo de vida y puede alojar vistas XML, fragments o contenido hecho con Jetpack Compose
2. Un Fragment representa una sección modular de la interfaz dentro de una Activity. Tiene su propio ciclo de vida y permite organizar pantallas, navegación y componentes reutilizables
3. C) OnCreate(). Se llama cuando se crea la Activity; después vienen onStart() y onResume()
4. B) RecyclerView. Está pensado para mostrar listas y reutilizar las vistas al desplazarse.
5. El Adapter enlaza la colección de datos con el RecyclerView. Crea los ViewHolder y asigna a cada uno los datos que debe mostrar. Si cambian los elementos de la lista, el adapter permite reflejar esos cambios en la interfaz
6. El ViewModel mantiene el estado y la lógica de presentación de la pantalla. Recibe las acciones del usuario, obtiene los datos mediante un repositorio y expone a la interfaz estados como carga, contenido o error. Esto evita concentrar toda la lógica en la Activity o el Fragment
7. El LiveData es un contenedor de datos observable y consciente del ciclo de vida. La interfaz puede observarlo para actualizarse cuando cambian los datos, respetando el estado activo de la Activity o el Fragment. En proyectos Kotlin modernos también puedo exponer el estado mediante StateFlow
8. ViewBinding genera una clase con referencias tipadas a las vistas de cada layout XML. Así puedo acceder a ellas sin llamar repetidamente a findViewById, y varios errores de identificador o tipo se detectan al compilar
9. En mis proyectos Android he utilizado diferentes bibliotecas para consumir APIs REST. En uno usé “Ktor Client”, hacía las peticiones HTTP desde la capa de datos y procesaba las respuestas JSON para mostrarlas en la aplicación. En otro proyecto puede utilizar Retrofit, desempeña actividades similares, pero permite definir los endpoints mediante interfaces y anotaciones como @GET y @POST. Con Ktor configuraba el cliente y las peticiones de forma más directa. Para elegir entre ambos, tendría en cuenta la arquitectura y las dependencias que ya utiliza el proyecto.
10. Retrofit es una biblioteca cliente HTTP. Defino en una interfaz las operaciones de la API, como GET o POST, y sus parámetros. Después puedo combinarla con un convertidor para transformar el JSON recibido en objetos de Kotlin y llamar a esos métodos desde la capa de datos
11.  B) JSON. Se utiliza normalmente en APIs REST.
