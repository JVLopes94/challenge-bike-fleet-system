package jvlopes.bicycle.fleet.infrastructure.adapter;

import jvlopes.bicycle.fleet.application.dto.PageResponse;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.repository.BicycleRepository;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBicycleRepositoryAdapter implements BicycleRepository {

    @Override
    public Bicycle save(Bicycle bicycle) {
        return null;
    }

    @Override
    public PageResponse<Bicycle> findAll(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public PageResponse<Bicycle> findAllByStatus(BicycleStatus status, int page, int size) {
        return null;
    }

    @Override
    public Bicycle findByID(BicycleID bicycleID) {
        return null;
    }
}
