package app.uma.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.factory.ItemFactory;

@Component
public class ItemModel extends ModelBase{
	
//	@Autowired
//	private IItemRepository itemRepository;


	@Override
	protected void initCfg(){
	}
	
	@Override
	public void registProsesser() {
		
	}
}
