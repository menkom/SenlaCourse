package info.mastera.dao.impl;

import info.mastera.dao.IPartCategoryDao;
import info.mastera.model.PartCategory;
import org.springframework.stereotype.Repository;

@Repository
public class PartCategoryDao extends AbstractDao<PartCategory> implements IPartCategoryDao<PartCategory> {

    @Override
    protected Class<PartCategory> getTClass() {
        return PartCategory.class;
    }
}
