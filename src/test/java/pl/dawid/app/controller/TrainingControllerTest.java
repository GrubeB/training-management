package pl.dawid.app.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.ModelAndView;
import pl.dawid.app.dto.TrainingCreateDTO;
import pl.dawid.app.dto.TrainingUpdateDTO;
import pl.dawid.app.exception.ValidationException;
import pl.dawid.app.mapper.TrainingMapper;
import pl.dawid.app.model.Training;
import pl.dawid.app.service.TrainingServiceImpl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test"})
class TrainingControllerTest {

    @MockBean
    private TrainingServiceImpl trainingService;

    @Autowired
    private TrainingController trainingController;

    @Autowired
    TrainingMapper mapper;

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
    void whenGetIndex_thenReturnIndexTemplate() {
        ModelAndView modelAndView = trainingController.index();
        Assertions.assertEquals(modelAndView.getViewName(), "index");
    }

    @Test
    void whenGetTrainingListTemplate_thenReturnTemplate() {
        given(trainingService.fetchTrainingList()).willReturn(Collections.singletonList(trainingAfterSave));
        ModelAndView modelAndView = trainingController.showTrainingListTemplate();

        Assertions.assertEquals(modelAndView.getViewName(), "training/show-training-list");
        Assertions.assertTrue(modelAndView.getModel().containsKey("training_list"));
    }

    //add
    @Test
    void whenGetCreateTrainingTemplate_thenReturnCreateTrainingTemplateWithTrainingCreateDTO() {
        ModelAndView modelAndView = trainingController.getCreateTrainingTemplate();

        Assertions.assertEquals(modelAndView.getViewName(), "training/add-training");
        Assertions.assertTrue(modelAndView.getModel().containsKey("training"));
        Assertions.assertEquals(modelAndView.getModel().get("training").getClass(),
                TrainingCreateDTO.class);
    }

    @Test
    void whenCreateTrainingWithValidObject_thenReturnTrainingListTemplate() {
        given(trainingService.saveTraining(any())).willReturn(trainingAfterSave);
        TrainingCreateDTO trainingCreateDTO = new TrainingCreateDTO();
        trainingCreateDTO.setTrainingDate(Date.valueOf("2000-02-1"));
        trainingCreateDTO.setTrainingTime(Time.valueOf("12:12:12"));
        trainingCreateDTO.setTravelledDistance(12.7);
        trainingCreateDTO.setBurnedCalories(12);
        trainingCreateDTO.setComment("Comment");
        ModelAndView modelAndView = trainingController.createTraining(trainingCreateDTO, null);

        Assertions.assertEquals(modelAndView.getViewName(), "redirect:/trainings/show_list");
    }

    @Test
    void whenCreateTrainingWithNotValidObject_thenReturnCreateTrainingTemplateWithTrainingCreateDTO() {
        given(trainingService.saveTraining(any())).willThrow(ValidationException.class);
        TrainingCreateDTO trainingCreateDTO = new TrainingCreateDTO();
        trainingCreateDTO.setTrainingDate(Date.valueOf("9999-09-9"));
        trainingCreateDTO.setTrainingTime(Time.valueOf("12:12:12"));
        trainingCreateDTO.setTravelledDistance(12.7);
        trainingCreateDTO.setBurnedCalories(12);
        trainingCreateDTO.setComment("Comment");
        ModelAndView modelAndView = trainingController.createTraining(trainingCreateDTO, null);

        Assertions.assertEquals(modelAndView.getViewName(), "training/add-training");
        Assertions.assertTrue(modelAndView.getModel().containsKey("training"));
        Assertions.assertEquals(modelAndView.getModel().get("training").getClass(),
                TrainingCreateDTO.class);
    }

    //update
    @Test
    void whenGetUpdateTrainingTemplate_thenReturnUpdateTrainingTemplateWithTrainingUpdateDTO() {
        given(trainingService.fetchTrainingById(any())).willReturn(trainingAfterSave);
        ModelAndView modelAndView = trainingController.getUpdateTrainingTemplate(1L);

        Assertions.assertEquals(modelAndView.getViewName(), "training/update-training");
        Assertions.assertTrue(modelAndView.getModel().containsKey("training"));
        Assertions.assertEquals(modelAndView.getModel().get("training").getClass(),
                TrainingUpdateDTO.class);
    }

    @Test
    void whenUpdateTrainingWithValidObject_thenReturnTrainingListTemplate() {
        given(trainingService.updateTraining(any(), any())).willReturn(trainingAfterSave);
        TrainingUpdateDTO trainingUpdateDTO = new TrainingUpdateDTO();
        trainingUpdateDTO.setId(1L);
        trainingUpdateDTO.setTrainingDate(Date.valueOf("2000-02-1"));
        trainingUpdateDTO.setTrainingTime(Time.valueOf("12:12:12"));
        trainingUpdateDTO.setTravelledDistance(12.7);
        trainingUpdateDTO.setBurnedCalories(12);
        trainingUpdateDTO.setComment("Comment");

        ModelAndView modelAndView = trainingController.updateTraining(trainingUpdateDTO, 1L);
        Assertions.assertEquals(modelAndView.getViewName(), "redirect:/trainings/show_list");
    }

    @Test
    void whenUpdateTrainingWithNotValidObject_thenReturnUpdateTrainingTemplateWithTrainingCreateDTO() {
        given(trainingService.updateTraining(any(), any())).willThrow(ValidationException.class);
        TrainingUpdateDTO trainingUpdateDTO = new TrainingUpdateDTO();
        trainingUpdateDTO.setId(1L);
        trainingUpdateDTO.setTrainingDate(Date.valueOf("2000-02-1"));
        trainingUpdateDTO.setTrainingTime(Time.valueOf("12:12:12"));
        trainingUpdateDTO.setTravelledDistance(12.7);
        trainingUpdateDTO.setBurnedCalories(12);
        trainingUpdateDTO.setComment("Comment");
        ModelAndView modelAndView = trainingController.updateTraining(trainingUpdateDTO, 9291L);

        Assertions.assertEquals(modelAndView.getViewName(), "training/update-training");
        Assertions.assertTrue(modelAndView.getModel().containsKey("training"));
        Assertions.assertEquals(modelAndView.getModel().get("training").getClass(),
                TrainingUpdateDTO.class);
    }

    //delete
    @Test
    void whenGetDeleteTrainingTemplate_thenReturnDeleteTrainingTemplateWithTrainingId() {
        given(trainingService.fetchTrainingById(any())).willReturn(trainingAfterSave);
        ModelAndView modelAndView = trainingController.deleteTrainingTemplate(1L);

        Assertions.assertEquals(modelAndView.getViewName(), "training/delete-training");
        Assertions.assertTrue(modelAndView.getModel().containsKey("training_id"));
    }

    @Test
    void whenDeleteTrainingWithValidId_thenReturnTrainingListTemplate() {
        ModelAndView modelAndView = trainingController.deleteTraining(1L);

        Assertions.assertEquals(modelAndView.getViewName(), "redirect:/trainings/show_list");
    }
}
