package com.bank.bo;

import com.bank.exceptions.BusinessException;
import com.bank.to.User;

public interface UserBO {
	User validateUser(User user) throws BusinessException;
	User registerUser(User user, String passwordOne, String passwordTwo) throws BusinessException;
}
