package de.hybris.platform.cuppytrail.daos.impl;

import static org.junit.Assert.assertEquals;

import de.hybris.platform.cuppytrail.daos.TrippDAO;
import de.hybris.platform.cuppytrail.model.TrippModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;


public class DefaultTripDaoIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private TrippDAO trippDAO;

	@Resource
	private ModelService modelService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Test
	public void oneTripFound()
	{
		final TrippModel trip = newTrip(1, "Varna", "20161010", "20161015", "8510221465");
		pretendRegisteredTripsAre(trip);

		final List<TrippModel> trips = trippDAO.findAllTrips();

		assertEquals(1, trips.size());
		assertEquals(trip, trips.get(0));
	}

	@Test
	public void manyTripsFound()
	{
		final TrippModel trip = newTrip(1, "Varna", "20161010", "20161015", "8510221465");
		final TrippModel otherTrip = newTrip(2, "Sofia", "20161003", "20161020", "8510221465");
		pretendRegisteredTripsAre(trip, otherTrip);

		final List<TrippModel> trips = trippDAO.findAllTrips();

		assertEquals(2, trips.size());
		assertEquals(trip, trips.get(0));
		assertEquals(otherTrip, trips.get(1));
	}

	@Test
	public void noTripFound()
	{
		final List<TrippModel> trips = trippDAO.findAllTrips();

		assertEquals(0, trips.size());
	}

	@Test
	public void findCitiesByVisitors()
	{
		pretendRegisteredTripsAre(newTrip(1, "Varna", "20161010", "20161015", "8510221465"),
				newTrip(2, "Sofia", "20161003", "20161020", "8510221465"), newTrip(3, "Sofia", "20161003", "20161020", "8510221465"),
				newTrip(4, "Burgas", "20161003", "20161020", "8510221465"),
				newTrip(5, "Burgas", "20161003", "20161020", "8510221465"),
				newTrip(6, "Burgas", "20161003", "20161020", "8510221465"));

		final List<String> trips = trippDAO.findCitiesByVisitors();

		assertEquals(3, trips.size());
		assertEquals("Burgass", trips.get(0));
		assertEquals("Sofia", trips.get(1));
		assertEquals("Sofia", trips.get(2));
	}

	@Test
	public void findAllTripsByCity()
	{
		pretendRegisteredTripsAre(newTrip(1, "Varna", "20161010", "20161015", "8510221465"),
				newTrip(2, "Sofia", "20161003", "20161020", "8510221465"), newTrip(3, "Sofia", "20161003", "20161020", "8510221465"),
				newTrip(4, "Burgas", "20161003", "20161020", "8510221465"),
				newTrip(5, "Burgas", "20161003", "20161020", "8510221465"),
				newTrip(6, "Burgas", "20161003", "20161020", "8510221465"));

		final List<TrippModel> trips = trippDAO.findAllTripsByCity("Burgas");

		assertEquals(3, trips.size());
	}


	//	HELPER METHODS
	private TrippModel newTrip(final int id, final String city, final String dateArrived, final String dateDeparted,
			final String egn)
	{
		final TrippModel trip = modelService.create(TrippModel.class);

		trip.setId(id);
		trip.setCity(city);
		trip.setDateArrived(dateArrived);
		trip.setDateDeparted(dateDeparted);

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