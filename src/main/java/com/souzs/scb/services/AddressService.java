package com.souzs.scb.services;

import com.souzs.scb.domain.dtos.AddressDTO;
import com.souzs.scb.domain.entities.Address;
import com.souzs.scb.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void createAddress(AddressDTO dto) {
        Address address = instanceAddress(dto);

        addressRepository.save(address);
    }

    public Address instanceAddress(AddressDTO dto) {
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCep(dto.getCep());
        address.setRoad(dto.getRoad());
        address.setNumber(Integer.parseInt(dto.getNumber()));

        return address;
    }
}
