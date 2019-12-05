package org.launchcode.dndmvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Subclasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1)
    private String description;

    @ManyToOne
    private Classes classes;

    public Subclasses(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Subclasses() {

    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {this.classes = classes;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

