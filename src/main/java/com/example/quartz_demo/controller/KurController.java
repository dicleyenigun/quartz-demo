package com.example.quartz_demo.controller;

import com.example.quartz_demo.model.Kur;
import com.example.quartz_demo.repository.KurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/kurlar")
public class KurController {

    @Autowired
    private KurRepository kurRepository;

    @GetMapping
    public List<Kur> getAllKurlar() {
        return kurRepository.findAll();
    }
}
