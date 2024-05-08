# AyudaAlkeWalletM5
Proyecto de ayuda y guía para resolver la evaluación M5

## Refuerzo Conocimientos
### Backend
![modelo comunicación cliente y servidor](img/cliente_servidor1.png)<br>
La comunicación entre el lado del cliente (página) y el servidor (backend) se hace mediante el protocolo HTTP, que significa transferencia de hipertexto. El cliente manda una request al servidor y este le manda una response.
![patron de diseño mvc](img/6ffbd6cd-64f6-4508-801f-7aa1edfaa6b1.png)<br>
El patrón de diseño MVC permite separar en capas la lógica de nuestra aplicación, teniendo:
* **Modelo:** Aquí se encuentran nuestras clases y objetos con los que estaremos trabajando (ej: usuario, empleado, perro) con sus determinados constructores y getters y setters.
* **Vista:** Todo lo que mostramos visualmente al cliente, estaremos usando JSP para las vistas, los cuales nos permiten mezclar código html y código java.
* **Controlador:** Contiene toda la lógica del programa, aquí estarán nuestros servlets quienes manejaran las solicitudes HTTP (ej: GET, POST).

El patrón de diseño DAO nos permite separar la lógica de nuestra aplicación de la lógica de la base de datos y es llamado en el controlador (servlets). Se crea un package en el mismo nivel que modelo, vista, controlador con el nombre de dao y usualmente van dos archivos por nuestras entidades (ej: entidad usuario -> interfaz usuarioDAO, clase usuarioDAOImpl). Aquí van a ir todas las querys que debamos realizar en la base de datos.
