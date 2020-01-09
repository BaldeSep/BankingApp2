package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.UserDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;
import com.bank.to.types.UserType;
import com.bank.util.OracleDBConnection;

public class UserDAOImpl implements UserDAO {

	@Override
	public User validateUser(User user) throws BusinessException {
		User copyUser = new User(user);
		try(Connection conn = OracleDBConnection.getConnection()){
			// Create SQL Search Statement
			String sql = "Select user_type From Users Where user_name = ? And password = ?";
			// Create Statement Object
			PreparedStatement statement = conn.prepareStatement(sql);
			// Get User Name and Password From Passed Object
			String userName = copyUser.getUserName();
			String password = copyUser.getPassword();
			// Execute Query
			statement.setString(1, userName);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				copyUser.setUserType(UserType.fromInt(result.getInt("user_type")));
			}else {
				throw new BusinessException("User Not Found");
			}			
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		return copyUser;
	}

	@Override
	public User registerUser(User user) throws BusinessException {
		User copyUser = null;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "{call addnewuser(?,?,?)}";
			CallableStatement statement = conn.prepareCall(sql);
			String userName = user.getUserName();
			String password = user.getPassword();
			UserType userType = user.getUserType();
			statement.setString(1, userName);
			statement.setString(2, password);
			statement.setInt(3, userType.ordinal());
			statement.executeUpdate();
			copyUser = validateUser(user);
			if(copyUser == null) {
				throw new BusinessException("An Error Occured When Restering User Account");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("There Was An Internal Error.");
		} 
		return copyUser;
	}

}
