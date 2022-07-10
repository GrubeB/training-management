package pl.dawid.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.dawid.app.dto.TrainingBaseDTO;
import pl.dawid.app.dto.TrainingCreateDTO;
import pl.dawid.app.dto.TrainingUpdateDTO;
import pl.dawid.app.exception.ValidationException;
import pl.dawid.app.mapper.TrainingMapperImpl;
import pl.dawid.app.model.Training;
import pl.dawid.app.service.TrainingServiceImpl;

import java.util.List;

@Controller
public class TrainingController {

    @Autowired
    private TrainingServiceImpl trainingService;

    @Autowired
    private TrainingMapperImpl trainingMapper;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping({"/trainings", "/trainings/show_list"})
    public ModelAndView showTrainingListTemplate() {
        ModelAndView modelAndView = new ModelAndView("training/show-training-list");
        List<TrainingBaseDTO> trainingBaseDTOList = trainingMapper.mapEntityListToBaseDtoList(trainingService.fetchTrainingList());
        modelAndView.addObject("training_list", trainingBaseDTOList);
        return modelAndView;
    }

    //add
    @GetMapping("/trainings/add")
    public ModelAndView getCreateTrainingTemplate() {
        ModelAndView modelAndView = new ModelAndView("training/add-training");
        TrainingCreateDTO trainingCreateDTO = new TrainingCreateDTO();
        modelAndView.addObject("training", trainingCreateDTO);
        return modelAndView;
    }

    @PostMapping("/trainings/add")
    public ModelAndView createTraining(@ModelAttribute TrainingCreateDTO trainingCreateDTO, BindingResult result) {
        try {
            Training training = trainingMapper.mapCreateDtoToEntity(trainingCreateDTO);
            trainingService.saveTraining(training);
        } catch (ValidationException e) {
            ModelAndView modelAndView = new ModelAndView("training/add-training");
            modelAndView.addObject("training", trainingCreateDTO);
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/trainings/show_list");
        }
        return new ModelAndView("redirect:/trainings/show_list");
    }

    //update
    @GetMapping("/trainings/update/{id}")
    public ModelAndView getUpdateTrainingTemplate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("training/update-training");
        TrainingUpdateDTO trainingUpdateDTO = trainingMapper.mapEntityToUpdateDto(trainingService.fetchTrainingById(id));
        modelAndView.addObject("training", trainingUpdateDTO);
        return modelAndView;
    }

    @PostMapping("/trainings/update/{id}")
    public ModelAndView updateTraining(@ModelAttribute TrainingUpdateDTO trainingUpdateDTO, @PathVariable Long id) {
        try {
            Training training = trainingMapper.mapUpdateDtoToEntity(trainingUpdateDTO);
            trainingService.updateTraining(id, training);
        } catch (ValidationException e) {
            ModelAndView modelAndView = new ModelAndView("training/update-training");
            modelAndView.addObject("training", trainingUpdateDTO);
            modelAndView.addObject("error", e.getMessage());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/trainings/show_list"); //TODO
        }

        return new ModelAndView("redirect:/trainings/show_list");
    }

    //delete
    @GetMapping("/trainings/delete/{id}")
    public ModelAndView deleteTrainingTemplate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("training/delete-training");
        Training training = trainingService.fetchTrainingById(id);
        modelAndView.addObject("training_id", training.getId());
        return modelAndView;
    }

    @PostMapping("/trainings/delete/{id}")
    public ModelAndView deleteTraining(@PathVariable Long id) {
        trainingService.deleteTrainingById(id);
        return new ModelAndView("redirect:/trainings/show_list");
    }
}
