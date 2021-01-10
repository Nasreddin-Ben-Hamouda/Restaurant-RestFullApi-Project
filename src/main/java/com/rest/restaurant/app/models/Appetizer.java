package com.rest.restaurant.app.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@DiscriminatorValue("1")
public class Appetizer extends Met {

}
