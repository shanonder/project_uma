package app.uma;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.uma.model.ItemModel;
import app.uma.model.MapModel;
import app.uma.model.ModelBase;
import app.uma.model.PackModel;
import app.uma.model.RoleModel;
import app.uma.model.UserModel;
import app.uma.net.socket.interfaces.IModuleFacade;

public class ModuleFacade implements IModuleFacade {

	private static final Logger log = LoggerFactory.getLogger(ModuleFacade.class);
	private ArrayList<ModelBase> models;
	
	public ModuleFacade() {
		models = new ArrayList<>();
	}
	

	
	@Override
	public void start() {
		registModels();
		
		for (ModelBase model:models) {
			model.initCfgs();
		}
		
		for(ModelBase model : models){
			model.registProsesser();
		}
	}


	@Autowired
	UserModel userModel;
	
	@Autowired
	RoleModel roleModel;
	
	@Autowired
	ItemModel itemModel;
	
	@Autowired
	PackModel packModel;
	@Autowired
	MapModel mapModel;

	private void registModels() {
		models.add(userModel);
		models.add(roleModel);
		models.add(itemModel);
		models.add(packModel);
		models.add(mapModel);
	}

}
