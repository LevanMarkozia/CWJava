package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository){
        this.customerRepository=customerRepository;
        this.addressRepository=addressRepository;
    }
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    public Customer getById(Long id){
        return customerRepository.findById(id).orElse(null);
    }
    @Transactional
    public Customer addEditCustomer(AddCustomer addCustomer,Long id){
        Customer customer=new Customer();
        customer.setFirstName(addCustomer.getFirstName());
        customer.setLastName(addCustomer.getLastName());
        customer.setCreateDate(new Date());
        Address address=addressRepository.findOneByAddress(addCustomer.getAddress());
        if(address==null){
            Address newAddress = new Address();
            newAddress.setAddress(addCustomer.getAddress());
            address=addressRepository.save(newAddress);
        }
        customer.setAddress(address);
        return customerRepository.save(customer);
    }
}
