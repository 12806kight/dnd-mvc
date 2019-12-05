package org.launchcode.dndmvc.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1)
    private String description;

    @OneToMany
    @JoinColumn(name = "classes_id")
    private List<Dungeon> dungeons = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "classes_id")
    private List<Subclasses> subclasses = new ArrayList<>();

    public Classes(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Classes() {

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

