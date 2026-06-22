package com.Grupo15.BolsaDeTrabajo.Features.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<CompaniesEntity,Long> {

    boolean existsByCuit(String Cuit);

    boolean existsByEmail(String email);

    Optional<CompaniesEntity> findById(Long id);

    Optional<CompaniesEntity> findByExternalId(UUID externalId);

    Optional<CompaniesEntity> findByCuit(String cuit);

    Optional<CompaniesEntity> findByEmail(String email);

    List<CompaniesEntity> findByNameContaining(String eame);

    List<CompaniesEntity> findByEmailContaining(String email);

    List<CompaniesEntity> findByCategory(Category category);

    List<CompaniesEntity> findByLocationContaining(String location);



    @Query("""
            SELECT c FROM CompaniesEntity c
            WHERE (:name     IS NULL OR c.name    LIKE CONCAT('%',:name, '%'))
            AND   (:email    IS NULL OR c.email    LIKE CONCAT('%',:email,'%'))
            AND   (:category IS NULL OR c.category  = :category)
            AND   (:location IS NULL OR c.location LIKE CONCAT('%',:location,'%'))
    """)
    Page<CompaniesEntity> findByFilters(
            @Param("name")     String name,
            @Param("email")    String email,
            @Param("category") Category category,
            @Param("location") String location,
            Pageable pageable
    );
}