package com.bhumil.thebellboy.Models;

public class Product
{
    int productId;
    String productName;
    Double productPrice;
    String productRating;
    int prodcutImage;
    int quantity;
    double total;

    public Product(int productId, String productName, Double productPrice, String productRating, int prodcutImage)
    {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productRating = productRating;
        this.prodcutImage = prodcutImage;
        this.quantity = 1;
        this.total = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductRating() {
        return productRating;
    }

    public void setProductRating(String productRating) {
        this.productRating = productRating;
    }

    public int getProdcutImage() {
        return prodcutImage;
    }

    public void setProdcutImage(int prodcutImage) {
        this.prodcutImage = prodcutImage;
    }

    public int getProdcutQty() {
        return quantity;
    }

    public void setProdcutQty(int productQty) {
        this.quantity = productQty;
    }
    public double getProductTotal() {
        return total;
    }

    public void setProductTotal(double productTotal) {
        this.total = productTotal;
    }
}
