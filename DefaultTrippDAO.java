/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.TrippDAO;
import de.hybris.platform.cuppytrail.model.PersonnModel;
import de.hybris.platform.cuppytrail.model.TrippModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;


/**
 *
 */
public class DefaultTrippDAO implements TrippDAO
{

	@Resource
	private ModelService modelService;

	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Override
	public void addPersonn(final PersonnModel personn)
	{
		modelService.save(personn);
	}

	@Override
	public void updatePersonn(final PersonnModel personn)
	{
		// YTODO Auto-generated method stub

	}

	@Override
	public List<PersonnModel> findAllPeople()
	{
		final String queryString = "SELECT {p:" + PersonnModel.PK + "} " + "FROM {" + PersonnModel._TYPECODE + " AS p} ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		return flexibleSearchService.<PersonnModel> search(query).getResult();
	}

	@Override
	public void addTripp(final TrippModel tripp)
	{
		modelService.save(tripp);
	}

	@Override
	public void updateTripp(final TrippModel tripp)
	{
		// YTODO Auto-generated method stub

	}

	@Override
	public List<PersonnModel> findPeopleInSameCityAtSameTime(final String city, final String date)
	{

		final StringBuilder sb = new StringBuilder();

		sb.append("SELECT { ").append(PersonnModel.PK).append("} ");
		sb.append("FROM { ").append(PersonnModel._TYPECODE).append("} ");
		sb.append("WHERE { ").append(PersonnModel.EGN).append("} ");
		sb.append("IN ({{ SELECT {").append(TrippModel.EGN).append("} ");
		sb.append("FROM {").append(TrippModel._TYPECODE).append("} ");
		sb.append("WHERE {").append(TrippModel.CITY).append("} = ?city ");
		sb.append("AND {").append(TrippModel.DATEARRIVED).append("} <= ?date ");
		sb.append("AND {").append(TrippModel.DATEDEPARTED).append("} >= ?date }}) ");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(sb.toString());

		query.addQueryParameter("city", city);
		query.addQueryParameter("date", date);

		return flexibleSearchService.<PersonnModel> search(query).getResult();
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

		return flexibleSearchService.<PersonnModel> search(query).getResult();
	}

	@Override
	public List<String> findCitiesByVisitors()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	@Override
	public List<TrippModel> findAllTrips()
	{
		final String query = "SELECT {t:" + TrippModel.PK + "} " + "FROM {" + TrippModel._TYPECODE + " AS t} ";

		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(query);

		return flexibleSearchService.<TrippModel> search(flexibleSearchQuery).getResult();
	}

}
