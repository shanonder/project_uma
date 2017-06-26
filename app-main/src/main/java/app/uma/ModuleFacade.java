package app.uma;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.factory.Ifactory;
import app.uma.factory.ItemFactory;
import app.uma.model.ItemModel;
import app.uma.model.MapModel;
import app.uma.model.ModelBase;
import app.uma.model.PackModel;
import app.uma.model.RoleModel;
import app.uma.model.UserModel;
import app.uma.net.socket.interfaces.IModuleFacade;

public class ModuleFacade implements IModuleFacade {

	private static final Logger log = LoggerFactory.getLogger(ModuleFacade.class);
	private ArrayList<Class<Ifactory>> factorys;
	private ArrayList<Class<ModelBase>> models;
	public ModuleFacade() {
		factorys = new ArrayList<>();
		models = new ArrayList<>();
	}
	
	@Override
	public void start() {
		registModels();
		for (Class<Ifactory> cls:factorys) {
			Application.context.getBean(cls).initCfgs();
		}
		
		for (Class<ModelBase> cls:models) {
			Application.context.getBean(cls).startup();
		}
	}
	
	
//	
	@SuppressWarnings("unchecked")
	private void addModel(Class<? extends ModelBase> cls){
		models.add((Class<ModelBase>) cls);
		log.info("model regist success: " + cls.getName());
	}
	@SuppressWarnings("unchecked")
	private void addFactory(Class<? extends Ifactory> cls){
		factorys.add((Class<Ifactory>) cls);
		log.info("factory regist success: " + cls.getName());
	}


	private void registModels() {
		addModel(UserModel.class);
		addModel(RoleModel.class);
		addModel(ItemModel.class);
		addModel(PackModel.class);
		addModel(MapModel.class);
		addFactory(ItemFactory.class);
	}

}
