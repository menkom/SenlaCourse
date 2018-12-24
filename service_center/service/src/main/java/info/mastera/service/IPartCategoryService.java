package info.mastera.service;

import info.mastera.model.PartCategory;

import java.util.List;

public interface IPartCategoryService {

    PartCategory create(PartCategory entity);

    void delete(PartCategory entity);

    void update(PartCategory entity);

    PartCategory getById(int id);

    List<PartCategory> getAll();

    Long count();


}
