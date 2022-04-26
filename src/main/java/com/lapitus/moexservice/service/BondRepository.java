package com.lapitus.moexservice.service;

import com.lapitus.moexservice.dto.BondsDto;
import com.lapitus.moexservice.exception.LimitRequestsException;
import com.lapitus.moexservice.moexClient.CorporateBondClient;
import com.lapitus.moexservice.moexClient.GovermentBondClient;
import com.lapitus.moexservice.parser.Parser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class BondRepository {

    private final CorporateBondClient corporateBondClient;
    private final GovermentBondClient govermentBondClient;
    private final Parser parser;

    @Cacheable(value = "corporateCollection")
    public List<BondsDto> getCorporateBonds() {
        log.info("Getting corporate bonds from Moex");
        String xmlFromMoex = corporateBondClient.getBondsFromMoex();
        List<BondsDto> bondsDtos = parser.parse(xmlFromMoex);
        if (bondsDtos.isEmpty()) {
            log.error("Moex is not available for getting corporate bonds.");
            throw new LimitRequestsException("Moex is not available for getting corporate bonds.");
        }
        return bondsDtos;
    }

    @Cacheable(value = "governmentCollection")
    public List<BondsDto> getGovermentBonds() {
        log.info("Getting goverment bonds from Moex");
        String xmlFromMoex = govermentBondClient.getBondsFromMoex();
        List<BondsDto> bondsDtos = parser.parse(xmlFromMoex);
        if (bondsDtos.isEmpty()) {
            log.error("Moex is not available for getting goverments bonds.");
            throw new LimitRequestsException("Moex is not available for getting goverment bonds.");
        }
        return bondsDtos;
    }
}
