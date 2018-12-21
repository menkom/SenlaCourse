package info.mastera.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderJobTest {

    @Test
    public void equals() {
        OrderJob[] orderJobs = new OrderJob[2];

        for (OrderJob orderJob : orderJobs) {
            orderJob = new OrderJob();
            orderJob.setId(2);
            orderJob.setCode("code");
            orderJob.setName("name");
            orderJob.setPrice(1234.2);
        }
        Assert.assertEquals(orderJobs[0], orderJobs[1]);
    }
}