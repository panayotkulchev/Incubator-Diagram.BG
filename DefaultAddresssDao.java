package de.hybris.platform.cuppytrail.daos.impl;

import de.hybris.platform.cuppytrail.daos.AddresssDao;
import de.hybris.platform.cuppytrail.model.AddresssModel;
import de.hybris.platform.cuppytrail.model.UserrModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;


public class DefaultAddresssDao extends DefaultGenericDao<AddresssModel> implements AddresssDao
{
	@Resource
	private ModelService modelService;

	public DefaultAddresssDao()
	{
		super(AddresssModel._TYPECODE);
	}

	@Override
	public void registerAddress(final AddresssModel address)
	{
		modelService.save(address);

	}

	@Override
	public List<AddresssModel> findAllAddresses()
	{
		return find();
	}

	@Override
	public List<AddresssModel> findAddressesByKewword(final String keyword)
	{
		//		final StringBuilder sb = new StringBuilder();

		final String s = "SELECT DISTINCT {" + AddresssModel.PK + "} " + "FROM {" + AddresssModel._TYPECODE + "} "
				+ "WHERE CONCAT ({" + AddresssModel.CITY + "}, {" + AddresssModel.STREET + "}) LIKE '%" + keyword + "%'";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(s);

		query.addQueryParameter("keyword", keyword);

		return getFlexibleSearchService().<AddresssModel> search(query).getResult();
	}

	@Override
	public void addAddressesToUser(final UserrModel user, final AddresssModel... addresses)
	{
		if (user.getAddresses() == null)
		{
			user.setAddresses(Arrays.asList(addresses));
		}
		else
		{
			user.getAddresses().addAll(Arrays.asList(addresses));
		}

		modelService.save(user);
	}
}