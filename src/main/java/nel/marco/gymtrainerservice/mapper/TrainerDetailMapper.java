package nel.marco.gymtrainerservice.mapper;

import nel.marco.gymtrainerservice.business.dto.TrainerDto;
import nel.marco.gymtrainerservice.db.entity.TrainerDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = TrainerMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TrainerDetailMapper {

  TrainerDetailMapper INSTANCE = Mappers.getMapper(TrainerDetailMapper.class);

  @Mappings({
    @Mapping(target = "trainer", ignore = true),
    @Mapping(target = "inserted", ignore = true),
    @Mapping(target = "updated", ignore = true),
  })
  TrainerDetail mapToV1(TrainerDto trainerDto);
}
