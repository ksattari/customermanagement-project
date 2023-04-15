package com.genspark.casestudy.customermicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author  Khashayar Sattari
 *
 * used to register customer and login, backed by database table
 */

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long customerId;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Dependent> dependents;

    //adds new dependent to customer
    public void addDependent(Dependent dependent) {
        if (dependents == null) {
            dependents = new ArrayList<>();
        }
        dependents.add(dependent);
    }

    //removes a dependent from customer if id exists
    //returns true if dependent exists
    public boolean removeDependent(Long dependentId) {
       return dependents.removeIf(d -> Objects.equals(d.getDependentId(), dependentId));
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

}


