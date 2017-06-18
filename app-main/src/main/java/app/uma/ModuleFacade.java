package app.uma;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.uma.model.ItemModel;
import app.uma.model.ModelBase;
import app.uma.model.PackModel;
import app.uma.model.UserModel;
import app.uma.net.socket.interfaces.IModuleFacade;

public class ModuleFacade implements IModuleFacade {

	private static final Logger log = LoggerFactory.getLogger(ModuleFacade.class);
	private ArrayList<ModelBase> models;
	
	public ModuleFacade() {
		models = new ArrayList<>();
	}
	
	@Autowired
	ItemModel itemModel;
	
	@Autowired
	PackModel packModel;
	
	@Autowired
	UserModel userModel;
	
	@Override
	public void start() {
		models.add(userModel);
		models.add(itemModel);
		models.add(packModel);
		
		for (ModelBase model:models) {
			model.initCfgs();
		}
		
		for(ModelBase model : models){
			model.registProsesser();
		}
	}

}
