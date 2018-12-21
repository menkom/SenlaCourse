package info.mastera.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void equals() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(42);
        manufacturer.setName("name");

        Product[] products = new Product[2];

        for (Product product : products) {
            product = new Product();
            product.setId(1);
            product.setCode("code");
            product.setName("name");
            product.setManufacturer(manufacturer);
        }
        Assert.assertEquals(products[0], products[1]);

    }
}