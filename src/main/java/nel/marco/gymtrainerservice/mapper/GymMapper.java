package nel.marco.gymtrainerservice.mapper;

import nel.marco.gymtrainerservice.business.dto.GymDto;
import nel.marco.gymtrainerservice.db.entity.Gym;
import nel.marco.gymtrainerservice.rest.v1.model.GymModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GymMapper {

  GymMapper INSTANCE = Mappers.getMapper(GymMapper.class);

  // I will like to specify each mapping to be very explicit but policy reporting should catch if
  // something is not mapped properly
  @Mappings({})
  GymModel mapToV1(GymDto gymDto);

  @InheritConfiguration
  GymDto mapToV1(GymModel gymDto);

  // MapStruct can pickup how to map list if you specify single mapping above
  @Mappings({})
  List<GymModel> mapToV1(List<GymDto> gyms);

  @Mappings({})
  GymDto mapToV1(Gym gym);
}
