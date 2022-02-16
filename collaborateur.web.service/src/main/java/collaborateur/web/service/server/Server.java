package collaborateur.web.service.server;

import javax.xml.ws.Endpoint;

import collaborateur.web.service.model.services.CollaborateurServiceImpl;

public class Server {

	public static void main(String[] args) {

		Endpoint.publish("http://localhost:8478/collaborateurService"	, new CollaborateurServiceImpl());
		System.out.println("Serveur prêt !");
	
	}

}
