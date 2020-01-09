package com.bank.bo.impl;

import com.bank.bo.UserBO;
import com.bank.dao.impl.UserDAOImpl;
import com.bank.exceptions.BusinessException;
import com.bank.to.User;

public class UserBOImpl implements UserBO {

	@Override
	public User validateUser(User user) throws BusinessException {
		String userName = user.getUserName();
		String password = user.getPassword();
		if(!userName.contains(" ") && userName.length() >= 5) {
			if(!password.contains(" ") && password.length() >= 3) {
				return new UserDAOImpl().validateUser(user);
			}
		}
		return null;
	}

	@Override
	public User registerUser(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
