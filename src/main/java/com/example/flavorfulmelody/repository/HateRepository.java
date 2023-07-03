package com.example.flavorfulmelody.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HateRepository extends JpaRepository<Integer, Long> {
}
