package com.rest.restaurant.app.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@DiscriminatorValue("2")

public class MainCourse extends Met{

}
