package info.mastera.service.impl;

import info.mastera.model.Part;
import info.mastera.service.IPartService;
import info.mastera.service.impl.config.TestConfig;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PartServiceTest {

    @Autowired
    IPartService partService;

    @Test(expected = LazyInitializationException.class)
    public void getByIdWithPartCategoryAndProduct() {
        Part newPart = partService.getByIdWithPartCategoryAndProduct(2);

        System.out.println("newPart.getId():" + newPart.getId());
        System.out.println("newPart.prod.getId():" + newPart.getProduct().getId());
        System.out.println("newPart.prod.manuf.getId():" + newPart.getProduct().getManufacturer().getId());
        System.out.println("newPart.prod.manuf.getName():" + newPart.getProduct().getManufacturer().getName());

    }

    @Test(expected = LazyInitializationException.class)
    public void getById() {
        Part newPart = partService.getById(3);

        System.out.println("newPart.getId():" + newPart.getId());
        System.out.println("newPart.prod.getId():" + newPart.getProduct().getId());
        System.out.println("newPart.prod.getCode():" + newPart.getProduct().getCode());
        System.out.println("newPart.prod.manuf.getId():" + newPart.getProduct().getManufacturer().getId());
        System.out.println("newPart.prod.manuf.getName():" + newPart.getProduct().getManufacturer().getName());
    }
}