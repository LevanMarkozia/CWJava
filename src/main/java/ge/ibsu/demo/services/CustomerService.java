package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.Paging;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.repositories.CustomerRepository;
import ge.ibsu.demo.util.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Customer addEditCustomer(AddCustomer addCustomer,Long id) throws Exception {
        Customer customer=new Customer();
//        customer.setFirstName(addCustomer.getFirstName());
//        customer.setLastName(addCustomer.getLastName());
        GeneralUtil.getCopyOf(addCustomer,customer);
        customer.setCreateDate(new Date());
        Address address=addressRepository.findOneByAddress(addCustomer.getAddress());
        if(address==null){
            Address newAddress = new Address();
            newAddress.setAddress(addCustomer.getAddress());
            newAddress.setDistrict("Unidentified");
            address=addressRepository.save(newAddress);
        }
        customer.setAddress(address);
        return customerRepository.save(customer);
    }
    public Page<Customer> search(SearchCustomer searchCustomer,Paging paging){
        Pageable pageable= PageRequest.of(paging.getPage()-1, paging.getSize(), Sort.by("id").ascending());
        String searchText="%" + searchCustomer.getSearchText() + "%";
        return customerRepository.searchCustomers(searchText, pageable);
    }
    public Page<Customer> searchNative(SearchCustomer searchCustomer,Paging paging){
        Pageable pageable= PageRequest.of(paging.getPage()-1, paging.getSize(), Sort.by("customer_id").ascending());
        String searchText="%" + searchCustomer.getSearchText() + "%";
        return customerRepository.searchCustomerViaNativeQuery(searchText,pageable);
    }
}
