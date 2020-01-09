package com.bank.bo.impl;

import com.bank.bo.UserBO;
import com.bank.dao.impl.UserDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;

public class UserBOImpl implements UserBO {

	@Override
	public User validateUser(User user) throws BusinessException {
		if(validateUserCreadetials(user)) {
			return new UserDAOImpl().validateUser(user);			
		}
		return null;
	}

	@Override
	public User registerUser(User user) throws BusinessException {
		if(validateUserCreadetials(user)) {
			return new UserDAOImpl().registerUser(user);
		}
		return null;
	}
	
	private boolean validateUserCreadetials(User user) {
		boolean validUser = false;
		String userName = user.getUserName();
		String password = user.getPassword();
		if(!userName.contains(" ") && userName.length() >= 5) {
			if(!password.contains(" ") && password.length() >= 3) {
				return validUser = true;
			}
		}
		return validUser;
	}

}
