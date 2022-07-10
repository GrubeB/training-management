package pl.dawid.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dawid.app.exception.InvalidStateException;
import pl.dawid.app.exception.ResourceNotFoundException;
import pl.dawid.app.exception.ValidationException;
import pl.dawid.app.model.Training;
import pl.dawid.app.repository.TrainingRepository;
import pl.dawid.app.validators.DataValidators;
import pl.dawid.app.validators.NumberValidators;
import pl.dawid.app.validators.TimeValidators;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService{

    private TrainingRepository trainingRepository;

    @Override
    public Training fetchTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Training not found with id: "+id));
    }

    @Override
    public List<Training> fetchTrainingList() {
        return trainingRepository.findAll();
    }

    @Override
    public Training saveTraining(Training training) throws ValidationException {
        DataValidators.validateDateIsBeforeOrEqual(training.getTrainingDate(), Date.valueOf(LocalDate.now()),"Date of training");
        TimeValidators.validateTimeIsNotNull(training.getTrainingTime(),"Time of training");
        NumberValidators.validateNumberIsPositiveOrZero(training.getTravelledDistance(),"Number of traveled distance");
        NumberValidators.validateNumberIsPositiveOrZero(training.getBurnedCalories(),"Number of burned calories");
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, Training training) throws ValidationException {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Training not found with id: "+id));
        DataValidators.validateDateIsBeforeOrEqual(training.getTrainingDate(), Date.valueOf(LocalDate.now()),"Date of training");
        TimeValidators.validateTimeIsNotNull(training.getTrainingTime(),"Time of training");
        NumberValidators.validateNumberIsPositiveOrZero(training.getTravelledDistance(),"Number of traveled distance");
        NumberValidators.validateNumberIsPositiveOrZero(training.getBurnedCalories(),"Number of burned calories");

        existingTraining.setTrainingDate(training.getTrainingDate());
        existingTraining.setTrainingTime(training.getTrainingTime());
        existingTraining.setTravelledDistance(training.getTravelledDistance());
        existingTraining.setBurnedCalories(training.getBurnedCalories());
        existingTraining.setComment(training.getComment());
        trainingRepository.save(existingTraining);
        return existingTraining;
    }

    @Override
    public void deleteTrainingById(Long id) {
        trainingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Training not found with id: "+id));
        trainingRepository.deleteById(id);
    }

    @Override
    public Double getAverageSpeed(Training training) throws InvalidStateException {
        if (training == null ) {
            throw new  ResourceNotFoundException("Training not found");
        }
        return training.getAverageSpeed().orElseThrow(()->new InvalidStateException("Time can not be null or zero"));
    }
}
