package de.hybris.platform.cuppytrail.daos.impl;

import static org.junit.Assert.assertEquals;

import de.hybris.platform.cuppytrail.daos.AddresssDao;
import de.hybris.platform.cuppytrail.model.AddresssModel;
import de.hybris.platform.cuppytrail.model.UserrModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jersey.repackaged.com.google.common.collect.Lists;

import org.junit.Test;


public class DefaultAddresssDaoIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private AddresssDao addresssDao;
	@Resource
	private ModelService modelService;

	@Test
	public void findAddressByKeywordInTheCity()
	{
		addresssDao.registerAddress(newAddress(1, "Veliko Tarnovo", "8 Ivan Vazov str."));
		addresssDao.registerAddress(newAddress(2, "Draganovo", "20 Petar Ignatov str."));

		final List<AddresssModel> addresses = addresssDao.findAddressesByKewword("ovo");

		assertEquals(2, addresses.size());
		assertEquals(Integer.valueOf(1), addresses.get(0).getCode());
		assertEquals(Integer.valueOf(2), addresses.get(1).getCode());
	}

	@Test
	public void findAddressByKeywordInTheStreet()
	{
		addresssDao.registerAddress(newAddress(1, "Sofia", "8 Dimitar Vazov str."));
		addresssDao.registerAddress(newAddress(2, "Sofia", "20 Petar Ignatov str."));
		addresssDao.registerAddress(newAddress(3, "Sofia", "20 Dimo Dimov str."));

		final List<AddresssModel> addresses = addresssDao.findAddressesByKewword("tar");

		assertEquals(2, addresses.size());
		assertEquals(Integer.valueOf(1), addresses.get(0).getCode());
		assertEquals(Integer.valueOf(2), addresses.get(1).getCode());
	}

	@Test
	public void addAddressesToUserWitoutAnyAddresses()
	{
		final AddresssModel firstAddress = newAddress(1, "Sofia", "8 Dimitar Vazov str.");
		final AddresssModel secondAddress = newAddress(2, "Sofia", "20 Petar Ignatov str.");

		final UserrModel user = newUser("email@test.com", "name", new Date());

		addresssDao.addAddressesToUser(user, firstAddress, secondAddress);

		final List<AddresssModel> addresses = addresssDao.findAllAddresses();

		assertEquals(2, addresses.size());
		assertEquals(user, addresses.get(0).getUser());
		assertEquals(user, addresses.get(1).getUser());
	}

	@Test
	public void addAddressesToUserWithAddresses()
	{
		final AddresssModel firstAddress = newAddress(1, "Sofia", "8 Dimitar Vazov str.");
		final AddresssModel secondAddress = newAddress(2, "Sofia", "20 Petar Ignatov str.");

		final UserrModel user = newUser("email@test.com", "name", new Date());
		user.setAddresses(Lists.newArrayList(firstAddress));

		addresssDao.addAddressesToUser(user, secondAddress);

		final List<AddresssModel> addresses = addresssDao.findAllAddresses();

		assertEquals(2, addresses.size());
		assertEquals(user, addresses.get(0).getUser());
		assertEquals(user, addresses.get(1).getUser());
	}


	private AddresssModel newAddress(final int i, final String city, final String street)
	{
		final AddresssModel address = modelService.create(AddresssModel.class);

		address.setCode(Integer.valueOf(i));
		address.setCity(city);
		address.setStreet(street);

		return address;
	}

	private UserrModel newUser(final String email, final String name, final Date birthDay)
	{
		final UserrModel user = modelService.create(UserrModel.class);

		user.setEmail(email);
		user.setName(name);
		user.setBirthDay(birthDay);

		return user;
	}
}
