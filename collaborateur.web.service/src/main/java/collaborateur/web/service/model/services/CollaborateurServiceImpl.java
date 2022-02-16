package collaborateur.web.service.model.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javax.jws.WebService;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import collaborateur.web.service.DBUtil.DBUtil;
import collaborateur.web.service.model.Collaborateur;
import collaborateur.web.service.model.exceptions.CollaborateurExistException;
import collaborateur.web.service.model.exceptions.CollaborateurInconnuException;

/**
 * 
 * @author mrshakibaei
 *
 */
@WebService(endpointInterface = "collaborateur.web.service.model.services.CollaborateurService")
public class CollaborateurServiceImpl implements CollaborateurService {

	
	
	/*ATRIBUTS*/
	private ArrayList<Collaborateur> collaborateurs;
	
	

	/* QUERY*/
	private  final String INSERT_COLLAB_SQL = "INSERT INTO collaborateur" + "  (id,nom) VALUES " + " (?,?);";
	private  final String SELECT_COLLAB_BY_ID = "SELECT nom FROM collaborateur WHERE id =?";	
	private  final String SELECT_ALL_COLLAB = "SELECT * FROM collaborateur";
	private  final String DELETE_COLLAB_SQL = "DELETE FROM collaborateur WHERE id = ?;";
	private  final String UPDATE_COLLAB_SQL = "UPDATE collaborateur SET nom = ? WHERE id = ?;";

	


	
	/*CONSTRUCTOR*/
	public CollaborateurServiceImpl() {
		

	  
		collaborateurs = new ArrayList<Collaborateur>();
		
		collaborateurs.addAll(Arrays.asList(
				new Collaborateur(1,"creg"),
				new Collaborateur(2,"steve"),
				new Collaborateur(3,"bart"),
				new Collaborateur(4,"homer"),
				new Collaborateur(5,"marge")

				));
	}
	
	/*METHODS*/
	@Override
	public int count() {
		return collaborateurs.size();
	}

	@Override
	public ArrayList<Collaborateur> getCollaborateurs() {
		return collaborateurs;
	}

	@Override
	public Collaborateur getCollaborateur(int id) throws CollaborateurInconnuException{
		
		
		/**
		Optional<Collaborateur> collaborateur = collaborateurs.stream().filter(e -> e.getId() == id).findFirst();
		if(collaborateur.isEmpty()) {
			throw new CollaborateurInconnuException("Erreur collaborateur avec l'id " + id + " n'a pas été trouvé");
		}else {
			return collaborateur.get();
		}
		*/
		
		Collaborateur col = new Collaborateur(0, "personne");
	     try (Connection connection = DBUtil.getConnection();
		            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COLLAB_BY_ID)) {
		            preparedStatement.setInt(1, id);
		            ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		            while (rs.next()) {
		                String nomColl = rs.getString("nom");
		                  col.setId(id);
		                  col.setNom(nomColl);
		            }
		        } catch (SQLException e) {
		            System.out.println(e);
		        }
		        return col;
		    };

		
		
	

	@Override
	public Collaborateur addCollaborateur(int id, String nom) throws CollaborateurExistException {

		/**
		Optional<Collaborateur> collaborateur = collaborateurs.stream().filter(e -> e.getId() == id).findFirst();
		
		if(collaborateur.isPresent()) {
			throw new CollaborateurExistException("Erreur collaborateur avec l'id " + id + " existe deja");
		}else {
			
			collaborateurs.add(collaborateur.get());
			return collaborateur.get();
		}
		*/
		
	     try (Connection connection = DBUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COLLAB_SQL)) {
	            preparedStatement.setLong(1, id);
	            preparedStatement.setString(2, nom);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	      
	     return new Collaborateur(id,nom);
		
	 
	
	}

	@Override
	public boolean delete(int id) throws CollaborateurInconnuException {
		
	Optional<Collaborateur> collaborateur = collaborateurs.stream().filter(e -> e.getId() == id).findFirst();
		
		if(collaborateur.isEmpty()) {
			throw new CollaborateurInconnuException("Erreur collaborateur avec l'id " + id + " n'a pas été trouvé");
			
		}else {
			return collaborateurs.remove(collaborateur.get());
		}
		
	}

	@Override
	public Collaborateur updateCollaborateur(int id, String nom) throws CollaborateurInconnuException {
		
		Optional<Collaborateur> collaborateur = collaborateurs.stream().filter(e -> e.getId() == id).findFirst();

		
		if(collaborateur.isEmpty()) {
			throw new CollaborateurInconnuException("Erreur collaborateur avec l'id " + id + " n'a pas été trouvé");
			
		}else {
			
			collaborateur.get().setNom(nom);
			
			return collaborateur.get();
		}	
		
	}

}
