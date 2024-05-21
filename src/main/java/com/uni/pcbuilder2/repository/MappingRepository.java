package com.uni.pcbuilder2.repository;

import java.util.List;

import com.uni.pcbuilder2.entity.Mapping;
import org.springframework.data.repository.CrudRepository;

public interface MappingRepository extends CrudRepository<Mapping, Integer> {
    List<Mapping> findByMotherboardId(Integer id);

}
