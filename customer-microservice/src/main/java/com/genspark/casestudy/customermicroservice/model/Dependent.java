package com.genspark.casestudy.customermicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Dependent(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
