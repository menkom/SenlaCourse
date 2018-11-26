package info.mastera.service;

import java.util.List;

import org.springframework.stereotype.Component;

import info.mastera.model.Customer;

@Component
public interface ICustomerService<T extends Customer> extends IGenericService<T> {

}
