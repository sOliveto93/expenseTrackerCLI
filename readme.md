# Expense Tracker

App CLI simple. Reto propuesto por la plataforma [roadmap.sh](#roadmap.sh)

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Commands](#commands)
- [Implementation](#implementation)
- [Installation](#installation)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Expense Tracker is a command-line application that allows users to manage their expenses efficiently. Users can add, update, delete, and view their expenses, along with a summary of their financial activity.

## Features

- Add an expense with a description and amount.
- Update an existing expense.
- Delete an expense.
- View all expenses.
- View a summary of total expenses.
- View a summary of expenses for a specific month.

### Additional Features (Optional)

- Add expense categories and filter expenses by category.
- Set a monthly budget and receive warnings when exceeding it.
- Export expenses to a CSV file.

## Requirements

The application should run from the command line and support the following features:

- Users can add an expense with a description and amount.
- Users can update an expense.
- Users can delete an expense.
- Users can view all expenses.
- Users can view a summary of all expenses.
- Users can view a summary of expenses for a specific month (of the current year).

## Commands

Here are some example commands and their expected output:

```bash
$ expense-tracker add --description "Lunch" --amount 20
# Expense added successfully (ID: 1)

$ expense-tracker add --description "Dinner" --amount 10
# Expense added successfully (ID: 2)

$ expense-tracker list
# ID  Date       Description  Amount
# 1   2024-08-06  Lunch        $20
# 2   2024-08-06  Dinner       $10

$ expense-tracker summary
# Total expenses: $30

$ expense-tracker delete --id 1
# Expense deleted successfully

$ expense-tracker summary
# Total expenses: $20

$ expense-tracker summary --month 8
# Total expenses for August: $20
