package com.uni.pcbuilder2.repository;

import java.util.List;

import com.uni.pcbuilder2.entity.Hardware;
import com.uni.pcbuilder2.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface HardwareRepository extends CrudRepository<Hardware, Integer> {

    List<Hardware> findByType(Type type);

    List<Hardware> findAllById(Iterable<Integer> ids);

}
