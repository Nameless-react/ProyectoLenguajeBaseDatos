package com.bienesRaices.Controllers;

import com.bienesRaices.Services.AddressService;
import com.bienesRaices.Services.AgentService;
import com.bienesRaices.Services.CharacteristicsService;
import com.bienesRaices.Services.ContactService;
import com.bienesRaices.Services.FireBaseStorageService;
import com.bienesRaices.Services.ImagePropertyService;
import com.bienesRaices.Services.PropertyService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    

  


    @GetMapping("/")
    public String index(Model model) {
        
        return "/index";
    }   

    @GetMapping("/contact")
    public String contact(Model model) {
        return "/contact";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        return "/aboutUs";
    }

}
