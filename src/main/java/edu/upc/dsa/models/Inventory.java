package edu.upc.dsa.models;

public class Inventory {
    int id;
    int item1 = 0;
    int item2 = 0;
    int item3 = 0;
    int item4 = 0;
    int item5 = 0;

    public Inventory() {
        this.id = 0;
    }

    public Inventory(int idUser) {
        this.id = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public int getItem1() {
        return item1;
    }

    public void setItem1(Integer item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(Integer item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(Integer item3) {
        this.item3 = item3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(Integer item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(Integer item5) {
        this.item5 = item5;
    }
}
