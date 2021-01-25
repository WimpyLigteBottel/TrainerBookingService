package nel.marco.gymtrainerservice.mapper;

import nel.marco.gymtrainerservice.business.dto.TrainerDto;
import nel.marco.gymtrainerservice.db.entity.Trainer;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR, uses = GymMapper.class)
public interface TrainerMapper {

    TrainerMapper INSTANCE = Mappers.getMapper(TrainerMapper.class);


    @Mappings({
            @Mapping(target = "gymId", source = "gymDto.id")
    })
    TrainerModel mapToV1(TrainerDto trainerDto);

    @Mappings({
            @Mapping(target = "gymDto.id", source = "gymId"),
    })
    TrainerDto mapToV1(TrainerModel trainerDto);

    @Mappings({
            @Mapping(target = "gymDto", ignore = true),
    })
    TrainerDto mapToV1(Trainer trainer);

    @Mappings({})
    List<TrainerDto> mapToListV1(List<TrainerDto> trainerDtos);

    @Mappings({})
    List<TrainerModel> mapToV1(List<TrainerDto> all);

}
