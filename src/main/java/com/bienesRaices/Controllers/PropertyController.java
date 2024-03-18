package com.bienesRaices.Controllers;

import com.bienesRaices.Domain.*;
import com.bienesRaices.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CharacteristicsService characteristicsService;


    @Autowired
    private AgentService agentService;

    @Autowired
    private FireBaseStorageService fireBaseStorageService;

    @Autowired
    private ImagePropertyService imagePropertyService;

//    @Autowired
//    private UserServic user;

    @GetMapping("/list")
    public String list(Model model) {
        List<Property> properties = propertyService.getProperties();
        model.addAttribute("properties", properties);
        return "/properties/list";
    }

    @GetMapping("/new")
    public String newElement(Property property, Model model) {
        model.addAttribute("agents", agentService.getAgents());
        return "/properties/new";
    }

    @PostMapping("/save")
    public String save(Property property, Model model, @RequestParam("imageFile") MultipartFile[] images) {
        Address address = addressService.save(property.getAddress());
        property.setAddress(address);

        Characteristics characteristics = characteristicsService.save(property.getCharacteristics());
        property.setCharacteristics(characteristics);


        Agent agent = agentService.getAgent(property.getAgent().getIdAgent());
        property.setAgent(agent);


        property = propertyService.save(property);

        for (MultipartFile image : images) {
            ImageProperty imageProperty = new ImageProperty(
                    property.getIdProperty(),
                    fireBaseStorageService.loadImage(image, "/properties", property.getPrice().longValue() + LocalDateTime.now().getNano())
            );
            System.out.println(imageProperty);
            imagePropertyService.save(imageProperty);
        }

        return "redirect:/properties/list";
    }

    @GetMapping("/update/{idProperty}")
    public String update(Property property, Model model) {
        property = propertyService.getProperty(property.getIdProperty());
        model.addAttribute("property", property);
        model.addAttribute("agents", agentService.getAgents());
        return "/properties/update";
    }


    @PostMapping("/update/")
    public String updatePut(Property property, Model model, @RequestParam("imageFile") MultipartFile[] images) {
        Agent agent = agentService.getAgent(property.getAgent().getIdAgent());
        property.setAgent(agent);




        for (MultipartFile image : images) {
            ImageProperty imageProperty = new ImageProperty(
                    property.getIdProperty(),
                    fireBaseStorageService.loadImage(image, "/properties", property.getPrice().longValue() + LocalDateTime.now().getNano())
            );
            imagePropertyService.save(imageProperty);
        }
        List<ImageProperty> savedImages = imagePropertyService.getImagesProperty(property.getIdProperty());

        property.setImages(savedImages);
        System.out.println(property);
        System.out.println(images.length);

        addressService.save(property.getAddress());
        characteristicsService.save(property.getCharacteristics());




        propertyService.save(property);
        return "redirect:/properties/list";
    }
    @DeleteMapping("/{idProperty}")
    public void delete(Property property, Model model) {
        propertyService.delete(property.getIdProperty());
    }


    @GetMapping("/{idProperty}")
    public String getOne(Property property, Model model) {
        property = propertyService.getProperty(property.getIdProperty());
        model.addAttribute("property", property);
        model.addAttribute("propertyLength", property.getImages().size());
        return "/properties/property";
    }
}
