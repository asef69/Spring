package model;

import jakarta.persistence.*;

//@Entity
//@Table(name="address")
@Entity(name="addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
