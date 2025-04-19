package com.souzs.scb.services;

import com.souzs.scb.domain.dtos.LibraryDTO;
import com.souzs.scb.domain.dtos.LibraryMinDTO;
import com.souzs.scb.domain.dtos.LibraryUserDTO;
import com.souzs.scb.domain.entities.Address;
import com.souzs.scb.domain.entities.Library;
import com.souzs.scb.domain.entities.User;
import com.souzs.scb.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private AddressService addressService;

    @Transactional
    public LibraryMinDTO create(LibraryUserDTO dto) {
        Library library = new Library();
        library.setName(dto.getName());
        library.setAcronym(dto.getAcronym());

        User userLibrary = authService.instanceUser(dto);
        Address address = addressService.instanceAddress(dto.getAddress());

        library.setAddress(address);
        library.setUser(userLibrary);

        library = libraryRepository.save(library); // Por CASCADE salva o user e o address

        return new LibraryMinDTO(library);
    }
}
