package app.uma;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.base.Ifactory;
import app.uma.base.ModelBase;
import app.uma.modules.auth.UserModel;
import app.uma.modules.item.ItemFactory;
import app.uma.modules.item.ItemModel;
import app.uma.modules.npc.MonsterFactory;
import app.uma.modules.pack.PackFactory;
import app.uma.modules.pack.PackModel;
import app.uma.modules.role.RoleModel;
import app.uma.modules.scene.MapModel;
import app.uma.modules.scene.SceneFactory;
import app.uma.modules.scene.WorldModel;
import app.uma.modules.task.TaskFactory;
import app.uma.modules.task.TaskModel;
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
		addModel(WorldModel.class);
		addModel(TaskModel.class);
		
		addFactory(ItemFactory.class);
		addFactory(PackFactory.class);
		addFactory(SceneFactory.class);
		addFactory(MonsterFactory.class);
//		addFactory(TaskFactory.class);
	}

}
