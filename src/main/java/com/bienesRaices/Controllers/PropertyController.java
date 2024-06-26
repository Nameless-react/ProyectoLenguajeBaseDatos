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
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

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
    private ContactService contactService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private FireBaseStorageService fireBaseStorageService;

    @Autowired
    private ImagePropertyService imagePropertyService;


    @GetMapping("/list")
    public String list(
            @Param("palabraClave") String palabraClave,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Property> properties = propertyService.getProperties(palabraClave, page, size);
        model.addAttribute("properties", properties);
        model.addAttribute("palabraClave", palabraClave);
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


        Agent agent = agentService.getAgentId(property.getAgent().getIdAgent());
        property.setAgent(agent);

        property = propertyService.save(property);

        for (MultipartFile image : images) {
            ImageProperty imageProperty = new ImageProperty(
                    property.getIdProperty(),
                    fireBaseStorageService.loadImage(image, "/properties", property.getPrice().longValue() + LocalDateTime.now().getNano())
            );
            System.out.println(imageProperty);
            imagePropertyService.save(imageProperty.getIdProperty(), imageProperty.getImage());
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
        Agent agent = agentService.getAgentId(property.getAgent().getIdAgent());
        property.setAgent(agent);




        for (MultipartFile image : images) {
            ImageProperty imageProperty = new ImageProperty(
                    property.getIdProperty(),
                    fireBaseStorageService.loadImage(image, "/properties", property.getPrice().longValue() + LocalDateTime.now().getNano())
            );
            imagePropertyService.save(imageProperty.getIdProperty(), imageProperty.getImage());
        }
        List<ImageProperty> savedImages = imagePropertyService.getImagesProperty(property.getIdProperty());

        property.setImages(savedImages);


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

    @GetMapping("/contact/{idProperty}")
    public String contact(Property property, Model model) {
        model.addAttribute("idProperty", property.getIdProperty());

        return "/properties/contact";
    }

    @PostMapping("/contact/save")
    public String contactSave(Contact contact, Model model) {
        contactService.save(contact);
        return "redirect:/";
    }
}
