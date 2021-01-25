package nel.marco.gymtrainerservice.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GymMapper {

  GymMapper INSTANCE = Mappers.getMapper(GymMapper.class);

  // I will like to specify each mapping to be very explicit but policy reporting should catch if
  // something is not mapped properly
  @Mappings({})
  nel.marco.gymtrainerservice.v1.model.Gym mapToV1(
      nel.marco.gymtrainerservice.business.dto.GymDto gymDto);

  @InheritConfiguration
  nel.marco.gymtrainerservice.business.dto.GymDto mapToV1(
      nel.marco.gymtrainerservice.v1.model.Gym gymDto);

  // Mapstruct can pickup how to map list if you specify single mapping above
  @Mappings({})
  List<nel.marco.gymtrainerservice.v1.model.Gym> mapToV1(
      List<nel.marco.gymtrainerservice.business.dto.GymDto> gyms);

  @Mappings({})
  nel.marco.gymtrainerservice.business.dto.GymDto mapToV1(
      nel.marco.gymtrainerservice.db.entity.Gym gym);
}
