package net.learnwithanaya.organisationservice.mapper;

import net.learnwithanaya.organisationservice.dto.OrganisationDto;
import net.learnwithanaya.organisationservice.entity.Organisation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    OrganisationDto mapToOrganisationDto(Organisation organisation);
    Organisation mapToOrganisation(OrganisationDto organisationDto);
}
