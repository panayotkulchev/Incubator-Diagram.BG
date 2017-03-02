package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.PersonnModel;

import java.util.List;


public interface PersonnDao
{
	void addPersonn(PersonnModel personn);

	List<PersonnModel> findAllPeople();

	List<PersonnModel> findPeopleInSameCityAtSameTime(String cityName, String date);

	List<PersonnModel> findPeopleByKeyword(String keyword);
}
