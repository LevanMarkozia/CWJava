package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    public Address findOneByAddress(String address);
}
