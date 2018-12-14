package info.mastera.beans;

import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import info.mastera.service.IGenericService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
public class CustomerListBean extends BaseListBean<Customer> {

    @Inject
    private ICustomerService<Customer> customerService;

    @Override
    protected IGenericService<Customer> getService() {
        return customerService;
    }
}
