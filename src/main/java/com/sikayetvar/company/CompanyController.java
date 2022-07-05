package com.sikayetvar.company;

import com.sikayetvar.complaint.Complaint;
import com.sikayetvar.complaint.ComplaintDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/company")
public class CompanyController {

    @Autowired
    private ModelMapper modelMapper;
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        return companyService.getAllCompanies()
                .stream().map(company -> modelMapper.map(company, CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyByPath(@PathVariable(name = "id") Long id) {
        Company company = companyService.getCompanyByID(id);

        CompanyDTO companyResponse = modelMapper.map(company, CompanyDTO.class);
        return ResponseEntity.ok().body(companyResponse);
    }

    @GetMapping(params = "id")
    public ResponseEntity<CompanyDTO> getCompanyByParam(@RequestParam Long id) {
        Company company = companyService.getCompanyByID(id);

        CompanyDTO companyResponse = modelMapper.map(company, CompanyDTO.class);
        return ResponseEntity.ok().body(companyResponse);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createComplaint(@RequestBody CompanyDTO companyDTO) {
        Company companyRequest = modelMapper.map(companyDTO, Company.class);
        Company company = companyService.createCompany(companyRequest);

        CompanyDTO companyResponse = modelMapper.map(company, CompanyDTO.class);
        return new ResponseEntity<CompanyDTO>(companyResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompanyByPath(@PathVariable(name = "id") Long id, @RequestBody CompanyDTO companyDTO) {
        Company companyRequest = modelMapper.map(companyDTO, Company.class);
        Company company = companyService.updateCompany(id, companyRequest);

        CompanyDTO companyResponse = modelMapper.map(company, CompanyDTO.class);
        return new ResponseEntity<CompanyDTO>(companyResponse, HttpStatus.CREATED);
    }

    @PutMapping(params = "id")
    public ResponseEntity<CompanyDTO> updateCompanyByParam(@RequestParam Long id, @RequestBody CompanyDTO companyDTO) {
        Company companyRequest = modelMapper.map(companyDTO, Company.class);
        Company company = companyService.updateCompany(id, companyRequest);

        CompanyDTO companyResponse = modelMapper.map(company, CompanyDTO.class);
        return new ResponseEntity<CompanyDTO>(companyResponse, HttpStatus.CREATED);
    }

    //TODO: adding response to delete might be better
    @DeleteMapping("/{id}")
    public void deleteCompanyByPath(@PathVariable(name = "id") Long id) {
        companyService.deleteCompany(id);
    }

    @DeleteMapping(params = "id")
    public void deleteCompanyByParam(@RequestParam Long id) {
        companyService.deleteCompany(id);
    }
}
