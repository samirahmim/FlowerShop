
package flowershop;


public class orderlist {
    private int no,productId;
    private String barcode,productName;
    private double price;
    private int qty;
    private double amount;

    public orderlist(int no, int productId, String barcode, String productName, double price, int qty, double amount) {
        this.no = no;
        this.productId = productId;
        this.barcode = barcode;
        this.productName = productName;
        this.price = price;
        this.qty = qty;
        this.amount = amount;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
}

