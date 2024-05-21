package com.uni.pcbuilder2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uni.pcbuilder2.model.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Generated;

@Entity
@Data
public class Hardware {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Generated
    private Integer id;
    private Type type;
    private String name;
    private Integer marketPrice;
    private String model;
    private String manufacturer;

}
