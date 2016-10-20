package io.spring.cloud.samples.commerce.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import io.spring.cloud.samples.commerce.ui.services.items.Item;
import io.spring.cloud.samples.commerce.ui.services.items.ItemService;


@RestController
public class UiController {

    @Autowired
    ItemService service;

    @RequestMapping("/items")
    public String listItemsPrices() {
        return service.allItems();
    }
	
	@RequestMapping("/category/{cat}")
	public String categoryResult(@PathVariable("cat") String category) {
		return service.itemsbycategory(category);
	}
	
	@RequestMapping("/item/{id}")
	public String resultbyId(@PathVariable("id") String id) {
		return service.itemsbyId(id);
    }
}
