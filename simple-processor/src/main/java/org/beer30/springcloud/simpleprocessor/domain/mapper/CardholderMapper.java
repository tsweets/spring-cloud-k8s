package org.beer30.springcloud.simpleprocessor.domain.mapper;


import org.beer30.springcloud.simpleprocessor.domain.Cardholder;
import org.beer30.springcloud.simpleprocessor.domain.dto.CardholderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Cardholder and its DTO CardholderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CardholderMapper {

    CardholderDTO cardholderToCardholderDTO(Cardholder cardholder);

    @Mapping(target = "cards", ignore = true)
    Cardholder cardholderDTOToCardholder(CardholderDTO cardholderDTO);
}
