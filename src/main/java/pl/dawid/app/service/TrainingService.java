package pl.dawid.app.service;

import pl.dawid.app.model.Training;

import java.util.List;

public interface TrainingService {
    //get single
    public Training fetchTrainingById(Long id);

    //get list
    List<Training> fetchTrainingList();

    Double getAverageSpeed(Training training);

    // modification
    Training saveTraining(Training training);

    Training updateTraining(Long id, Training training);

    void deleteTrainingById(Long id);
}
