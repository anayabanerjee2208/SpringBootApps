package net.learnwithanaya.organisationservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.learnwithanaya.organisationservice.dto.OrganisationDto;
import net.learnwithanaya.organisationservice.entity.Organisation;
import net.learnwithanaya.organisationservice.repository.OrganisationRepository;
import net.learnwithanaya.organisationservice.service.OrganisationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrganisationDto saveOrganisation(OrganisationDto organisationDto) {
        Organisation organisation =modelMapper.map(organisationDto, Organisation.class);
        Organisation savedOrganisation = organisationRepository.save(organisation);
        OrganisationDto savedOrganisationDto = modelMapper.map(savedOrganisation, OrganisationDto.class);
        return savedOrganisationDto;
    }

    @Override
    public OrganisationDto findByOrganisationCode(String organisationCode) {
        Organisation organisation = organisationRepository.findByOrganisationCode(organisationCode);
        return modelMapper.map(organisation, OrganisationDto.class);
    }
}
