package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.SpecialDateDTO;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;

import java.util.function.Function;

public class SpecialDateEntityToSpecialDateDTOMapper implements Function<SpecialDate, SpecialDateDTO> {

    @Override
    public SpecialDateDTO apply(SpecialDate specialDate) {
        return map(specialDate);
    }

    public SpecialDateDTO map(SpecialDate specialDate) {
        return new SpecialDateDTO(
                specialDate.getDate(),
                specialDate.getDescription()
        );
    }
}
