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
    @Size(min=1, max = 10000)
    private String description;

    @NotNull
    @Size(min=2, max=3)
    private String hitdice;

    @NotNull
    @Size(min=1, max= 20)
    private String ability;

    @OneToMany
    @JoinColumn(name = "classes_id")
    private List<Dungeon> dungeons = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "classes_id")
    private List<Subclasses> subclasses = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "classes_id")
    private List<ClassFeatures> classFeatures = new ArrayList<>();

    public Classes(String name, String description, String hitdice, String ability) {
        this.name = name;
        this.description = description;
        this.hitdice = hitdice;
        this.ability = ability;

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

    public String getHitdice() {
        return hitdice;
    }

    public void setHitdice(String hitdice) {
        this.hitdice = hitdice;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}

