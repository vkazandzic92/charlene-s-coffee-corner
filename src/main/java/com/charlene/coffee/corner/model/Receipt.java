package com.charlene.coffee.corner.model;

import com.charlene.coffee.corner.util.Currency;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receipt {

    String id;
    Date date = new Date();
    List<Item> items = new ArrayList<>();
    Double total;

    public String getId() {
        return id;
    }
    public void setId(final String id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(final Date date) {
        this.date = date;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(final List<Item> items) {
        this.items = items;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(final Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id Receipt: ").append(id).append("\n");
        sb.append("Date      : ").append(date).append("\n");

        items.forEach(item -> {
            sb.append(item.toString());
        });

        sb.append("Total     : ").append(String.format("%.2f", total)).append(" ").append(Currency.CHF.name()).append("\n");
        return sb.toString();
    }
}
