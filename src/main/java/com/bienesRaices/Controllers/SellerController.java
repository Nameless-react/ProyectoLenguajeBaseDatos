/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Controllers;

import com.bienesRaices.Domain.Agent;
import com.bienesRaices.Domain.Users;
import com.bienesRaices.Services.AgentService;
import com.bienesRaices.Services.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author arjoz
 */
@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private UserService userService;

    @GetMapping("/sellerList")
    public String sellerList(Model model) {

        List<Users> allUsers = userService.getUsers();
        List<Agent> allAgents = agentService.getAgents();

        List<Users> usersWithoutAgent = new ArrayList<>();
        for (Users user : allUsers) {
            boolean hasAgent = false;
            for (Agent agent : allAgents) {
                if (agent.getUser().getIdUser().equals(user.getIdUser())) {
                    hasAgent = true;
                    break;
                }
            }

            if (!hasAgent) {
                usersWithoutAgent.add(user);
            }
        }
        model.addAttribute("agents", allAgents);
        model.addAttribute("usersWithoutAgent", usersWithoutAgent);
        return "/seller/list";
    }

    @GetMapping("/new")
    public String newSeller(Agent agent, Model model) {
        var users = userService.getUsers();
        model.addAttribute("users", users);
        return "seller/modify";
    }

    @PostMapping("/save")
    public String SellerSave(@ModelAttribute("agent") Agent agent, @RequestParam("user.idUser") Long userId) {
        Users user = userService.findById(userId);
        agent.setUser(user);
        if (agent.getHireDate() == null) {
            // Recuperar la fecha de contratación original de la base de datos
            Agent originalAgent = agentService.getAgentId(agent.getIdAgent());
            agent.setHireDate(originalAgent.getHireDate());
        }
        agentService.save(agent);
        return "redirect:/seller/sellerList";
    }

    @GetMapping("/modify/{idAgent}")
    public String modifyAgent(Agent agent, Model model) {
        agent = agentService.getAgent(agent);
        model.addAttribute("agent", agent);
        return "/seller/modify";
    }
    
    @GetMapping("/delete/{idAgent}")
    public String DeleteAgent (Agent agent){
        agentService.delete(agent);
        return "redirect:/seller/sellerList";
    }

}
