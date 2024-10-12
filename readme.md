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

