package com.springbootpagination.springbootpagination.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Container {

    @Id
    private Integer containerId;
    private String containerType;
    private String containerSubType;
    private Integer fromLocation;
    private Integer toLocation;
    private double weight;
    private Date createdDate;

}
