package com.bank.bo;

import com.bank.exceptions.BusinessException;
import com.bank.to.User;

public interface UserBO {
	boolean validateUser(User user) throws BusinessException;
	User registerUser(User user) throws BusinessException;
}
