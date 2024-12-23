package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddAddress;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.repositories.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Transactional
    public Address addEditAddress(AddAddress addAddress,Long id){
        Address address=new Address();
        address.setAddress(addAddress.getAddress());
//        address.setCity(addAddress.getCity());
        City city=cityRepository.findOneByCity(addAddress.getCity());
        if(city==null){
            City newCity = new City();
            newCity.setCity(addAddress.getCity());
            city=cityRepository.save(newCity);
        }
        address.setCity(city);
        return addressRepository.save(address);
    }
}
