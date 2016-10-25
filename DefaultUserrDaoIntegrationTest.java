package de.hybris.platform.cuppytrail.daos.impl;

import static org.junit.Assert.assertEquals;

import de.hybris.platform.cuppytrail.daos.UserrDao;
import de.hybris.platform.cuppytrail.model.AddresssModel;
import de.hybris.platform.cuppytrail.model.UserrModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jersey.repackaged.com.google.common.collect.Lists;

import org.junit.Test;


public class DefaultUserrDaoIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private UserrDao userrDao;
	@Resource
	private ModelService modelService;

	@Test
	public void registerUser()
	{
		final UserrModel user = newUser("email@test.com", "Ivan Petrov", new Date());
		userrDao.registerUser(user);

		final List<UserrModel> users = userrDao.findAllUsers();

		assertEquals(1, users.size());
		assertEquals("email@test.com", users.get(0).getEmail());
	}

	@Test
	public void findUsersWithoutAddresses()
	{
		final UserrModel user = newUser("email@test.com", "Ivan Petrov", new Date());
		user.setAddresses(Lists.newArrayList(newAddress(123)));

		final UserrModel userWithoutAddress = newUser("email2@test.com", "Pavel Martinov", new Date());

		userrDao.registerUser(user);
		userrDao.registerUser(userWithoutAddress);

		final List<UserrModel> users = userrDao.findAllUsersWithoutAddress();

		assertEquals(1, users.size());
		assertEquals("email2@test.com", users.get(0).getEmail());
	}

	//HELPER METHODS
	private UserrModel newUser(final String email, final String name, final Date birthDay)
	{
		final UserrModel user = modelService.create(UserrModel.class);

		user.setEmail(email);
		user.setName(name);
		user.setBirthDay(birthDay);

		return user;
	}

	private AddresssModel newAddress(final int code)
	{
		final AddresssModel address = modelService.create(AddresssModel.class);
		address.setCode(Integer.valueOf(code));

		return address;
	}
}
