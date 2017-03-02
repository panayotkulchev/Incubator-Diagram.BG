package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.UserrDao;
import de.hybris.platform.cuppytrail.model.UserrModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;

import javax.annotation.Resource;


public class DefaultUserrDao extends DefaultGenericDao<UserrModel> implements UserrDao
{
	@Resource
	private ModelService modelService;

	public DefaultUserrDao()
	{
		super(UserrModel._TYPECODE);
	}

	@Override
	public void registerUser(final UserrModel user)
	{
		modelService.save(user);
	}

	@Override
	public List<UserrModel> findAllUsers()
	{
		return find();
	}

	@Override
	public List<UserrModel> findAllUsersOnSameStreet()
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserrModel> findAllUsersWithoutAddress()
	{
		final String s = "SELECT {Userr.PK} " + "FROM {Userr} " + "WHERE {Userr.PK} NOT IN ({{ " + "SELECT {Userr.PK} "
				+ "FROM {Addresss JOIN Userr ON {Addresss.user}={Userr.PK}} }})";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(s);

		return getFlexibleSearchService().<UserrModel> search(query).getResult();

	}
}
