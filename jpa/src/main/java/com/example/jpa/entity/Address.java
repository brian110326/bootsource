package com.example.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable // 다른 entity에서 포함
public class Address {
    private String city;

    private String street;

    private String zipcode;
}
