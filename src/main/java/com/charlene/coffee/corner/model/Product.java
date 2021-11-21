package com.charlene.coffee.corner.model;

import com.charlene.coffee.corner.util.Currency;
import com.charlene.coffee.corner.util.ProductType;

public class Product {
    String id;
    String description;
    Double price;
    Currency currency;
    ProductType typeProduct;

    public Product(final String id,
                   final String description,
                   final Double price,
                   final Currency currency,
                   final ProductType typeProduct) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.typeProduct = typeProduct;
    }

    public String getId() {
        return id;
    }
    public void setId(final String id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(final Double price) {
        this.price = price;
    }
    public Currency getCurrency() {
        return currency;
    }
    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }
    public ProductType getTypeProduct() {
        return typeProduct;
    }
    public void setTypeProduct(final ProductType typeProduct) {
        this.typeProduct = typeProduct;
    }
}