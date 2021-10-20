package com.example.model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@ApiModel(description="All details about the city")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "us_cities")
//@SequenceGenerator(name = "EntitySequenceGenerator", sequenceName = "seq_city", allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class City extends BaseModel {

    @ApiModelProperty(notes = "Name of the City", name="name", required=true)
    @NotNull
    @Column(name = "city")
    private String name;

}
