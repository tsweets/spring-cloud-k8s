package org.beer30.springcloud.simpleprocessor.domain.mapper;

import org.beer30.springcloud.simpleprocessor.domain.Card;
import org.beer30.springcloud.simpleprocessor.domain.Cardholder;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Card and its DTO CardDTO.
 */
@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(source = "cardholder.id", target = "cardholderId")
    CardDTO cardToCardDTO(Card card);

    @Mapping(source = "cardholderId", target = "cardholder")
    @Mapping(target = "transactions", ignore = true)
    Card cardDTOToCard(CardDTO cardDTO);

    default Cardholder cardholderFromId(Long id) {
        if (id == null) {
            return null;
        }
        Cardholder cardholder = new Cardholder();
        cardholder.setId(id);
        return cardholder;
    }
}
