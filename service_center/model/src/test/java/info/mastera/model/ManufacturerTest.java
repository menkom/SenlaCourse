package info.mastera.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManufacturerTest {

    @Test
    public void equals() {
        Manufacturer[] manufacturers = new Manufacturer[2];

        for (Manufacturer manufacturer : manufacturers) {
            manufacturer = new Manufacturer();
            manufacturer.setId(42);
            manufacturer.setName("name");
        }
        Assert.assertEquals(manufacturers[0], manufacturers[1]);
    }
}