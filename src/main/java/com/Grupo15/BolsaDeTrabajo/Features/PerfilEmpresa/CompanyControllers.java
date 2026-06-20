package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompaniesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Company")
@PreAuthorize("hasRole('COMPANY')")
public class CompanyControllers {

    private final CompanyServices companyServices;

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody CompanyNewDTO newDTO) {
        CompanyResponseDTO createdCompany = companyServices.create_Company(newDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @GetMapping
    public ResponseEntity<Page<CompanyResponseDTO>> listCompanies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Category category,
            @PageableDefault(size = 10) Pageable pageable // Define un tamaño por defecto si no lo mandan
    ) {
        Page<CompanyResponseDTO> companies = companyServices.ListCompanies(name, email, location, category, pageable);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{externalId}")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable UUID externalId) {
        CompanyResponseDTO company = companyServices.getById(externalId);
        return ResponseEntity.ok(company);
    }

    @PutMapping
    public ResponseEntity<CompanyResponseDTO> updateCompany(@RequestBody CompaniesRequestDTO atUpdate
    ) {
        CompanyResponseDTO updatedCompany = companyServices.UpdateCompany(atUpdate);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<CompanyResponseDTO> deleteCompany(@PathVariable UUID externalId) {
        CompanyResponseDTO deletedCompany = companyServices.DeleteCompany(externalId);
        return ResponseEntity.ok(deletedCompany);
    }
}
