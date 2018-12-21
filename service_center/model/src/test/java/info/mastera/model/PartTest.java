package info.mastera.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartTest {

    @Test
    public void equals() {
        Part[] parts = new Part[2];

        PartCategory partCategory = new PartCategory();
        partCategory.setId(42);
        partCategory.setName("name");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(42);
        manufacturer.setName("name");

        Product product = new Product();
        product.setId(1);
        product.setCode("code");
        product.setName("name");
        product.setManufacturer(manufacturer);

        for (Part part : parts) {
            part = new Part();
            part.setId(42);
            part.setName("name");
            part.setPartCategory(partCategory);
            part.setProduct(product);
        }
        Assert.assertEquals(parts[0], parts[1]);
    }
}