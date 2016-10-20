package io.spring.cloud.samples.commerce.ui.services.items;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableConfigurationProperties(ItemProperties.class)
public class ItemService {

	
    @Autowired
    ItemProperties itemProperties;

    @Autowired
    RestTemplate restTemplate1;
	
	@Autowired
    RestTemplate restTemplate2;

	@Autowired
    RestTemplate restTemplate3;
	
	@Autowired
    RestTemplate restTemplate4;
	
	@Autowired
    RestTemplate restTemplate5;
	
	@Autowired
    RestTemplate restTemplate6;

    @HystrixCommand(fallbackMethod = "fallbackFortune")
   public String allItems() {
        Item[] myitem = restTemplate1.getForObject("http://item/items", Item[].class);
		String pricestring = restTemplate2.getForObject("http://price/prices", String.class);
        String[] pricelist = pricestring.split(",");
        String newline = System.getProperty("line.separator");
		StringBuilder entirelist = new StringBuilder("");
		for (int i=0; i<myitem.length; i++) {
			String[] fields = pricelist[i].split(":");
			entirelist.append(myitem[i].toString() + "    Price of " + myitem[i].getName() + ": " + fields[1] + "       " + newline);
		}
		return entirelist.toString();
		//Item[] myitem = restTemplate1.getForObject("http://item/items", Item[].class);
		//String entirelist = new String("");
		//String url = new String("http://price/prices/" + Long.toString(myitem[0].getId()));
		//String str = restTemplate2.getForObject(url, String.class);
		//ArrayList<Item> myitems = new ArrayList<Item>();
		//myitems = stringitemlist(str);
		//return (myitem[0].toString() + str);
    }

    private String fallbackFortune() {
        return itemProperties.getRandomItemFromProperty();
    }
    
    public String itemsbycategory(String category) {
        Item[] myitem = restTemplate3.getForObject("http://item/items", Item[].class);
		String pricestring = restTemplate4.getForObject("http://price/prices", String.class);
        String[] pricelist = pricestring.split(",");
        String newline = System.getProperty("line.separator");
		StringBuilder entirelist = new StringBuilder("");
		for (int i=0; i<myitem.length; i++) {
			if (myitem[i].getCategory().equals(category)) {
				String[] fields = pricelist[i].split(":");
				entirelist.append(myitem[i].toString() + "    Price of " + myitem[i].getName() + ": " + fields[1] + "       " + newline);
			}
		}
		return entirelist.toString();
	}
	
	public String itemsbyId(String id) {
        Item[] myitem = restTemplate5.getForObject("http://item/items", Item[].class);
		String pricestring = restTemplate6.getForObject("http://price/prices", String.class);
        String[] pricelist = pricestring.split(",");
        String newline = System.getProperty("line.separator");
		StringBuilder entirelist = new StringBuilder("");
		for (int i=0; i<myitem.length; i++) {
			if (Long.toString(myitem[i].getId()).equals(id)) {
				String[] fields = pricelist[i].split(":");
				entirelist.append(myitem[i].toString() + "    Price of " + myitem[i].getName() + ": " + fields[1] + "       " + newline);
			}
		}
		return entirelist.toString();
	}
	
    public ArrayList<Item> stringitemlist(String str) {
    	ArrayList<Item> myitems = new ArrayList<Item>();
    	int len = str.length();
    	for (int i=0; i<len; i++) {
    		int start = -1;
    		int end = -1;
    		if (str.charAt(i) == '{') {
    			start = i;
    		}
    		if (str.charAt(i) == '}') {
    			end = i;
    		}
    		if (start > 0 && end > 0) {
    			myitems.add(new Item(str.substring(start+1, end)));
    		}
    		end = -1;
    	}
    	return myitems;
    }
}