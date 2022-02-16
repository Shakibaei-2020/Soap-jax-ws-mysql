package collaborateur.web.service.model.services;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;


import collaborateur.web.service.model.Collaborateur;
import collaborateur.web.service.model.exceptions.CollaborateurExistException;
import collaborateur.web.service.model.exceptions.CollaborateurInconnuException;

@WebService
public interface CollaborateurService {

	@WebMethod
	int count();

	@WebMethod
	ArrayList<Collaborateur> getCollaborateurs();

	@WebMethod
	Collaborateur getCollaborateur(int id) throws CollaborateurInconnuException, SQLException;

	@WebMethod
	Collaborateur addCollaborateur(int id, String nom) throws CollaborateurExistException;

	@WebMethod
	boolean delete(int id) throws CollaborateurInconnuException;

	@WebMethod
	Collaborateur updateCollaborateur(int id, String nom) throws CollaborateurInconnuException;


}
