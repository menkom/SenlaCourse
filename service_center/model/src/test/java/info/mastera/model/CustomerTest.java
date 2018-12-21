package info.mastera.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void equals() {
        Customer[] customers = new Customer[3];

        for (Customer customer : customers) {
            customer = new Customer();
            customer.setId(2);
            customer.setCustomerName("custname");
            customer.setTelephone("tel123");
        }
        Assert.assertEquals(customers[0], customers[1]);
        Assert.assertEquals(customers[1], customers[0]);
        Assert.assertEquals(customers[1], customers[2]);
        Assert.assertEquals(customers[0], customers[2]);
    }
}