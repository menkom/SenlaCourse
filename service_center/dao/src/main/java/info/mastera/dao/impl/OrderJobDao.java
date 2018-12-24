package info.mastera.dao.impl;

import info.mastera.dao.IOrderJobDao;
import info.mastera.model.OrderJob;
import org.springframework.stereotype.Repository;

@Repository
public class OrderJobDao extends AbstractDao<OrderJob> implements IOrderJobDao<OrderJob> {

    @Override
    protected Class<OrderJob> getTClass() {
        return OrderJob.class;
    }

}
