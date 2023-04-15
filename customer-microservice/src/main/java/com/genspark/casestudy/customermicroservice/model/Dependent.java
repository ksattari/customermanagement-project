package com.genspark.casestudy.customermicroservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long dependentId;
    private String firstName;
    private String lastName;
    private Integer age;

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
