package pl.dawid.app.mapper;

import pl.dawid.app.dto.TrainingBaseDTO;
import pl.dawid.app.dto.TrainingCreateDTO;
import pl.dawid.app.dto.TrainingExtendedDTO;
import pl.dawid.app.dto.TrainingUpdateDTO;
import pl.dawid.app.model.Training;

import java.util.List;

public interface TrainingMapper {
    TrainingBaseDTO mapEntityToBaseDto(Training entity);

    List<TrainingBaseDTO> mapEntityListToBaseDtoList(List<Training> entityList);

    public TrainingExtendedDTO mapEntityToExtendDto(Training entity);

    public List<TrainingExtendedDTO> mapEntityListToExtendDtoList(List<Training> entityList);

    Training mapBaseDTOtoEntity(TrainingBaseDTO dto);

    Training mapUpdateDtoToEntity(TrainingUpdateDTO dto);

    TrainingUpdateDTO mapEntityToUpdateDto(Training entity);

    Training mapCreateDtoToEntity(TrainingCreateDTO dto);
}
