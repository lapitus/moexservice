package com.lapitus.moexservice.moexClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="govermentbonds", url="${moex.bonds.goverment.url}")
public interface GovermentBondClient {
    @GetMapping
    String getBondsFromMoex();
}
