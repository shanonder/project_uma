package app.uma.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.uma.dao.entity.Item;
import app.uma.dao.repository.IItemRepository;

@Controller
@RequestMapping(path="/item")
public class ItemController {
	@Autowired // This means to get the bean called itemRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private IItemRepository repository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewItem (@RequestParam Integer sourceId
			) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Item item = new Item();
		item.setSourceId(sourceId);
		repository.save(item);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Item> getAllUsers() {
		// This returns a JSON or XML with the users
		return repository.findAll();
	}
	
}
