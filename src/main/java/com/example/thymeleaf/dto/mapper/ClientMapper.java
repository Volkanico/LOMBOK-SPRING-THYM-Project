package com.example.thymeleaf.dto.mapper;

import com.example.thymeleaf.dto.CreateClientDTO;
import com.example.thymeleaf.dto.ClientResponseDTO;
import com.example.thymeleaf.entity.Address;
import com.example.thymeleaf.entity.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

    public static Client toEntity(CreateClientDTO dto) {
        Client student = new Client();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setBirthday(dto.getBirthday());

        Address address = new Address();
        address.setZipCode(dto.getZipCode());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setComplement(dto.getComplement());
        address.setDistrict(dto.getDistrict());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setStudent(student);

        student.setAddress(address);

        return student;
    }

    public static ClientResponseDTO toDTO(Client student) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setBirthday(student.getBirthday());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setZipCode(student.getAddress().getZipCode());
        dto.setStreet(student.getAddress().getStreet());
        dto.setNumber(student.getAddress().getNumber());
        dto.setComplement(student.getAddress().getComplement());
        dto.setDistrict(student.getAddress().getDistrict());
        dto.setCity(student.getAddress().getCity());
        dto.setState(student.getAddress().getState());

        return dto;
    }

    public static List<ClientResponseDTO> toDTO(List<Client> students) {
        return students.stream()
                .map(student -> toDTO(student))
                .collect(Collectors.toList());
    }

}
