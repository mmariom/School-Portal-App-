package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.annotation.FieldsValueMatch;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.annotation.PasswordValidator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

//@Data  ma tostring() metodu ktora moze sposobovat Stackoverflow exception ked sa pouzivaju
// onetomany,manytomany ,onetoone relationships , preto je dobre nahradit bud  @Getter @Seter
// alebo skusis danu asociaci oznacit ako     @EqualsAndHashCode.Exclude

@Getter
@Setter
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int personId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;

    @NotBlank(message="Confirm Email must not be blank")
    @Email(message = "Please provide a valid confirm email address" )
    @Transient
    private String confirmEmail;

    @NotBlank(message="Password must not be blank")
    @Size(min=5, message="Password must be at least 5 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="Confirm Password must not be blank")
    @Size(min=5, message="Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPwd;


    //toone pouzivat EAGER tomany LAZY
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    //name= je hodnota/ foreign key  v tabulke Person a
    // referencedColumnName je Id v classe Roles
    @JoinColumn(name = "role_id", referencedColumnName = "roleId",nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId",nullable = true)
    private Address address;


    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId",nullable = true)
    private SmartClass smartClass;
}
