package com.Grupo15.BolsaDeTrabajo.Features.Company;

import com.Grupo15.BolsaDeTrabajo.Features.Company.dto.CompaniesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Company.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Company.dto.CompanyResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('COMPANY')")
@RequestMapping("/BolsaDeTrabajo/company")
@Tag(name = "Companies", description = "Endpoints for registration, update, and management of company profiles")
public class CompanyControllers {

    private final CompanyServices companyServices;


    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyNewDTO newDTO) {
        CompanyResponseDTO createdCompany = companyServices.create_Company(newDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @GetMapping
    @Operation(summary = "List companies with filters", description = "Returns a paginated list of companies filtered by name, email, location, or category.")
    @ApiResponse(responseCode = "200", description = "Paginated list retrieved successfully")
    public ResponseEntity<Page<CompanyResponseDTO>> listCompanies(
            @Parameter(description = "Filter by company name") @RequestParam(required = false) String name,
            @Parameter(description = "Filter by email address") @RequestParam(required = false) String email,
            @Parameter(description = "Filter by geographic location") @RequestParam(required = false) String location,
            @Parameter(description = "Filter by business category") @RequestParam(required = false) Category category,
            @PageableDefault(size = 10) Pageable pageable // Define un tamaño por defecto si no lo mandan
    ) {
        Page<CompanyResponseDTO> companies = companyServices.ListCompanies(name, email, location, category, pageable);
        return ResponseEntity.ok(companies);
    }


    @GetMapping("/{externalId}")
    @Operation(summary = "Get company by ID", description = "returns the profile details of a company using its external ID.")
    @ApiResponse(responseCode = "200", description = "Company found successfully")
    @ApiResponse(responseCode = "404", description = "Company not found with the provided ID")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@Parameter(description = "Unique external ID of the company")@PathVariable UUID externalId) {
        CompanyResponseDTO company = companyServices.getById(externalId);
        return ResponseEntity.ok(company);
    }

    @PutMapping
    @Operation(summary = "Update profile details", description = "Allows the authenticated company to modify its own profile information.")
    @ApiResponse(responseCode = "200", description = "Profile updated successfully")
    @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    @ApiResponse(responseCode = "404", description = "Company not found")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@RequestBody CompaniesRequestDTO atUpdate,
                                                            @AuthenticationPrincipal UserDetails userDetails
    ) {
        CompanyResponseDTO updatedCompany = companyServices.UpdateCompany(atUpdate,userDetails.getUsername());
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{externalId}")
    @Operation(summary = "Delete a company (Logical delete)", description = "Performs a logical delete on the company and inactivates all its posts. Requires no open job offers.")
    @ApiResponse(responseCode = "200", description = "Company deleted successfully")
    @ApiResponse(responseCode = "400", description = "Cannot delete a company with job offers in OPEN state")
    @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    @ApiResponse(responseCode = "404", description = "Company not found")
    public ResponseEntity<CompanyResponseDTO> deleteCompany(@PathVariable UUID externalId,
                                                            @Parameter(description = "External ID of the company to delete")@AuthenticationPrincipal UserDetails userDetails) {
        CompanyResponseDTO deletedCompany = companyServices.DeleteCompany(externalId, userDetails.getUsername());
        return ResponseEntity.ok(deletedCompany);
    }
}
