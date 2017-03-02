package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.TrippDAO;
import de.hybris.platform.cuppytrail.model.TrippModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;


public class DefaultTrippDAO extends DefaultGenericDao<TrippModel> implements TrippDAO
{
	public DefaultTrippDAO()
	{
		super(TrippModel._TYPECODE);
	}

	@Resource
	private ModelService modelService;

	@Override
	public void addTripp(final TrippModel tripp)
	{
		modelService.save(tripp);
	}

	@Override
	public List<String> findCitiesByVisitors()
	{
		final StringBuilder sb = new StringBuilder();

		//		final String s = "SELECT {Tripp.city} from {Tripp} group by {Tripp.city} order by count({Tripp.city}) desc";

		sb.append("SELECT {").append(TrippModel.CITY).append("} ");
		sb.append("FROM {").append(TrippModel._TYPECODE).append("} ");
		sb.append("GROUP BY {").append(TrippModel.CITY).append("} ");
		sb.append("ORDER BY COUNT ({").append(TrippModel.CITY).append("}) DESC");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(sb.toString());

		final List<String> cities = getFlexibleSearchService().<String> search(query).getResult();

		return cities;
	}

	@Override
	public List<TrippModel> findAllTrips()
	{
		return this.find();
	}

	@Override
	public List<TrippModel> findAllTripsByCity(final String city)
	{
		return find(Collections.<String, Object> singletonMap(TrippModel.CITY, city));
	}
}
