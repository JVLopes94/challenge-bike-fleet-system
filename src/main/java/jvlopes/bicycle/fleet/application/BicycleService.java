package jvlopes.bicycle.fleet.application;

import jvlopes.bicycle.fleet.application.dto.PageResponse;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.exception.BicycleNotFoundException;
import jvlopes.bicycle.fleet.domain.exception.InvalidBicycleIdException;
import jvlopes.bicycle.fleet.domain.repository.BicycleRepository;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import org.springframework.stereotype.Service;

@Service
public class BicycleService {

    private final BicycleRepository repo;

    public BicycleService(BicycleRepository repo) {
        this.repo = repo;
    }

    public Bicycle save(Bicycle bicycle) {
        return repo.save(bicycle);
    }

    public PageResponse<Bicycle> list(int page, int size) {
        return repo.findAll(page, size);
    }

    public PageResponse<Bicycle> listByStatus(BicycleStatus status, int page, int size) {
        return repo.findAllByStatus(status, page, size);
    }

    public Bicycle getByID(String bicycleID) throws InvalidBicycleIdException, BicycleNotFoundException {
        Bicycle bicycle = repo.findByID(new BicycleID(bicycleID));
        if (bicycle == null) throw new BicycleNotFoundException();
        return bicycle;
    }
}
