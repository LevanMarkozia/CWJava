package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddAddress;
import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Address getById(@PathVariable Long id) {
        return addressService.getById(id);
    }

    @RequestMapping(value="/add",method = RequestMethod.POST,produces = {"application/json"})
    public Address add(@RequestBody AddAddress addAddress){
        return addressService.addEditAddress(addAddress,null);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT,produces = {"application/json"})
    public Address edit(@RequestBody AddAddress addAddress,@PathVariable Long id){
        return addressService.addEditAddress(addAddress,id);
    }
}
