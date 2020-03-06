/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Group15.PollutionBackend.Model;

import java.util.ArrayList;


/**
 *
 * @author Andrew Wright
 */
public class CountryCodes
{

    private static ArrayList<String> isoCodes = new ArrayList<>();

    public static void addCode(String code)
    {
        isoCodes.add(code);
    }

    public static ArrayList<String> getIsoCodes() {
        return isoCodes;
    }
    
    
    
    
    
    
    
}
