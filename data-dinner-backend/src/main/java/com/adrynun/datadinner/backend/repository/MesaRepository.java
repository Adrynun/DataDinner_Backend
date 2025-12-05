package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Mesa.
 */
@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
}
