package com.bank.dao;

import com.bank.exceptions.BusinessException;
import com.bank.to.User;

public interface UserDAO {
	User validateUser(User user) throws BusinessException;
	User registerUser(User user) throws BusinessException;
}
