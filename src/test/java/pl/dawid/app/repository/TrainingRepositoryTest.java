package pl.dawid.app.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dawid.app.model.Training;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles({"test"})
@DataJpaTest
@RunWith(SpringRunner.class)
class TrainingRepositoryTest {

    @Autowired
    private TrainingRepository trainingRepository;

    private Training training1 = Training
            .builder()
            .trainingDate(Date.valueOf(LocalDate.now().minusDays(1)))
            .trainingTime(Time.valueOf(LocalTime.of(0, 50)))
            .travelledDistance(5.0)
            .burnedCalories(320)
            .comment("Im out of breath")
            .build();
    private Training training2 = Training
            .builder()
            .trainingDate(Date.valueOf(LocalDate.now()))
            .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
            .travelledDistance(10.0)
            .burnedCalories(630)
            .comment("It was great!")
            .build();

    @BeforeEach
    void setUp() {
        trainingRepository.save(training1);
        trainingRepository.save(training2);
        trainingRepository.flush();
    }

    @AfterEach
    void clearAll() {
        trainingRepository.deleteAll();
    }

    @Test
    public void whenFindById_thenReturnTraining() {
        Training training = trainingRepository.findById(1L).get();
        assertEquals(training.getId(), 1L);
        assertTrue(training.getTrainingDate().compareTo(Date.valueOf(LocalDate.now())) <= 0);
        assertEquals(training.getTravelledDistance(), 5);
    }

    @Test
    public void whenFindAll_thenReturnTwoOrMore() {
        List<Training> trainingList = trainingRepository.findAll();
        assertTrue(trainingList.size() >= 2);
    }

    @Test
    public void whenSave_thenOneMoreInBase() {
        List<Training> trainingListBefore = trainingRepository.findAll();
        Training training = Training
                .builder()
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(10.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        trainingRepository.save(training);
        List<Training> trainingListAfter = trainingRepository.findAll();
        assertEquals(trainingListBefore.size(), trainingListAfter.size() - 1);
    }

    @Test
    public void whenUpdate_thenReturnUpdated() {
        List<Training> trainingListBefore = trainingRepository.findAll();
        Training training = Training
                .builder()
                .trainingDate(Date.valueOf(LocalDate.now()))
                .trainingTime(Time.valueOf(LocalTime.of(1, 30)))
                .travelledDistance(10.0)
                .burnedCalories(630)
                .comment("It was great!")
                .build();
        trainingRepository.save(training);
        List<Training> trainingListAfter = trainingRepository.findAll();
        assertEquals(trainingListBefore.size(), trainingListAfter.size() - 1);
    }
}