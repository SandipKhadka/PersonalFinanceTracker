package org.app.Controller;

import java.util.List;

import org.app.model.DataModel;
import org.app.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class DataController {
    @Autowired
    HttpSession session;

    @Autowired
    DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/welcome")
    public ModelAndView shoData() {
        ModelAndView modelAndView = new ModelAndView();
        List<DataModel> dataModels = dataService.getDataService();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("user", dataModels);
        return modelAndView;
    }
}
