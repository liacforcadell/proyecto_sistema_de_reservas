package com.Hotel_booking.WebApp.reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository <Reserva,Long>{

    @Query(value = "SELECT * FROM reserva WHERE habitacion_id = ?1 AND \n" +
            "((fecha_ingreso <= ?2 AND fecha_salida > ?2) \n" +
            " OR \n" +
            " ((fecha_ingreso < ?3 AND fecha_salida >= ?3) \n" +
            " OR \n" +
            " (fecha_ingreso >= ?2 AND fecha_salida < ?3))) FETCH FIRST ROW ONLY", nativeQuery = true)
    Optional<Reserva> findByHabitacionReserva(long codHabitacion, LocalDate fechaIngreso, LocalDate fechaSalida);

    @Query(value = "SELECT * FROM reserva WHERE habitacion_id=?1", nativeQuery = true)
    List<Reserva> findAllReservaHabitacion(long codHabitacion);

    @Query(value = "SELECT * FROM reserva WHERE Reserva_ID=?1", nativeQuery = true)
    Optional<Reserva> findByReservaID(long codReserva);

    @Query("SELECT r FROM Reserva r WHERE r.fechaIngreso = ?1")
    List<Reserva> findReservaConfig(LocalDate fechaIngreso);


}
