package com.souzs.scb.controllers;

import com.souzs.scb.domain.dtos.LibraryMinDTO;
import com.souzs.scb.domain.dtos.LibraryUserDTO;
import com.souzs.scb.services.LibraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LibraryUserDTO dto) {
        LibraryMinDTO resultDTO = libraryService.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(resultDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(resultDTO);
    }
}
