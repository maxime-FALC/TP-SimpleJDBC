package simplejdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {

	protected final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) {
		this.myDataSource = dataSource;
	}

	
        

	/**
	 * Ajouter un avantage client dans la table
	 *
	 * @param nameDisc le nom du taux
         * @param rateDisc valeur du taux à ajouter
	 * @throws DAOException, Exception
	 */
	public void addDiscount(String nameDisc, int rateDisc) throws DAOException, Exception {
            
            
            String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
            
            try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql)) {
		
		stmt.setString(1, nameDisc);
                stmt.setInt(2, rateDisc);

			
		if(1 == stmt.executeUpdate())
                {
                    throw new Exception("Problem of execution");
                }
                
            }  catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
		throw new DAOException(ex.getMessage());
            }
	}
        
        
        
        
        
        
        /**
	 * Supprimer un avantage client dans la table
	 *
	 * @param nameDisc le nom du taux
	 * @throws DAOException, Exception
	 */
	public void deleteDiscount(String nameDisc) throws DAOException, Exception {
            
            
            String sql = "DELETE FROM DISCOUNT_CODE WHERE = ?";
            
            try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql)) {
		
		stmt.setString(1, nameDisc);

			
		if(1 == stmt.executeUpdate())
                {
                    throw new Exception("Problem of execution");
                }
                
            }  catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
		throw new DAOException(ex.getMessage());
            }
	}
        
        
        
        
        
        
        /**
	 * modifier un taux de réduction dans la table
	 *
	 * @param nameDisc le nom du taux
         * @param newRate nouveau taux à mettre à jour
	 * @throws DAOException, Exception
	 */
	public void updateDiscount(String nameDisc, int newRate) throws DAOException, Exception {
            
            
            String sql = "UPDATE TABLE SET RATE=? WHERE DISCOUNT_CODE = ?";
            
            try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
		PreparedStatement stmt = connection.prepareStatement(sql)) {
		
		stmt.setInt(1, newRate);
                stmt.setString(2, nameDisc);

			
		if(1 == stmt.executeUpdate())
                {
                    throw new Exception("Problem of execution");
                }
                
            }  catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
		throw new DAOException(ex.getMessage());
            }
	}
        
        
        
        
        
        
        
        

	/**
	 * Liste des taux de réduction de la table
	 *
	 * @return la liste des clients habitant dans cet état
	 * @throws DAOException
	 */
	public List<Discount_Code_Entity> customersInState() throws DAOException {
            
            List<Discount_Code_Entity> result = new LinkedList<>(); // Liste vIde

            String sql = "SELECT * FROM DISCOUNT_CODE";
            try (Connection connection = myDataSource.getConnection();
            	PreparedStatement stmt = connection.prepareStatement(sql)) {

		try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) { // Tant qu'il y a des enregistrements
                        // On récupère les champs nécessaires de l'enregistrement courant
			int rate = rs.getInt("RATE");
			String name = rs.getString("DISCOUNT_CODE");
			// On crée l'objet entité
			Discount_Code_Entity c = new Discount_Code_Entity(name, rate);
			// On l'ajoute à la liste des résultats
			result.add(c);
                    }
		}
            }  catch (SQLException ex) {
		Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
		throw new DAOException(ex.getMessage());
            }

        return result;
	}

}
