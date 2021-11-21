package com.charlene.coffee.corner.service;

import com.charlene.coffee.corner.model.Item;
import com.charlene.coffee.corner.model.Product;
import com.charlene.coffee.corner.model.Receipt;
import com.charlene.coffee.corner.util.ProductType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CoffeeShopServiceImpl implements CoffeeShopService {

    @Override
    public Receipt getCoffeeReceipt(List<Product> products) {
        // count beverages
        long countBeverage = products
                .stream()
                .filter(p-> ProductType.BEVERAGE.equals(p.getTypeProduct()))
                .count();
        // count snacks
        long countSnack = products
                .stream()
                .filter(p-> ProductType.SNACK.equals(p.getTypeProduct()))
                .count();

        var hasDiscount = countBeverage > 0 && countSnack > 0;

        if (hasDiscount) {
            // One extra is free
            var firstExtraProduct
                = products.stream().filter(product -> ProductType.EXTRA.equals(product.getTypeProduct())).findFirst();
            if (firstExtraProduct.isPresent()) {
                var product = firstExtraProduct.get();
                product.setDescription(product.getDescription() + " free ");
                product.setPrice(0.0);
            }
        }

        if (countBeverage > 5) {
            int beverageCount = 0;
            for (Product product: products) {
                if (ProductType.BEVERAGE.equals(product.getTypeProduct())) {
                    beverageCount++;
                }
                // Each 5th beverage is free
                if (beverageCount == 5) {
                    product.setPrice(0.0);
                    product.setDescription(product.getDescription() + " free");
                    beverageCount = 0;
                }
            }
        }
        Receipt receipt = new Receipt();
        receipt.setDate(new Date());

        var items = products.stream()
            .map(product -> new Item(UUID.randomUUID().toString(),
                                     product.getId(),
                                     product.getDescription(),
                                     product.getPrice(),
                                     product.getCurrency(),
                                     product.getTypeProduct()))
            .collect(Collectors.toList());

        receipt.setItems(items);

        //generate the id
        receipt.setId(UUID.randomUUID().toString());

        // calculate price
        if (!receipt.getItems().isEmpty()) {
            receipt.setTotal(receipt.getItems().stream().mapToDouble(product -> product.getPrice()).sum());
        } else {
            receipt.setTotal(0.00);
        }
        return receipt;
    }
}