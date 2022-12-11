package com.example.thymeleaf.service;

import com.example.thymeleaf.entity.Client;
import com.example.thymeleaf.repository.AddressRepository;
import com.example.thymeleaf.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private AddressRepository addressRepository;
    private ClientRepository studentRepository;

    public Client findById(String id) {
        return this.studentRepository.findById(id).orElseThrow();
    }

    public Client save(Client student) {
        this.studentRepository.save(student);
        this.addressRepository.save(student.getAddress());
        return student;
    }

    public Client update(String id, Client student) {
        Client studentDatabase = this.findById(id);
        BeanUtils.copyProperties(student, studentDatabase, "id", "createdAt", "updatedAt", "address");
        BeanUtils.copyProperties(student.getAddress(), studentDatabase.getAddress(), "id", "createdAt", "updatedAt", "student");
        return this.studentRepository.save(studentDatabase);
    }

    public void deleteById(String id) {
        this.studentRepository.delete(this.findById(id));
    }

}
