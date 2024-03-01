/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author arjoz
 */
@Controller
@RequestMapping("/seller")
public class sellerController {
      @GetMapping("/sellerList")
    public String sellerList (){
     return "/seller/list";
    }
}
