package jvlopes.bicycle.fleet.infrastructure.controller;

import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.application.dto.PageResponse;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.BicycleDetailsDTO;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.CreateBicycleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController("/bicycles")
public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PostMapping
    public ResponseEntity<BicycleDetailsDTO> create(CreateBicycleDTO createBicycleDTO) {
        Bicycle bicycle = bicycleService.save(createBicycleDTO.toBicycle());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(URI.create("/bicycles/" + bicycle.getId()))
                .body(BicycleDetailsDTO.fromBicycle(bicycle));
    }

    @GetMapping
    public ResponseEntity<Page<BicycleDetailsDTO>> list(BicycleStatus status, int page, int size) {
        PageResponse<Bicycle> bicycles = (status == null)
                ? bicycleService.list(page, size)
                : bicycleService.listByStatus(status, page, size);
        return ResponseEntity.ok(new PageImpl<>(
                bicycles.getContent().stream().map(BicycleDetailsDTO::fromBicycle).toList(),
                PageRequest.of(page, size),
                bicycles.getTotalElements()
        ));
    }
}
