/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bienesRaices.Domain;

import java.util.Random;

public class RandomIdGenerator {

    private static final int ID_LENGTH = 18; // Longitud del identificador en dígitos
    private static final Random random = new Random();

    public static String generateRandomId() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            sb.append(random.nextInt(10)); // Genera un dígito aleatorio entre 0 y 9
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String randomId = generateRandomId();
        System.out.println("ID aleatorio generado: " + randomId);
    }
}