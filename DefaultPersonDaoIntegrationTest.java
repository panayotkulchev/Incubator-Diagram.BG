package de.hybris.platform.cuppytrail.daos.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.platform.cuppytrail.daos.PersonnDao;
import de.hybris.platform.cuppytrail.model.PersonnModel;
import de.hybris.platform.cuppytrail.model.TrippModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;


public class DefaultPersonDaoIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private TrippDAO trippDAO;

	@Resource
	private ModelService modelService;

	@Test
	public void onePersonFound()
	{
		pretendRegisteredPeopleAre(newPerson("8510221465", "Panayot", 20, "test@test.com"));

		final List<PersonnModel> people = personDao.findAllPeople();

		assertEquals(people.size(), 1);
		assertEquals("8510221465", people.get(0).getEgn());
	}

	@Test
	public void manyPeopleFound()
	{
		pretendRegisteredPeopleAre(newPerson("8510221467", "Ivan", 30, "oter@email.com"),
				newPerson("8510221468", "Panayot", 20, "test@test.com"));

		final List<PersonnModel> people = personDao.findAllPeople();

		assertEquals(2, people.size());
		assertEquals("8510221467", people.get(0).getEgn());
		assertEquals("8510221468", people.get(1).getEgn());
	}

	@Test
	public void noPeopleFound()
	{
		final List<PersonnModel> people = personDao.findAllPeople();

		assertTrue(people.isEmpty());
	}

	@Test
	public void findPeopleInSameCityAtSameDate()
	{
		final PersonnModel person = newPerson("8510221465", "Panayot", 20, "test@test.com");
		final PersonnModel otherPerson = newPerson("8510221467", "Ivan", 30, "oter@email.com");

		final TrippModel trip = newTrip(1, "Varna", "20161010", "20161015", person);
		final TrippModel otherTrip = newTrip(2, "Varna", "20161003", "20161020", otherPerson);
		pretendRegisteredTripsAre(trip, otherTrip);

		final List<PersonnModel> people = personDao.findPeopleInSameCityAtSameTime("Varna", "20161012");

		assertEquals(2, people.size());
		assertEquals(person, people.get(0));
		assertEquals(otherPerson, people.get(1));
	}

	@Test
	public void findPeopleByName()
	{
		pretendRegisteredPeopleAre(newPerson("1", "Ivan", 1, ""), newPerson("2", "Petar", 2, ""), newPerson("3", "Julian", 3, ""));

		final List<PersonnModel> people = personDao.findPeopleByKeyword("an");

		assertEquals(2, people.size());
		assertEquals("Ivan", people.get(0).getName());
		assertEquals("Julian", people.get(1).getName());
	}

	//	HELPER METHODS
	private PersonnModel newPerson(final String egn, final String name, final int age, final String email)
	{
		final PersonnModel personn = modelService.create(PersonnModel.class);
		personn.setEgn(egn);
		personn.setName(name);
		personn.setAge(Integer.valueOf(age));
		personn.setEmail(email);

		return personn;
	}

	private void pretendRegisteredPeopleAre(final PersonnModel... people)
	{

		for (final PersonnModel person : people)
		{
			modelService.save(person);
		}
	}

	private TrippModel newTrip(final int id, final String city, final String dateArrived, final String dateDeparted,
			final PersonnModel person)
	{
		final TrippModel trip = modelService.create(TrippModel.class);

		trip.setId(id);
		trip.setCity(city);
		trip.setDateArrived(dateArrived);
		trip.setDateDeparted(dateDeparted);
		trip.setPersonn(person);

		return trip;
	}

	private void pretendRegisteredTripsAre(final TrippModel... trips)
	{
		for (final TrippModel trip : trips)
		{
			modelService.save(trip);
		}
	}
}
