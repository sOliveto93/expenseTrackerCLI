package com.expensetracker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {
    private int id;
    Date date;
    String description;
    double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateString() {
         SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
         return targetFormat.format(date);
    
    }
    public Date getDate() {
        return date;
   
   }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
