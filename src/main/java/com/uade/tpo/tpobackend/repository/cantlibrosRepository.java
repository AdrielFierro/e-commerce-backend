package com.uade.tpo.tpobackend.repository;

import com.uade.tpo.tpobackend.entity.cantlibros;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface cantlibrosRepository extends JpaRepository<cantlibros, Integer> {

}
