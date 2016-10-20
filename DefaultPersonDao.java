package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.PersonnDao;
import de.hybris.platform.cuppytrail.model.PersonnModel;
import de.hybris.platform.cuppytrail.model.TrippModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;


public class DefaultPersonDao extends DefaultGenericDao<PersonnModel> implements PersonnDao
{

	@Resource
	private ModelService modelService;

	public DefaultPersonDao()
	{
		super(PersonnModel._TYPECODE);
	}

	@Override
	public void addPersonn(final PersonnModel personn)
	{
		modelService.save(personn);
	}


	@Override
	public List<PersonnModel> findAllPeople()
	{
		return find();
	}

	@Override
	public List<PersonnModel> findPeopleInSameCityAtSameTime(final String city, final String date)
	{

		final StringBuilder sb = new StringBuilder();

		sb.append("SELECT {").append(PersonnModel.PK).append("} ");
		sb.append("FROM {").append(TrippModel._TYPECODE).append(" JOIN ").append(PersonnModel._TYPECODE).append(" ");
		sb.append("ON {").append(TrippModel.PERSONN).append("}={").append(PersonnModel.PK).append("} } ");
		sb.append("WHERE {").append(TrippModel.CITY).append("} = ?city ");
		sb.append("AND {").append(TrippModel.DATEARRIVED).append("} <= ?date ");
		sb.append("AND {").append(TrippModel.DATEDEPARTED).append("} >= ?date ");

		//		final String s = "SELECT {Personn.PK} " + "FROM {Tripp JOIN Personn " + "ON {Tripp.personn}={Personn.PK}} "
		//				+ "WHERE {Tripp.city} = 'Varna' " + "AND {Tripp.dateArrived} <= '20161012'	AND {Tripp.dateDeparted} >= '20161012'";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(sb.toString());

		query.addQueryParameter("city", city);
		query.addQueryParameter("date", date);

		return getFlexibleSearchService().<PersonnModel> search(query).getResult();
	}

	@Override
	public List<PersonnModel> findPeopleByKeyword(final String keyword)
	{
		final StringBuilder sb = new StringBuilder();

		sb.append("SELECT {").append(PersonnModel.PK).append("} ");
		sb.append("FROM {").append(PersonnModel._TYPECODE).append("} ");
		sb.append("WHERE {").append(PersonnModel.NAME).append("} ");
		sb.append("LIKE CONCAT('%', CONCAT(?keyword, '%'))");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(sb.toString());

		query.addQueryParameter("keyword", keyword);

		return getFlexibleSearchService().<PersonnModel> search(query).getResult();
	}
}
