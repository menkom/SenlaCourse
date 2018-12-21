package info.mastera.model;

import org.junit.Test;

public class WarehouseTest {

    @Test
    public void equals() {

        PartCategory partCategory = new PartCategory();
        partCategory.setId(42);
        partCategory.setName("partName");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(42);
        manufacturer.setName("manufacturerName");

        Product product = new Product();
        product.setId(1);
        product.setCode("code");
        product.setName("name");
        product.setManufacturer(manufacturer);

        Part part = new Part();
        part.setId(42);
        part.setName("name");
        part.setPartCategory(partCategory);
        part.setProduct(product);

        Warehouse[] warehouses = new Warehouse[2];

        for (Warehouse warehouse : warehouses) {
            warehouse = new Warehouse();
            warehouse.setId(4);
            warehouse.setCount(24D);
            warehouse.setPricePerOne(12D);
            warehouse.setCode("code");
            warehouse.setDescr("descr");
            warehouse.setPart(part);
        }

    }
}