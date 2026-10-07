# Entrevista

Aplicación Android nativa en Java y XML. Android mínimo: 6.0 (API 23).

## Ejecutar
1. En Android Studio abre esta carpeta y espera la sincronización de Gradle.
2. Conecta el Realme 7 por USB, activa Depuración USB y acepta la autorización del equipo.
3. Selecciona el teléfono en la barra superior y pulsa Run (triángulo verde).
4. Si el dispositivo no aparece, usa Tools > Troubleshoot Device Connections.

## Flujo implementado
- Información: celular obligatorio de 1 a 8 dígitos; carnet obligatorio de 1 a 10 dígitos; complemento opcional de 0 a 2 caracteres ASCII alfanuméricos.
- Antes del punto de conexión se presenta una explicación y el diálogo oficial de Android para permitir ubicación precisa o aproximada. Si Android bloquea la solicitud después de varios rechazos, se ofrece una vía a los ajustes de la aplicación.
- La aplicación no lee la ubicación del dispositivo en esta demostración; solo valida el permiso antes de continuar.
- Autenticación: recomendaciones de iluminación basadas en la captura. Ilustración vectorial provisional.
- Siguiente en autenticación informa que la verificación de identidad aún no está disponible.

## Servicio pendiente
DemoService.submitInformation es una demostración local documentada en el código: no hace peticiones, no obtiene coordenadas y no realiza verificación de identidad.
Para integrarlo hacen falta URL, método HTTP, formato de solicitud/respuesta, errores, autenticación y requisitos de ubicación. La verificación facial requiere además especificaciones del proveedor y sus permisos. No hay credenciales reales ni tokens guardados.

## Archivos principales
- MainActivity.java y activity_main.xml: información, validación y permisos.
- AuthenticationActivity.java y activity_authentication.xml: recomendaciones y fin de demostración.
- InputValidator.java: reglas de entrada.
- DemoService.java: punto de conexión pendiente.
- AndroidManifest.xml: permiso y registro de actividades.

## Pruebas
Compilar y verificar desde PowerShell en esta carpeta:

    $env:JAVA_HOME = 'C:\Program Files\Android\Android Studio\jbr'
    .\gradlew.bat :app:assembleDebug :app:testDebugUnitTest :app:lintDebug

Prueba manual en el celular:
1. Pulsa Siguiente con los campos vacíos: deben aparecer errores.
2. Pega letras en celular/carnet y símbolos en complemento: se deben filtrar.
3. Verifica que no acepte más de 8, 10 y 2 caracteres respectivamente.
4. Usa 71234567, 412345 y complemento vacío o 1D.
5. Rechaza el permiso: no debe continuar ni llamar al punto de conexión.
6. Permite ubicación: debe avanzar a la pantalla de autenticación.
7. Activa ubicación y pulsa Siguiente: debe abrir Autenticación.
8. Pulsa Siguiente: debe indicar que la verificación aún no está disponible.
9. Prueba con el teclado abierto, gira el celular y vuelve desde Autenticación.

APK de depuración: app/build/outputs/apk/debug/app-debug.apk después de una compilación exitosa.


