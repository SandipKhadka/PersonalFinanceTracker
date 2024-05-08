package org.app.model;

public class UserTransaction {
    int income, expanses;
    String catagory;

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpanses() {
        return expanses;
    }

    public void setExpanses(int expanses) {
        this.expanses = expanses;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

}
