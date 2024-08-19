## 3. Interfaz de Usuario

### 3.1. Pantalla Principal

Al iniciar el programa, se presentara una pantalla principal con varias opciones.

![Pantalla principal](gestorcc/src/img/inicio.png)

- **Barra de Menú**: Accede a todas las funcionalidades del programa directamente.

## 4. Gestiones

Antes de iniciar cualquier gestion se recomienda elegir el la carpeta destino donde se guardaran todos los reportes, de lo contrario se guardaran en la carpeta en donde esta el programa.

### 4.1. Archivo de entrada

Permite cargar un archivo de entrada con las instrucciones.
La sintaxis debe ser la siguiente:

SOLICITUD(NUMEROSOLICITUD, FECHA, TIPO, NOMBRE, SALARIO, DIRECCION);
MOVIMIENTO(NUMERO, FECHA, TIPOMOVIMIENTO, DESCRIPCION, ESTABLECIMIENTO, MONTO);
CONSULTAR_TARJETA(NUMERO);
AUTORIZACION_TARJETA(NUMEROSOLICITUD);
CANCELACION_TARJETA(NUMERO);
ESTADO_CUENTA();
LISTADO_TARJETAS();
LISTADO_SOLICITUDES();

Reglas:
Las instrucciones se deben apilar una sobre otra en un archivo de texto.
Las fechas deben tener un formato dd/MM/yyyy.
El tipo de tarjeta solo puede ser NACIONAL, REGIONAL O INTERNACIONAL.
Los salarios y montos pueden tener hasta dos decimales maximo.
El tipo de movimiento puede ser unicamente CARGO o ABONO.

![Archivo de entrada](gestorcc/src/img/cargar.png)


### 4.2. Solicitudes

![Archivo de entrada](gestorcc/src/img/solicitud.png)

### 4.2. Movimientos

![Archivo de entrada](gestorcc/src/img/movimiento.png)

### 4.2. Consultas

![Archivo de entrada](gestorcc/src/img/consulta.png)

### 4.2. Autorizaciones

![Archivo de entrada](gestorcc/src/img/autorizar.png)

### 4.2. Cancelaciones

![Archivo de entrada](gestorcc/src/img/cancelar.png)

### 4.2. Estado de cuenta

![Archivo de entrada](gestorcc/src/img/estado.png)

### 4.2. Listado de tarjetas

![Archivo de entrada](gestorcc/src/img/tarjetas.png)

### 4.2. Listado solicitudes

![Archivo de entrada](gestorcc/src/img/solicitudes.png)

