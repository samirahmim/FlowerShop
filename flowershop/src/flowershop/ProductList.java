/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowershop;

/**
 *
 * @author User
 */
public class ProductList {
   
    String barcode,name,bprice,sprice,qty,dateIn;
    int pid;

    public ProductList(String barcode, String name, String bprice, String sprice) {
        this.barcode = barcode;
        this.name = name;
        this.bprice = bprice;
        this.sprice = sprice;
    }
    public ProductList(int pid,String barcode, String name, String bprice, String sprice,String qty,String dateIn) {
        this.pid = pid;
        this.barcode = barcode;
        this.name = name;
        this.bprice = bprice;
        this.sprice = sprice;
        this.qty = qty;
        this.dateIn = dateIn;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBprice() {
        return bprice;
    }

    public void setBprice(String bprice) {
        this.bprice = bprice;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
    
}
