package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.CreateClientDTO;
import com.example.thymeleaf.dto.ClientResponseDTO;
import com.example.thymeleaf.dto.UpdateClientDTO;
import com.example.thymeleaf.dto.mapper.ClientMapper;
import com.example.thymeleaf.repository.ClientRepository;
import com.example.thymeleaf.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class ClientController {

    private StudentService studentService;
    private ClientRepository studentRepository;

    @GetMapping
    public ModelAndView showStudents() {
        List<ClientResponseDTO> students = ClientMapper.toDTO(this.studentRepository.findAll());
        return new ModelAndView("students").addObject("students", students);
    }

    @GetMapping("/new")
    public ModelAndView showCreateForm() {
        return new ModelAndView("new-student").addObject("student", new CreateClientDTO());
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("student") @Valid CreateClientDTO studentDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "new-student";
        }

        this.studentService.save(ClientMapper.toEntity(studentDTO));
        attributes.addFlashAttribute("message", "User registered successfully!");
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        ClientResponseDTO responseDTO = ClientMapper.toDTO(this.studentService.findById(id));
        return new ModelAndView("edit-student").addObject("student", responseDTO);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute("student") @Valid UpdateClientDTO studentDTO, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            studentDTO.setId(id);
            return "edit-student";
        }

        this.studentService.update(id, ClientMapper.toEntity(studentDTO));
        attributes.addFlashAttribute("message", "User updated successfully!");
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes attributes) {
        this.studentService.deleteById(id);
        attributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/";
    }

}
