package com.computer_database.mapper;

import com.computer_database.dto.ComputerDto;
import com.computer_database.dto.PageDto;
import com.computer_database.dto.PageDtoBuilder;
import com.computer_database.model.Computer;
import com.computer_database.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lag on 05/07/17.
 */
public class PageDtoMapper {

    /**
     * @param page page
     * @return PageDto for jsp
     */
    public static PageDto<ComputerDto> map(Page<Computer> page) {
        List<ComputerDto> list = new ArrayList<>();

        for (Computer c : page.getDatas()) {
            list.add(ComputerDtoMapper.map(c));
        }
        return new PageDtoBuilder<ComputerDto>()
                .setLimit(page.getLimit())
                .setPageCurrent(page.getPageCurrent())
                .setPageTotal(page.getPageTotal())
                .setDatas(list)
                .createPageDto();
    }
}
