package com.canytech.todolist_canylist.database;

public class MyDoes {

    String titleDoes;
    String descDoes;
    String dateDoes;

    public MyDoes() {
    }

    public MyDoes(String titleDoes, String descDoes, String dateDoes) {
        this.titleDoes = titleDoes;
        this.descDoes = descDoes;
        this.dateDoes = dateDoes;
    }

    public String getTitleDoes() {
        return titleDoes;
    }

    public void setTitleDoes(String titleDoes) {
        this.titleDoes = titleDoes;
    }

    public String getDescDoes() {
        return descDoes;
    }

    public void setDescDoes(String descDoes) {
        this.descDoes = descDoes;
    }

    public String getDateDoes() {
        return dateDoes;
    }

    public void setDateDoes(String dateDoes) {
        this.dateDoes = dateDoes;
    }
}
