package com.bootcamp.estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository <Estudiante,Long> {

    boolean existsByEmailAndIdIsNot(String email,Long id);

    boolean existsByEmail(String email);

//    List<Estudiante> findEstudianteByPrimerNombreOrPrimeroApellido(String primerNombre, String primeroApellido);


    //consulta con JPQL
//    @Query ("select e from Estudiante e where e.primerNombre =?1 or e.primeroApellido=?2")
//
//
    @Query (value =  "SELECT * from estudiante where primer_nombre = :primerNombre OR primerApellido = : primerApellido",nativeQuery = true)
    List<Estudiante> findEstudianteByPrimerNombreOrPrimeroApellido(@Param("primerNombre") String primerNombre,
                                                                   @Param("primerApellido") String primeroApellido);

}
