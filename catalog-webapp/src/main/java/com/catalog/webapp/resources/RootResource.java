package com.catalog.webapp.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Root resource to direct defaults
 * Created by Lance on 04/04/2015.
 */
@RestController
public class RootResource {

    public static final String API_URL = "/rest";

    @RequestMapping({"", "home", "index"})
    public ModelAndView showHomePage() {
        return new ModelAndView("index");
    }
}
