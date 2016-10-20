package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.TrippModel;

import java.util.List;


public interface TrippDAO
{
	void addTripp(TrippModel tripp);

	List<TrippModel> findAllTrips();

	List<TrippModel> findAllTripsByCity(String city);

	List<String> findCitiesByVisitors();
}