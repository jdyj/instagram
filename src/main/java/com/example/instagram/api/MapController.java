package com.example.instagram.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {


    @RequestMapping(value = "/map")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/map");
        mv.addObject("API_KEY", ApiConfig.GOOGLE_MAP);
        return mv;
    }


}
