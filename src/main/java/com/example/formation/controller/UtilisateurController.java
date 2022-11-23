package com.example.formation.controller;


import com.example.formation.model.Utilisateur;
import com.example.formation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/Utilisateur")
public class UtilisateurController  {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @GetMapping("/findAllUser")
    private List<Utilisateur> findAllUsers(){
        return utilisateurRepository.findAll();
    }

    @GetMapping("/findUser/{id}")
    private Utilisateur findUser(@RequestParam("id") Integer id){
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        return user.get();
    }

    @PostMapping("/")
    private Utilisateur saveUser(@RequestBody Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }
}
