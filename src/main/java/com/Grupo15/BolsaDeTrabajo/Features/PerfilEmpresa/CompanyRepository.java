package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompaniesEntity,Long> {

    boolean existsByCuit(String Cuit);

    boolean existsByEmail(String email);


}
