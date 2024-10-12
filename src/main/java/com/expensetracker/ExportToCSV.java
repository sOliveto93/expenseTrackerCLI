package com.expensetracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportToCSV {
    List<Expense> expenses;

    public ExportToCSV(List<Expense> expenses) {
        this.expenses=expenses;
    }

    public void export(String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("Id,Description,Amount,Date");
            writer.newLine();
            for (Expense e : expenses) {
                writer.write(
                    e.getId()+","+
                    e.getDescription()+","+
                    e.getAmount()+","+
                    e.getDate()
                );
                writer.newLine();
               
            }
            System.out.println("Datos exportas a CSV con exito.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
