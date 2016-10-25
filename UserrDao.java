package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.UserrModel;

import java.util.List;


public interface UserrDao
{
	void registerUser(UserrModel user);

	List<UserrModel> findAllUsers();

	List<UserrModel> findAllUsersOnSameStreet();

	List<UserrModel> findAllUsersWithoutAddress();
}
