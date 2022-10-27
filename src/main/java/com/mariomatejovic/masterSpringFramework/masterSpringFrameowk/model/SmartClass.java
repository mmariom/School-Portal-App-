package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//@Data  ma tostring() metodu ktora moze sposobovat Stackoverflow exception ked sa pouzivaju
// onetomany,manytomany ,onetoone relationships , preto je dobre nahradit bud  @Getter @Seter
// alebo skusis danu asociaci oznacit ako     @EqualsAndHashCode.Exclude
@Getter
@Setter
@Entity
@Table(name = "class")
public class SmartClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int classId;



    @NotBlank(message = "Name Must noot be blank")
    @Size(min = 3, message = "must be atleast 3 chars long")
    private String name ;



    private String premium;






    // mappedBy ma hodonotu child entity(Person) - smartClass je variable
    // s anotaciou @ManyToone
    @OneToMany(mappedBy = "smartClass",fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,targetEntity = Person.class)
    @EqualsAndHashCode.Exclude
    private Set<Person> persons;





}
