package info.mastera.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PartCategoryTest {

    @Test
    public void equals() {
        PartCategory[] partCategories = new PartCategory[2];

        for (PartCategory partCategory : partCategories) {
            partCategory = new PartCategory();
            partCategory.setId(42);
            partCategory.setName("name");
        }
        Assert.assertEquals(partCategories[0], partCategories[1]);

    }
}