package org.beer30.springcloud.simpleprocessor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Cardholder.
 */
@Entity
@Table(name = "cardholder")
public class Cardholder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "env_id", nullable = false)
    private Integer envId;

    @NotNull
    @Column(name = "external_id", nullable = false)
    private Long externalId;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

   /* @Column(name = "dob")
    private LocalDate dob;*/

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "home_street1")
    private String homeStreet1;

    @Column(name = "home_street2")
    private String homeStreet2;

    @Column(name = "home_city")
    private String homeCity;

    @Column(name = "home_state")
    private String homeState;

    @Column(name = "home_postal_code")
    private String homePostalCode;

    @Column(name = "ship_street1")
    private String shipStreet1;

    @Column(name = "ship_street2")
    private String shipStreet2;

    @Column(name = "ship_city")
    private String shipCity;

    @Column(name = "ship_state")
    private String shipState;

    @Column(name = "ship_postal_code")
    private String shipPostalCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "cardholder")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Card> cards = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnvId() {
        return envId;
    }

    public void setEnvId(Integer envId) {
        this.envId = envId;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   /* public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }*/

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getHomeStreet1() {
        return homeStreet1;
    }

    public void setHomeStreet1(String homeStreet1) {
        this.homeStreet1 = homeStreet1;
    }

    public String getHomeStreet2() {
        return homeStreet2;
    }

    public void setHomeStreet2(String homeStreet2) {
        this.homeStreet2 = homeStreet2;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getHomeState() {
        return homeState;
    }

    public void setHomeState(String homeState) {
        this.homeState = homeState;
    }

    public String getHomePostalCode() {
        return homePostalCode;
    }

    public void setHomePostalCode(String homePostalCode) {
        this.homePostalCode = homePostalCode;
    }

    public String getShipStreet1() {
        return shipStreet1;
    }

    public void setShipStreet1(String shipStreet1) {
        this.shipStreet1 = shipStreet1;
    }

    public String getShipStreet2() {
        return shipStreet2;
    }

    public void setShipStreet2(String shipStreet2) {
        this.shipStreet2 = shipStreet2;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cardholder cardholder = (Cardholder) o;
        return Objects.equals(id, cardholder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cardholder{" +
                "id=" + id +
                ", envId='" + envId + "'" +
                ", externalId='" + externalId + "'" +
                ", firstName='" + firstName + "'" +
                ", middleName='" + middleName + "'" +
                ", lastName='" + lastName + "'" +
                //     ", dob='" + dob + "'" +
                ", ssn='" + ssn + "'" +
                ", homeStreet1='" + homeStreet1 + "'" +
                ", homeStreet2='" + homeStreet2 + "'" +
                ", homeCity='" + homeCity + "'" +
                ", homeState='" + homeState + "'" +
                ", homePostalCode='" + homePostalCode + "'" +
                ", shipStreet1='" + shipStreet1 + "'" +
                ", shipStreet2='" + shipStreet2 + "'" +
                ", shipCity='" + shipCity + "'" +
                ", shipState='" + shipState + "'" +
                ", shipPostalCode='" + shipPostalCode + "'" +
                ", phoneNumber='" + phoneNumber + "'" +
                ", email='" + email + "'" +
                '}';
    }
}
