package com.charlene.coffee.corner.service;

import com.charlene.coffee.corner.model.Product;
import com.charlene.coffee.corner.model.Receipt;
import java.util.List;

public interface CoffeeShopService {

    Receipt getCoffeeReceipt(List<Product> products);
}
