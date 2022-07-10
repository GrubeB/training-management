package pl.dawid.app.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dawid.app.exception.ResourceNotFoundException;
import pl.dawid.app.model.Training;
import pl.dawid.app.repository.TrainingRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {

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
    public Training saveTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, Training training) {
        Training existingTraining = trainingRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Training not found with id: "+id));

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
                .orElseThrow(() -> new ResourceNotFoundException("Training not found with id: "+id));
        trainingRepository.deleteById(id);
    }

    @Override
    public Double getAverageSpeed(Training training) {
        throw new IllegalArgumentException("Not implemented yet!");
    }
}
