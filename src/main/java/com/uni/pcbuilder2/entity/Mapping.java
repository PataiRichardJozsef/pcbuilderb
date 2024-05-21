package com.uni.pcbuilder2.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hardware_mapping")
public class Mapping {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Generated
    private Integer id;

    private Integer motherboardId;
    private Integer hardwareId;
}
