package com.lapitus.moexservice.parser;

import com.lapitus.moexservice.dto.BondsDto;

import java.util.List;

public interface Parser {
    List<BondsDto> parse(String ratesAsString);
}
