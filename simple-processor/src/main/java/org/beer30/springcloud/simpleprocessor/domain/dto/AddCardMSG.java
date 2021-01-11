package org.beer30.springcloud.simpleprocessor.domain.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * A DTO for the Card entity.
 */
public class AddCardMSG implements Serializable {

    private Long id; // Request ID

    private Integer envId;

    private Long newCardExternalId; // Skyline ID (New Card - Instant Issue)
    private String newCardCardnumber; // Skyline Number (New Card - Instant Issue)

    private Long cardholderExternalId; //Skyline ID

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

    public Long getNewCardExternalId() {
        return newCardExternalId;
    }

    public void setNewCardExternalId(Long newCardExternalId) {
        this.newCardExternalId = newCardExternalId;
    }

    public String getNewCardCardnumber() {
        return newCardCardnumber;
    }

    public void setNewCardCardnumber(String newCardCardnumber) {
        this.newCardCardnumber = newCardCardnumber;
    }

    public Long getCardholderExternalId() {
        return cardholderExternalId;
    }

    public void setCardholderExternalId(Long cardholderExternalId) {
        this.cardholderExternalId = cardholderExternalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AddCardMSG)) return false;

        AddCardMSG that = (AddCardMSG) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(envId, that.envId)
                .append(newCardExternalId, that.newCardExternalId)
                .append(newCardCardnumber, that.newCardCardnumber)
                .append(cardholderExternalId, that.cardholderExternalId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(envId)
                .append(newCardExternalId)
                .append(newCardCardnumber)
                .append(cardholderExternalId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "AddCardMSG{" +
                "id=" + id +
                ", envId=" + envId +
                ", newCardExternalId=" + newCardExternalId +
                ", newCardCardnumber='" + newCardCardnumber + '\'' +
                ", cardholderExternalId=" + cardholderExternalId +
                '}';
    }
}
