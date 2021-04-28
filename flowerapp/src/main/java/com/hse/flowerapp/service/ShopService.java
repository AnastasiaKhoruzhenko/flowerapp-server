package com.hse.flowerapp.service;

import com.hse.flowerapp.domain.Shop;
import com.hse.flowerapp.dto.ShopDto;
import com.hse.flowerapp.dto.ShopInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface ShopService {

    Shop getShopById(Long id);

    void updateShopInfo(Shop shop);

    ShopDto convertToDTO(Shop shop);

    ShopInfoDto convertShopToShopInfoDto(Shop shop);

    Shop convertToEntity(ShopDto shopDto);
}
