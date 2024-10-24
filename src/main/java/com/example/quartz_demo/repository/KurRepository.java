package com.example.quartz_demo.repository;

import com.example.quartz_demo.model.Kur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KurRepository extends JpaRepository<Kur, Long> {
}

