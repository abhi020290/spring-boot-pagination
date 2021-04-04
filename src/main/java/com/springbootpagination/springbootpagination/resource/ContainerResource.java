package com.springbootpagination.springbootpagination.resource;

import com.springbootpagination.springbootpagination.entity.Container;
import com.springbootpagination.springbootpagination.respository.ContainerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/container")
@Slf4j
public class ContainerResource {

    private static final int numberOfElementsPerPage = 5;

    @Autowired
    private ContainerRepository containerRepository;

    @GetMapping("/load")
    public ResponseEntity<String> load() {
        containerRepository.saveAll(buildContainer());
        return new ResponseEntity<>("Data is loaded", HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Container>> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC,"toLocation");
        List<Container> list = containerRepository.findAll(sort);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<Container>> containers(@RequestParam("pageNo") Optional<String> pageNo,
                                                       @RequestParam("pageNo") Optional<String> sortBy,
                                                      @RequestParam("orderBy") Optional<String> orderBy) {
        try {
            int page = Integer.parseInt(pageNo.orElse("0"));
            String sortByAttribute = sortBy.orElse("containerId");
            Sort.Direction order = Sort.Direction.fromString(orderBy.orElse("asc"));
            Pageable pageable =  PageRequest.of(page, numberOfElementsPerPage, order, sortByAttribute);
            Page<Container> containerPage = containerRepository.findAll(pageable);
            return new ResponseEntity<>(containerPage, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    private List<Container> buildContainer() {
        List<Container> containers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Container container = new Container();
            container.setContainerId(new Random().nextInt(999999999));
            container.setContainerType("TRANSFER");
            container.setContainerSubType("PALLET");
            container.setCreatedDate(new Date());
            container.setFromLocation(new Random().nextInt(999));
            container.setToLocation(new Random().nextInt(999));
            container.setWeight(new Random().nextDouble());
            containers.add(container);
        }
        return containers;

    }

}

