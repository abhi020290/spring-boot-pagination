package com.springbootpagination.springbootpagination.respository;

import com.springbootpagination.springbootpagination.entity.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends JpaRepository<Container,Integer> {


}
