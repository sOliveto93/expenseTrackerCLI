package com.expensetracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDate;
import java.time.ZoneId;

public class App {
    Gson gson;
    List<Expense> expenses = null;

    public App() {
        gson = new Gson();
        String filePath = "resources/bd.json";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            Type listType = new TypeToken<List<Expense>>() {
            }.getType();
            setExpenses(gson.fromJson(br, listType));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String[] extractDescriptionAndAmount(String command) {
        // Expresión regular para capturar texto entre comillas y el monto después de
        // --mount
        Pattern pattern = Pattern.compile("\"([^\"]*)\".*--mount\\s*(\\S+)");
        Matcher matcher = pattern.matcher(command);

        if (matcher.find()) {
            String description = matcher.group(1); // Texto entre comillas
            String amount = matcher.group(2); // Valor después de --mount
            return new String[] { "add", "description", description, "--mount", amount };
        }

        return null;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void listAll() {
        for (Expense ex : expenses) {

            System.out.println(ex.getId());
            System.out.println(ex.getDescription());
            System.out.println(ex.getAmount());
            System.out.println(ex.getDate());
            System.out.println("-------------------------");
        }
    }

    public void filterAmount(double d) {
        boolean coincidencia = false;
        for (Expense e : expenses) {
            if (d == e.getAmount()) {
                System.out.println(e.getAmount());
                coincidencia = true;
            }
        }
        if (!coincidencia) {
            System.out.println("no se encontraron coincidencias");
        }
    }

    public double parseDouble(String s) {
        double value = 0.0;
        try {
            value = Double.parseDouble(s);
        } catch (Exception e) {
            System.out.println("valor incorrecto");
        }
        return value;
    }

    public void summary() {
        double sum = 0;
        for (Expense e : expenses) {
            sum += e.getAmount();
        }
        System.out.println("Total expenses: $" + sum);
    }

    public void SummaryMonth(int m) {
        double sum = 0;
        for (Expense e : expenses) {
            Date date = e.getDate();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue(); // Mes del 1 al 12

            if (month == m) {
                sum += e.getAmount();
            }
        }
        System.out.println("Total expenses for month " + m + ": $" + sum);
    }

    public void addExpense(String[] ops) {
        if (ops.length == 5) {
            String description = ops[2];
            double value = parseDouble(ops[4]);
            if (value > 0 && !description.equals("")) {
                Expense e = new Expense();
                e.setAmount(value);
                e.setDescription(description);
                e.setId(createId());
                e.setDate(new Date());
                expenses.add(e);
                System.out.println("agregado con exito");
            } else {
                System.out.println("mount debe ser un numero valido y la descripcion tener contenido valido");
            }

        } else {
            System.out.println("a saber que te falta, pero algo te falta");
        }
    }

    public void deleteById(String[] ops) {
        boolean encontrado = false;
        try {
            int id = Integer.parseInt(ops[1].trim());
            if (id <= 0) {
                System.out.println("id invalido");
                return;
            }
            for (int i = 0; i < expenses.size(); i++) {
                Expense e = expenses.get(i);
                if (e.getId() == id) {
                    expenses.remove(i);
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                System.out.println("eliminado con exito");
            } else {
                System.out.println("id no existe");
            }

        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Debe ser un número.");
            return;
        }

    }

    public int createId() {
        int id = 0;
        for (Expense e : expenses) {
            int current = e.getId();
            if (current > id) {
                id = current;
            }
        }
        return id + 1;
    }

    public void update(String[] ops, Scanner sc) {
        boolean encontrado = false;
        try {
            int id = Integer.parseInt(ops[1].trim());
            if (id <= 0) {
                System.out.println("id invalido");
                return;
            }
            for (int i = 0; i < expenses.size(); i++) {
                Expense e = expenses.get(i);
                if (e.getId() == id) {
                    System.out.println("update mount");
                    String mount = sc.nextLine();
                    double value = parseDouble(mount);
                    System.out.println("update description");
                    String description = sc.nextLine();
                    if (value != 0.0 && value > 0) {
                        expenses.get(i).setAmount(value);
                    } else {
                        System.out.println("expense tiene que ser mayor a 0.0(cero)");
                        return;
                    }
                    if (!description.equals("")) {
                        expenses.get(i).setDescription(description);
                    } else {
                        System.out.println("descripcion vacia");
                        return;
                    }
                    encontrado = true;

                }
            }
            if (encontrado) {
                System.out.println("modificado con exito");
            } else {
                System.out.println("id no existe");
            }

        } catch (NumberFormatException e) {
            System.out.println("ID no válido. Debe ser un número.");
            return;
        }
    }

    public void updateJson(String name) {
        String filePath = "resources/"+ name;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String json = gson.toJson(expenses);
            writer.write(json);
            System.out.println("JSON actualizado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        App app = new App();
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        do {
            System.out.println("list || add description \"texto\"--mount 821.2 || delete id || update id || filter mount || exit || summary --month 8 || save || export ");
                    
            String op = sc.nextLine();
            
            if (op.startsWith("add")) {
                // Extraer descripción y monto
                String[] ops = app.extractDescriptionAndAmount(op);
                if (ops != null) {
                    app.addExpense(ops);
                } else {
                    System.out.println("Entrada inválida. Asegúrate de usar el formato correcto.");
                }

            }
            if (op.startsWith("delete")) {
                String[] ops = op.split(" ");
                app.deleteById(ops);
            } else if (op.equals("list")) {
                app.listAll();
            } else if (op.equals("exit")) {
                System.out.println("GOOD BYE!...");
                exit = true;
            } else if (op.startsWith("filter")) {
                try {
                    String[] ops = op.split(" ");
                    if (ops.length > 1) {
                        double value = app.parseDouble(ops[1]);
                        if (value != 0.0 && value > 0.0) {
                            app.filterAmount(value);
                        } else {
                            System.out.println("valor invalido");
                        }

                    } else {
                        System.out.println("sos chistoso. como queres que filtre?");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (op.startsWith("summary")) {
                try {
                    String[] ops = op.split(" ");
                    if (ops.length > 2) {
                        int month = Integer.parseInt(ops[2]);
                        app.SummaryMonth(month);
                    } else {
                        System.out.println("te faltan parametros ej--> summary --month 8");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (op.startsWith("update")) {
                String[] ops = op.split(" ");
                app.update(ops, sc);
            } else if (op.equals("export")) {
                ExportToCSV eCSV = new ExportToCSV(app.getExpenses());
                eCSV.export("pruebacsv.csv");
            } else if (op.equals("save")) {
                app.updateJson("bd.json");
            } else {
                System.out.println("COMMAND NOT FOUND!");
            }

        } while (!exit);
        sc.close();

    }
}
