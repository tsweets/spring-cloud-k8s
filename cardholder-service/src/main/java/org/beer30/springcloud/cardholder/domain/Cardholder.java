package org.beer30.springcloud.cardholder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cardholder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String externalId;
    @NotBlank(message = "First Name is required")
    private String firstName;
    private String middleName;
    @NotBlank(message = "Last Name is required")
    private String lastName;
    private String status;
    private String ssn;
    private Timestamp dob;
    private Timestamp registrationDate;
    private String phone;
    private String email;

    @Transient
    @JsonInclude
    private Card card;
   /* private Address addressHome;
    private Address addressShip;
    private Set<FinancialAccount> financialAccounts = new HashSet<>();
    private ProgramManager programManager;
   */

}
