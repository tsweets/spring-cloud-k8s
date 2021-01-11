package org.beer30.springcloud.simpleprocessor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.beer30.springcloud.simpleprocessor.domain.enums.CardStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Card.
 */
@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "env_id")
    private Integer envId;

    @Column(name = "external_id")
    private Long externalId;

    @NotNull
    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "dda_account_number")
    private String ddaAccountNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "card_status", nullable = false)
    private CardStatus cardStatus;

    @Size(max = 60)
    @Column(name = "imprinted_name", length = 60)
    private String imprintedName;

    @Column(name = "balance", precision = 10, scale = 2)
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "cardholder_id")
    private Cardholder cardholder;

    @OneToMany(mappedBy = "card")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Transaction> transactions = new HashSet<>();

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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDdaAccountNumber() {
        return ddaAccountNumber;
    }

    public void setDdaAccountNumber(String ddaAccountNumber) {
        this.ddaAccountNumber = ddaAccountNumber;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getImprintedName() {
        return imprintedName;
    }

    public void setImprintedName(String imprintedName) {
        this.imprintedName = imprintedName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

    public void setCardholder(Cardholder cardholder) {
        this.cardholder = cardholder;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(id, card.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", envId='" + envId + "'" +
                ", externalId='" + externalId + "'" +
                ", cardNumber='" + cardNumber + "'" +
                ", ddaAccountNumber='" + ddaAccountNumber + "'" +
                ", cardStatus='" + cardStatus + "'" +
                ", imprintedName='" + imprintedName + "'" +
                ", balance='" + balance + "'" +
                '}';
    }
}
