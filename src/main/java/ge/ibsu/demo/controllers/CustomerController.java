package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.dto.Paging;
import ge.ibsu.demo.dto.RequestData;
import ge.ibsu.demo.dto.SearchCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.CustomerService;
import ge.ibsu.demo.util.GeneralUtil;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
    @RequestMapping(value="/all",method=RequestMethod.GET,produces={"application/json"})
    public List<Customer> getAll(){
        return customerService.getAll();
    }
    @RequestMapping(value="/{id}",method=RequestMethod.GET,produces={"application/json"})
    public Customer getById(@PathVariable Long id){
        return customerService.getById(id);
    }
    @RequestMapping(value="/add",method = RequestMethod.POST,produces = {"application/json"})
    public Customer add(@RequestBody AddCustomer addCustomer) throws Exception {
        return customerService.addEditCustomer(addCustomer,null);
    }
//    @RequestMapping(value="/{id}",method = RequestMethod.PUT,produces = {"application/json"})
//    public Customer edit(@RequestBody AddCustomer addCustomer,@PathVariable Long id){
//        return customerService.addEditCustomer(addCustomer,id);
//    }
    @RequestMapping(value="/{id}",method = RequestMethod.PUT,produces = {"application/json"})
    public Customer edit(@RequestBody AddCustomer addCustomer,@PathVariable Long id) throws Exception {
        GeneralUtil.checkRequiredProperties(addCustomer, Arrays.asList("firstName","lastName"));
        return customerService.addEditCustomer(addCustomer,id);
    }
    @RequestMapping(value="/search",method = RequestMethod.POST,produces = {"application/json"})
    public Page<Customer> search(@RequestBody RequestData<SearchCustomer> rd){
        return customerService.search(rd.getData(),rd.getPaging());
    }

    @RequestMapping(value = "/searchNative",method = RequestMethod.POST,produces = {"application/json"})
    public Page<Customer> searchNative(@RequestBody RequestData<SearchCustomer> rd){
        return customerService.searchNative(rd.getData(),rd.getPaging());
    }
}
