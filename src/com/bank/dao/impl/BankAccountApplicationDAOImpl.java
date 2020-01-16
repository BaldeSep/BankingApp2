package com.bank.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bank.dao.BankAccountApplicationDAO;
import com.bank.exceptions.BusinessException;
import com.bank.to.Application;
import com.bank.to.BankAccount;
import com.bank.to.User;
import com.bank.to.types.ApplicationStatus;
import com.bank.util.OracleDBConnection;

public class BankAccountApplicationDAOImpl implements BankAccountApplicationDAO {

	@Override
	public boolean applyForBankAccount(User user, double initialBalance) throws BusinessException {
		boolean applicationSuccess = false;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "{ call applyforbankaccount(?,?,?) }";
			CallableStatement statement = conn.prepareCall(sql);
			statement.setString(1, user.getUserName());
			statement.setDouble(2, initialBalance);
			statement.setDate(3, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
			int updateCount = statement.executeUpdate();
			if(updateCount == 1) {
				applicationSuccess = true;
			}
		}catch(ClassNotFoundException | SQLException | NullPointerException e) {
			throw new BusinessException("There Was An Internal Error");
		}
		return applicationSuccess;
	}

	@Override
	public BankAccount approveBankAccount(int applicationId) throws BusinessException {
		BankAccount account = null;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "{ call APPROVEAPPLICATION(?,?,?,?,?) }";
			CallableStatement statement = conn.prepareCall(sql);
			statement.setInt(1, applicationId);
			statement.registerOutParameter(2, java.sql.Types.VARCHAR);
			statement.registerOutParameter(3, java.sql.Types.DOUBLE);
			statement.registerOutParameter(4, java.sql.Types.INTEGER);
			statement.registerOutParameter(5, java.sql.Types.DATE);
			String holder;
			double balance;
			int accountNumber;
			Date dateCreated;
			int updateCount = statement.executeUpdate();
			if(updateCount > 0) {
				holder = statement.getString(2);
				balance = statement.getDouble(3);
				accountNumber = statement.getInt(4);
				dateCreated = statement.getDate(5);
				account = new BankAccount(accountNumber, holder, balance, dateCreated);
			}else {
				throw new BusinessException("Could Not Find Application");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException("Internal Error");
		}
		return account;
	}

	@Override
	public BankAccount denyBankAccount(int applicationId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Application> viewApplications(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Application viewApplication(int applicationId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Application> viewApplications() throws BusinessException {
		List<Application> applications = null;
		try(Connection conn = OracleDBConnection.getConnection()){
			String sql = "Select applicant, date_applied, application_id, default_balance, status From BankAccountApplications";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			int applicationId;
			double initialBalance;
			Date dateApplied;
			String applicant;
			ApplicationStatus status;
			while(results.next()) {
				applicationId = results.getInt("application_id");
				initialBalance = results.getDouble("default_balance");
				dateApplied = new Date(results.getDate("date_applied").getTime());
				applicant = results.getString("applicant");
				status = ApplicationStatus.fromInt(results.getInt("status"));
				if(applications == null) {
					applications = new ArrayList<>();
				}
				applications.add(
						new Application(
								applicationId, 
								initialBalance, 
								LocalDateTime.ofInstant(dateApplied.toInstant(), ZoneId.systemDefault()), 
								applicant, status));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new BusinessException(e.getMessage());
		}
		return applications;
	}
	

}
