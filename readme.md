# Expense Tracker

App CLI simple. Reto propuesto por la plataforma [roadmap.sh](#roadmap.sh)


## Features

- Creacion de gastos con descripcion y monto.
- Actualizacion de gastos existentes mediante su id.
- Borrado de gastos.
- Listar todos los gastos.
- Mostrar el gasto total de los gastos.
- Mostrar el gasto total de un mes.

### Additional Features (Optional)

- Export expenses to a CSV file.

## Commands

Here are some example commands and their expected output:

```bash
$ add --description "Lunch" --amount 20
# Expense added successfully (ID: 1)

$ add --description "Dinner" --amount 10
# Expense added successfully (ID: 2)

$ list
# ID  Date       Description  Amount
# 1   2024-08-06  Lunch        $20
# 2   2024-08-06  Dinner       $10

$ summary
# Total expenses: $30

$delete --id 1
# Expense deleted successfully

$ summary
# Total expenses: $20

$ summary --month 8
# Total expenses for August: $20
