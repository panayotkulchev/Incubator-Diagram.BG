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
		final StringBuilder sb = new StringBuilder();

		//		sb.append("SELECT {").append(UserrModel.PK).append("} ");
		//		sb.append("FROM {").append(UserrModel._TYPECODE).append("} ");
		//		sb.append("WHERE {").append(AddresssModel.CITY).append("} ");
		//		sb.append("LIKE CONCAT('%', CONCAT(?keyword, '%')) ");
		//		sb.append("{").append(AddresssModel.STREET).append("} ");
		//		sb.append("LIKE CONCAT('%', CONCAT(?keyword, '%')) ");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(sb.toString());

		query.addQueryParameter("keyword", keyword);

		return getFlexibleSearchService().<AddresssModel> search(query).getResult();
	}

	@Override
	public void addAddressesToUser(final UserrModel user, final AddresssModel... addresses)
	{
		user.getAddresses().addAll(Arrays.asList(addresses));

		modelService.save(user);
	}
}