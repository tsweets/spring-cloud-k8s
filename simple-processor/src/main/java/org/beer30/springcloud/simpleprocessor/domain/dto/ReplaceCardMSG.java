package org.beer30.springcloud.simpleprocessor.domain.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * A DTO for the Card entity.
 */
public class ReplaceCardMSG implements Serializable {

    private Long id; // Request ID

    private Integer envId;

    private Long externalId; // Skyline ID   (Old Card)
    private Long newCardExternalId; // Skyline ID (New Card - Instant Issue)
    private String newCardCardnumber; // Skyline Number (New Card - Instant Issue)


    private Long cardholderExternalId; //Skyline ID

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

    public Long getCardholderExternalId() {
        return cardholderExternalId;
    }

    public void setCardholderExternalId(Long cardholderExternalId) {
        this.cardholderExternalId = cardholderExternalId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ReplaceCardMSG)) return false;

        ReplaceCardMSG that = (ReplaceCardMSG) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(envId, that.envId)
                .append(externalId, that.externalId)
                .append(cardholderExternalId, that.cardholderExternalId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(envId)
                .append(externalId)
                .append(cardholderExternalId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ReplaceCardMSG{" +
                "id=" + id +
                ", envId=" + envId +
                ", externalId=" + externalId +
                ", newCardExternalId=" + newCardExternalId +
                ", newCardCardnumber=" + newCardCardnumber +
                ", cardholderExternalId=" + cardholderExternalId +
                '}';
    }
}
