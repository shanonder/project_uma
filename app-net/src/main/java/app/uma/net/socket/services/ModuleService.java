package app.uma.net.socket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.uma.net.socket.interfaces.IModuleFacade;

@Service
public class ModuleService  extends Thread {
	
	@Autowired
	private IModuleFacade moduleFacade;
	public void start(){
		moduleFacade.start();
	}
}
