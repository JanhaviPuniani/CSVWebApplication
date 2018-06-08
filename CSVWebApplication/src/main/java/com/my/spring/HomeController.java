package com.my.spring;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.my.spring.dao.CSVDao;
import com.my.spring.pojo.UserDetails;
import org.apache.commons.lang3.StringUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
		CSVDao csvdao = new CSVDao();
		ArrayList<UserDetails> list = csvdao.getContent("normal");
		ArrayList<String> listOfStrings = new ArrayList<String>();
		HashMap<Integer,ArrayList<String> > map = new HashMap();
		ArrayList<String> nonDuplicates = new ArrayList<String>();
		for(UserDetails u : list){
			listOfStrings.add(u.toString());
		}
		
		for (int i = 0; i < listOfStrings.size(); i++) {
			ArrayList<String> duplicates = new ArrayList<String>();
			int count = 0;
			for (int j = i+1; j < listOfStrings.size(); j++) {
				int dist = StringUtils.getLevenshteinDistance(listOfStrings.get(i), listOfStrings.get(j));

				if (dist < 7) {
					if (count == 0) {
						duplicates.add(listOfStrings.get(i));

					}
					duplicates.add(listOfStrings.get(j));
					count++;

				}
			}
			map.put(i, duplicates);
		}
		
		for(Entry<Integer, ArrayList<String>> e : map.entrySet()){
			
			if(e.getValue().size() > 1){
				System.out.println(e.getKey()+ "-->");
				for(String s : e.getValue()){
					System.out.println(s);
				}
				System.out.println("--------");
			}
			
		}
		
		
		nonDuplicates.addAll(listOfStrings);
		
			for(Entry<Integer, ArrayList<String>> e : map.entrySet()){
				
				nonDuplicates.removeAll(e.getValue());
			}
			System.out.println("Non-Duplicates");
			for(String s : nonDuplicates){
				System.out.println(s);
			}
			
	
		return "home";
	}
	
}
