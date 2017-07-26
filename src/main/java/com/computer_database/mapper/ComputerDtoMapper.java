package com.computer_database.mapper;

import com.computer_database.dto.ComputerDto;
import com.computer_database.dto.ComputerDtoBuilder;
import com.computer_database.model.CompanyBuilder;
import com.computer_database.model.Computer;

/**
 * Created by lag on 05/07/17.
 */
public class ComputerDtoMapper {
    /**
     * @param computer computer to map
     * @return computerDto for jsp
     */
    public static ComputerDto map(Computer computer) {
        return new ComputerDtoBuilder()
                .setId(computer.getId())
                .setName(computer.getName())
                .setIntroduced(computer.getIntroduced())
                .setDiscontinued(computer.getDiscontinued())
                .setCompany(new CompanyBuilder()
                        .setId(computer.getCompany().getId())
                        .setName(computer.getCompany().getName())
                        .createCompany())
                .createComputerDto();
    }
}
