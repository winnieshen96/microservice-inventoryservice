package com.microservice.project4.inventoryservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.project4.inventoryservice.domain.Good;

@RestController
public class inventoryServiceController {

    private static Map<String, List<Good>> inventoryDB = new HashMap<String, List<Good>>();
    
    static {
    	//hardcoding a database for storing inventory goods
        inventoryDB = new HashMap<String, List<Good>>();
 
        //create a fruit shop, which has two goods, apple and banana
        List<Good> lst = new ArrayList<Good>();
        Good g = new Good("Apple", 2);
        lst.add(g);
        g = new Good("Banana", 1);
        lst.add(g);
 
        //add the goods into the shop's inventory list
        inventoryDB.put("fruitshop", lst);
 
        //create a clothes shop, which has Tshirt and dress
        lst = new ArrayList<Good>();
        g = new Good("Tshirt", 10);
        lst.add(g);
        g = new Good("Dress", 20);
        lst.add(g);
 
        //add the goods into the shop's inventory list
        inventoryDB.put("clothesshop", lst);
 
    }
 
    //The annotation of RequestMapping can map HTTP request such as 
    //"http://host:port/getInventoryForShop/fruitShop" to this method
    @RequestMapping(value = "/getInventoryForShop/{shopName}", method = RequestMethod.GET)
    public List<Good> getInventory(@PathVariable String shopName) {
        System.out.println("Getting inventory details for " + shopName);
 
        List<Good> inventoryList = inventoryDB.get(shopName.toLowerCase());
        //if the shop name does not exist in the database
        if (inventoryList == null) {
        	inventoryList = new ArrayList<Good>();
            Good std = new Good("Not Found", 0);
            inventoryList.add(std);
        }
        return inventoryList;
    }
	
}
