package com.example.Sharing.model;

import org.hibernate.validator.constraints.Length;
import javax.persistence.*;


@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Length(min = 1, message = "Field can not be empty")
    @Column(name = "name")
    private String name;

    public Type(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
