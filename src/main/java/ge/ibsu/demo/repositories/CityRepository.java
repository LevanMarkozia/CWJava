package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    public City findOneByCity(String city);
}
