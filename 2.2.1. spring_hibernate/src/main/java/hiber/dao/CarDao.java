package hiber.dao;

import hiber.model.Car;
import org.springframework.stereotype.Repository;

public interface CarDao {
    void add(Car car);
}
