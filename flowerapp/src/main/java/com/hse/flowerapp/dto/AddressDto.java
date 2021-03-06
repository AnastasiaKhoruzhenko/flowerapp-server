package com.hse.flowerapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hse.flowerapp.domain.Address;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {

    private Long userId;
    private Long shopId;
    private String region;
    private String town;
    private String street;
    private String house;
    private String building;
    private String flat;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getHouseBuilding() {
        return building;
    }

    public void setHouseBuilding(String houseBuilding) {
        this.building = houseBuilding;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public static AddressDto toDto(Address address){
        AddressDto addressDto = new AddressDto();

        addressDto.setRegion(address.getRegion());
        addressDto.setTown(address.getTown());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouse(address.getHouse());
        if(address.getHouseBuilding() != null)
            addressDto.setHouseBuilding(address.getHouseBuilding());
        else
            addressDto.setHouseBuilding(null);

        if(address.getFlat() != null)
            addressDto.setFlat(address.getFlat());
        else
            addressDto.setFlat(null);

        return addressDto;
    }
}
