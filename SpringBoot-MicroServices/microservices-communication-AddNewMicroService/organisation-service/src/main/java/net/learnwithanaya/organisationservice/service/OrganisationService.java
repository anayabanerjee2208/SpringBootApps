package net.learnwithanaya.organisationservice.service;

import net.learnwithanaya.organisationservice.dto.OrganisationDto;

public interface OrganisationService {
    public OrganisationDto saveOrganisation(OrganisationDto organisationDto);

    public OrganisationDto findByOrganisationCode(String organisationCode);
}
