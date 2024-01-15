package net.learnwithanaya.organisationservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.learnwithanaya.organisationservice.dto.OrganisationDto;
import net.learnwithanaya.organisationservice.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organisations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @PostMapping
    public ResponseEntity<OrganisationDto> savedOrganisation(@RequestBody OrganisationDto organisationDto){
        OrganisationDto savedOrganisation = organisationService.saveOrganisation(organisationDto);
        return new ResponseEntity<>(savedOrganisation, HttpStatus.CREATED);
    }

    @GetMapping("{organisationCode}")
    public ResponseEntity<OrganisationDto> getOrganisationByCode(@PathVariable("organisationCode") String organisationCode){
        OrganisationDto organisationDto = organisationService.findByOrganisationCode(organisationCode);
        return new ResponseEntity<>(organisationDto, HttpStatus.OK);
    }
}
