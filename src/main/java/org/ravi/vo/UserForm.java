package org.ravi.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserForm {

   /* {
            "firstName" : "ravi",
            "lastName" : "kumar",
            "email" : "ravi@gmail.com",
            "location" : "hyderabad",
            "middleName" : ""
    } */

    @NotBlank(message = "First name is mandatory")
    private  String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private String middleName;

    @NotBlank(message = "Email is mandatory")
    private String email;

    private String location;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
