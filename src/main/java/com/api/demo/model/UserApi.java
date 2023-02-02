package com.api.demo.model;


import com.api.demo.validation.LivesInFrance;
import com.api.demo.validation.PhoneNumberIsFrench;
import com.api.demo.validation.Under18;
import com.fasterxml.jackson.annotation.JsonProperty;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("users")
@AllArgsConstructor
@NoArgsConstructor
//@JsonSerialize(using = UserApiSerializer.class)
public class UserApi {

    @Id
    private String id;
    @NotEmpty(message = "User's name can not be empty")
    private String name;

    @NotEmpty(message = "User's birth date can not be empty")
    @JsonProperty("birth_date")
    @Under18()
    private String birthDate;
    @JsonProperty("country_of_residence")
    @Field("country_of_residence")
    @NotEmpty(message = "User's country of residence can not be empty")
    @LivesInFrance(message= "User must have residence in France")
    private String countryOfResidence;

    @Field("phone_number")
    @JsonProperty("phone_number")
    @PhoneNumberIsFrench
    private String phoneNumber;

    @Field("gender")
    @JsonProperty("gender")
    @Size(min = 0, max = 1,message = "Invalid gender value. Must be 'M' or 'F'")
    private String gender;

    public UserApi(String name, String birthDate, String countryOfResidence, String phoneNumber,String gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.countryOfResidence = countryOfResidence;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
