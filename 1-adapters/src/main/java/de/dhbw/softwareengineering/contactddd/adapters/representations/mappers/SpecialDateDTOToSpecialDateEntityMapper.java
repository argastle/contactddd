package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.SpecialDateDTO;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;

import java.util.function.Function;

public class SpecialDateDTOToSpecialDateEntityMapper implements Function<SpecialDateDTO, SpecialDate> {

    @Override
    public SpecialDate apply(SpecialDateDTO specialDateDTO) {
        return map(specialDateDTO);
    }

    public SpecialDate map(SpecialDateDTO specialDateDTO) {
        return new SpecialDate(
                specialDateDTO.getDate(),
                specialDateDTO.getDescription()
        );
    }
}
