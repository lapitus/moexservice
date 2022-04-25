package com.lapitus.moexservice.moexClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="corporatebonds", url="${moex.bonds.corporate.url}")
public interface CorporateBondClient {
    @GetMapping
    String getBondsFromMoex();
}
