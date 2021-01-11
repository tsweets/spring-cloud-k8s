package org.beer30.springcloud.simpleprocessor.domain.mapper;

import org.beer30.springcloud.simpleprocessor.domain.Card;
import org.beer30.springcloud.simpleprocessor.domain.Transaction;
import org.beer30.springcloud.simpleprocessor.domain.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Transaction and its DTO TransactionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransactionMapper {

    @Mapping(source = "card.id", target = "cardId")
    TransactionDTO transactionToTransactionDTO(Transaction transaction);

    @Mapping(source = "cardId", target = "card")
    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);

    default Card cardFromId(Long id) {
        if (id == null) {
            return null;
        }
        Card card = new Card();
        card.setId(id);
        return card;
    }
}
