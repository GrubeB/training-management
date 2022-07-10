package pl.dawid.app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import pl.dawid.app.exception.InvalidStateException;
import pl.dawid.app.exception.ResourceNotFoundException;
import pl.dawid.app.model.Training;
import pl.dawid.app.repository.TrainingRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles({"test"})
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TrainingServiceImplTest {

    @Autowired
    private TrainingServiceImpl trainingService;

    @MockBean
    private TrainingRepository trainingRepository;

    private Training trainingBeforeSave = Training
            .builder()
            .trainingDate(Date.valueOf(LocalDate.now().minusDays(1)))
            .trainingTime(Time.valueOf(LocalTime.of(0, 50)))
            .travelledDistance(5.0)
            .burnedCalories(320)
            .comment("Im out of breath")
            .build();

    private Training trainingAfterSave = Training
            .builder()
            .id(1L)
            .trainingDate(Date.valueOf(LocalDate.now().minusDays(1)))
            .trainingTime(Time.valueOf(LocalTime.of(0, 50)))
            .travelledDistance(5.0)
            .burnedCalories(320)
            .comment("Im out of breath")
            .build();

    @Test
    public void whenFindByExistingId_thenTrainingShouldFound() {
        Mockito.when(trainingRepository.findById(1L)).thenReturn(Optional.ofNullable(trainingAfterSave));
        Training training = trainingService.fetchTrainingById(1L);
        assertEquals(training, trainingAfterSave);
    }

    @Test
    public void whenFindByNoExistingId_thenThrowError() {
        Mockito.when(trainingRepository.findById(any())).thenReturn(Optional.empty());
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            Training training = trainingService.fetchTrainingById(1L);
        });
        assertEquals(thrown.getMessage(), "Training not found with id: 1");
    }

    @Test
    public void whenFindAll_thenReturnNotEmptyList() {
        Mockito.when(trainingRepository.findAll()).thenReturn(Arrays.asList(trainingAfterSave));
        List<Training> trainingList = trainingService.fetchTrainingList();
        assertTrue(trainingList.size() > 0);
    }

    @Test
    public void whenValidSave_thenReturnSameObjectButWithId() {
        Mockito.when(trainingRepository.save(any())).thenReturn(trainingAfterSave);
        Training training = trainingService.saveTraining(trainingBeforeSave);
        assertNotEquals(training.getId(), trainingBeforeSave.getId());
        assertEquals(training.getComment(), trainingBeforeSave.getComment());
    }

    @Test
    public void whenValidUpdate_thenReturnUpdatedObject() {
        Training updatingTraining = Training
                .builder()
                .id(1L)
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(10.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        Training newTraining = Training
                .builder()
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(1000.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        Training updatedTraining = Training
                .builder()
                .id(1L)
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(1000.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        Mockito.when(trainingRepository.findById(any())).thenReturn(Optional.ofNullable(updatingTraining));
        Mockito.when(trainingRepository.save(any())).thenReturn(updatedTraining);
        Training training = trainingService.updateTraining(1L, newTraining);
        assertEquals(training, updatedTraining);
    }

    @Test
    public void whenUpdateWithNoExistingId_thenThrowError() {
        Training updatingTraining = Training
                .builder()
                .id(1L)
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(10.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        Training newTraining = Training
                .builder()
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(1000.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        Training updatedTraining = Training
                .builder()
                .id(1L)
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(1000.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        Mockito.when(trainingRepository.findById(any())).thenReturn(Optional.empty());
        Mockito.when(trainingRepository.save(any())).thenReturn(updatedTraining);
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            Training training = trainingService.updateTraining(1L, newTraining);
        });
        assertEquals(thrown.getMessage(), "Training not found with id: 1");
    }

    @Test
    public void whenGetAverageSpeedWithValidTraining_thenReturnDouble() {
        Double averageSpeed = trainingService.getAverageSpeed(trainingAfterSave);
        assertEquals(averageSpeed, 6.0);
    }

    @Test
    public void whenGetAverageSpeedWithNullTraining_thenThrowError() {
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            Double averageSpeed = trainingService.getAverageSpeed(null);
        });
        assertEquals(thrown.getMessage(), "Training not found");
    }

    @Test
    public void whenGetAverageSpeedWithTimeEqualZero_thenThrowError() {
        Training training = Training
                .builder()
                .trainingDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .trainingTime(Time.valueOf(LocalTime.of(0, 0, 0, 0)))
                .travelledDistance(5.0)
                .burnedCalories(320)
                .comment("Im out of breath")
                .build();
        InvalidStateException thrown = assertThrows(InvalidStateException.class, () -> {
            Double averageSpeed = trainingService.getAverageSpeed(training);
        });
        assertEquals(thrown.getMessage(), "Time can not be null or zero");
    }
}