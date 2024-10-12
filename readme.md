# Expense Tracker

App CLI simple. Reto propuesto por la plataforma [roadmap.sh](https://roadmap.sh/projects/expense-tracker)

## Features

- Creacion de gastos con descripcion y monto.
- Actualizacion de gastos existentes mediante su id.
- Borrado de gastos.
- Listar todos los gastos.
- Mostrar el gasto total de los gastos.
- Mostrar el gasto total de un mes.

### Extras (Optional)

- Export expenses to a CSV file.

## Como Instalarlo
- Clonar el proyecto
- asegurarse de tener java instalado
-asegurarse de tener un archivo en la ruta raiz ./resources/bd.json
```
[{"id":1,"date":"Jan 15, 2024, 12:00:00 AM","description":"Compra de supermercado","amount":150.75},{"id":3,"date":"Jan 17, 2024, 12:00:00 AM","description":"Factura de electricidad","amount":80.5},{"id":4,"date":"Jan 18, 2024, 12:00:00 AM","description":"Almuerzo con amigos","amount":45.25},{"id":5,"date":"Jan 19, 2024, 12:00:00 AM","description":"Compra de ropa","amount":120.0},{"id":6,"date":"Jan 20, 2024, 12:00:00 AM","description":"Cine","amount":30.0},{"id":7,"date":"Jan 21, 2024, 12:00:00 AM","description":"Suscripcion mensual a servicio de streaming","amount":12.99},{"id":8,"date":"Jan 22, 2024, 12:00:00 AM","description":"Mantenimiento del auto","amount":200.0}]
```

## Crear el .JAR
- cd raiz del proyecto
- ejecutar el comando mvn clean package
- cd target
- asegurarse que existe el archivo ./resources/bd.json en el mismo directorio del .JAR
- abrir con cmd o crear archivo batch para su automatizacion

## Comandos

Ejemplos

```bash
$ add --description "Lunch" --amount 20
# Gasto agregado con exito (ID: 1)

$ add --description "Dinner" --amount 10
# Gasto agregado con exito (ID: 2)

$ list
# ID  Date       Description  Amount
# 1   2024-08-06  Lunch        $20
# 2   2024-08-06  Dinner       $10

$ summary
# gasto total: $30

$ summary --month 8
# Gasto total del mes: $20

$ delete 1
# gasto eliminado con exito

$ update 1
# gasto modificado con exito

$ save
# actualiza el bd.json con la nueva informacion

$export
# exporta la informacion a un .CSV (formato aceptado por excel)
