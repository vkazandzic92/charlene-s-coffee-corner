package com.charlene.coffee.corner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.charlene.coffee.corner.model.Product;
import com.charlene.coffee.corner.model.Receipt;
import com.charlene.coffee.corner.service.CoffeeShopService;
import com.charlene.coffee.corner.util.Currency;
import com.charlene.coffee.corner.util.ProductType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoffeeCornerApplicationTests {

	@Autowired
	CoffeeShopService coffeeShopService;

	@DisplayName("Test customer receipt")
	@Test
	void getReceipt() {
		List<Product> products = new ArrayList<>();
		products.add(new Product("1","Large coffee", 3.50, Currency.CHF, ProductType.BEVERAGE));
		products.add(new Product("2", "Extra milk", 0.30,Currency.CHF,ProductType.EXTRA));
		products.add(new Product("3", "Special roast", 0.90,Currency.CHF,ProductType.EXTRA));
		products.add(new Product("4","Bacon Roll", 4.50,Currency.CHF,ProductType.SNACK));
		products.add(new Product("5", "Orange juice", 3.95,Currency.CHF,ProductType.EXTRA));
		products.add(new Product("6","Small coffee", 3.50, Currency.CHF, ProductType.BEVERAGE));
		products.add(new Product("7","Medium coffee", 3.50, Currency.CHF, ProductType.BEVERAGE));
		products.add(new Product("8","Large coffee", 3.50, Currency.CHF, ProductType.BEVERAGE));
		products.add(new Product("9","Small coffee", 3.50, Currency.CHF, ProductType.BEVERAGE));
		products.add(new Product("10","Small coffee", 3.50, Currency.CHF, ProductType.BEVERAGE));

		Receipt receipt = coffeeShopService.getCoffeeReceipt(products);

		System.out.println(receipt);
		assertNotNull(receipt);
		// We have 2 discounts one for EXTRA (0.30) and each 5th BEVERAGE is free
		assertEquals(26.85, receipt.getItems().stream().mapToDouble(product -> product.getPrice()).sum());
	}

}
