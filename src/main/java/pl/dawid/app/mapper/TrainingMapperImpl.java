package pl.dawid.app.mapper;

import org.springframework.stereotype.Component;
import pl.dawid.app.dto.TrainingBaseDTO;
import pl.dawid.app.dto.TrainingCreateDTO;
import pl.dawid.app.dto.TrainingExtendedDTO;
import pl.dawid.app.dto.TrainingUpdateDTO;
import pl.dawid.app.model.Training;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TrainingMapperImpl implements TrainingMapper {
    @Override
    public TrainingBaseDTO mapEntityToBaseDto(Training entity) {
        if (entity == null) {
            return null;
        }
        TrainingBaseDTO dto = new TrainingBaseDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getTrainingDate() != null) {
            dto.setTrainingDate(entity.getTrainingDate());
        }
        if (entity.getTrainingTime() != null) {
            dto.setTrainingTime(entity.getTrainingTime());
        }
        if (entity.getTravelledDistance() != null) {
            dto.setTravelledDistance(entity.getTravelledDistance());
        }
        if (entity.getBurnedCalories() != null) {
            dto.setBurnedCalories(entity.getBurnedCalories());
        }
        if (entity.getComment() != null) {
            dto.setComment(entity.getComment());
        }
        return dto;
    }

    @Override
    public List<TrainingBaseDTO> mapEntityListToBaseDtoList(List<Training> entityList) {
        if (entityList == null) {
            return null;
        }
        List<TrainingBaseDTO> dtoList = new ArrayList<>(entityList.size());
        entityList.stream().map(e -> mapEntityToBaseDto(e)).filter(Objects::nonNull).forEach(dtoList::add);
        return dtoList;
    }

    public TrainingExtendedDTO mapEntityToExtendDto(Training entity) {
        if (entity == null) {
            return null;
        }
        TrainingExtendedDTO dto = new TrainingExtendedDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getTrainingDate() != null) {
            dto.setTrainingDate(entity.getTrainingDate());
        }
        if (entity.getTrainingTime() != null) {
            dto.setTrainingTime(entity.getTrainingTime());
        }
        if (entity.getTravelledDistance() != null) {
            dto.setTravelledDistance(entity.getTravelledDistance());
        }
        if (entity.getBurnedCalories() != null) {
            dto.setBurnedCalories(entity.getBurnedCalories());
        }
        if (entity.getComment() != null) {
            dto.setComment(entity.getComment());
        }
        entity.getAverageSpeed().ifPresent(dto::setAverageSpeed);
        return dto;
    }

    public List<TrainingExtendedDTO> mapEntityListToExtendDtoList(List<Training> entityList) {
        if (entityList == null) {
            return null;
        }
        List<TrainingExtendedDTO> dtoList = new ArrayList<>(entityList.size());
        entityList.stream().map(e -> mapEntityToExtendDto(e)).filter(Objects::nonNull).forEach(dtoList::add);
        return dtoList;
    }

    @Override
    public Training mapBaseDTOtoEntity(TrainingBaseDTO dto) {
        if (dto == null) {
            return null;
        }
        Training entity = new Training();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getTrainingDate() != null) {
            entity.setTrainingDate(dto.getTrainingDate());
        }
        if (dto.getTrainingTime() != null) {
            entity.setTrainingTime(dto.getTrainingTime());
        }
        if (dto.getTravelledDistance() != null) {
            entity.setTravelledDistance(dto.getTravelledDistance());
        }
        if (dto.getBurnedCalories() != null) {
            entity.setBurnedCalories(dto.getBurnedCalories());
        }
        if (dto.getComment() != null) {
            entity.setComment(dto.getComment());
        }
        return entity;
    }

    @Override
    public Training mapUpdateDtoToEntity(TrainingUpdateDTO dto) {
        if (dto == null) {
            return null;
        }
        Training entity = new Training();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getTrainingDate() != null) {
            entity.setTrainingDate(dto.getTrainingDate());
        }
        if (dto.getTrainingTime() != null) {
            entity.setTrainingTime(dto.getTrainingTime());
        }
        if (dto.getTravelledDistance() != null) {
            entity.setTravelledDistance(dto.getTravelledDistance());
        }
        if (dto.getBurnedCalories() != null) {
            entity.setBurnedCalories(dto.getBurnedCalories());
        }
        if (dto.getComment() != null) {
            entity.setComment(dto.getComment());
        }
        return entity;
    }

    @Override
    public TrainingUpdateDTO mapEntityToUpdateDto(Training entity) {
        if (entity == null) {
            return null;
        }
        TrainingUpdateDTO dto = new TrainingUpdateDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getTrainingDate() != null) {
            dto.setTrainingDate(entity.getTrainingDate());
        }
        if (entity.getTrainingTime() != null) {
            dto.setTrainingTime(entity.getTrainingTime());
        }
        if (entity.getTravelledDistance() != null) {
            dto.setTravelledDistance(entity.getTravelledDistance());
        }
        if (entity.getBurnedCalories() != null) {
            dto.setBurnedCalories(entity.getBurnedCalories());
        }
        if (entity.getComment() != null) {
            dto.setComment(entity.getComment());
        }
        return dto;
    }

    @Override
    public Training mapCreateDtoToEntity(TrainingCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Training entity = new Training();
        if (dto.getTrainingDate() != null) {
            entity.setTrainingDate(dto.getTrainingDate());
        }
        if (dto.getTrainingTime() != null) {
            entity.setTrainingTime(dto.getTrainingTime());
        }
        if (dto.getTravelledDistance() != null) {
            entity.setTravelledDistance(dto.getTravelledDistance());
        }
        if (dto.getBurnedCalories() != null) {
            entity.setBurnedCalories(dto.getBurnedCalories());
        }
        if (dto.getComment() != null) {
            entity.setComment(dto.getComment());
        }
        return entity;
    }
}
