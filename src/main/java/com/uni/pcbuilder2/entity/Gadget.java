package com.uni.pcbuilder2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Generated;

@Entity
@Data
public class Gadget {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Generated
    private Integer id;
    private Integer marketPrice;
    private String name;

}
