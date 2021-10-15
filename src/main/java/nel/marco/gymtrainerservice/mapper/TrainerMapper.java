package nel.marco.gymtrainerservice.mapper;

import nel.marco.gymtrainerservice.business.dto.TrainerDto;
import nel.marco.gymtrainerservice.db.entity.Trainer;
import nel.marco.gymtrainerservice.db.entity.TrainerDetail;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerDetailModel;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
    unmappedSourcePolicy = ReportingPolicy.WARN,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = GymMapper.class)
public interface TrainerMapper {

  TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);

  @Mappings({@Mapping(target = "gymId", source = "gymDto.id")})
  TrainerModel mapToV1(TrainerDto trainerDto);

  @Mappings({
    @Mapping(target = "gymDto.id", source = "gymId"),

    // Below is used for trainerDetail class
    @Mapping(target = "detailId", ignore = true),
    @Mapping(target = "contactNumber", ignore = true),
    @Mapping(target = "email", ignore = true),
    @Mapping(target = "description", ignore = true),
  })
  TrainerDto mapToV1(TrainerModel trainerDto);

  @Mappings({
    @Mapping(target = "gymDto", source = "gym"),
    // Below is used for trainerDetail class
    @Mapping(target = "detailId", ignore = true),
    @Mapping(target = "contactNumber", ignore = true),
    @Mapping(target = "email", ignore = true),
    @Mapping(target = "description", ignore = true),
  })
  TrainerDto mapToV1(Trainer trainer);

  @Mappings({})
  List<TrainerDto> mapToListV1(List<TrainerDto> trainerDtos);

  @Mappings({})
  List<TrainerModel> mapToV1(List<TrainerDto> all);

  @Mappings({
    @Mapping(target = "gymDto", ignore = true),
  })
  TrainerDto mapToV1(TrainerDetailModel detailTrainerModel);

  @Mappings({
    @Mapping(target = "detailId", source = "id"),
    @Mapping(target = "name", source = "trainer.name"),
  })
  TrainerDetailModel mapToV1(TrainerDetail trainerDetail);
}
