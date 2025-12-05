package com.adrynun.datadinner.backend.repository;

import com.adrynun.datadinner.backend.entity.CierreCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Repositorio para la entidad CierreCaja.
 */
@Repository
public interface CierreCajaRepository extends JpaRepository<CierreCaja, Integer> {
    /**
     * Encuentra un cierre de caja por su fecha.
     *
     * @param fecha La fecha del cierre de caja.
     * @return Una instancia Optional que contiene el cierre de caja si se encuentra, o vacía si no.
     */
    Optional<CierreCaja> findByFecha(LocalDate fecha);
}
