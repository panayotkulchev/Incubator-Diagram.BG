package de.hybris.platform.crudtrail.dao.impl;

import de.hybris.platform.crudtrail.dao.DiagramProductDao;
import de.hybris.platform.crudtrail.model.DiagramProductModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Panayot Kulchev on 04.11.16.
 * e-mail: panayotkulchev@gmail.com
 * happy codding ...
 */
public class DefaultDiagramProductDao extends DefaultGenericDao<DiagramProductModel> implements DiagramProductDao {

  @Resource
  private ModelService modelService;

  public DefaultDiagramProductDao() {
    super(DiagramProductModel._TYPECODE);
  }

  @Override
  public void createProduct(DiagramProductModel product) {
    modelService.save(product);
  }

  @Override
  public DiagramProductModel getProductByCode(String productCode) {

    List<DiagramProductModel> products = find(Collections.<String, Object>singletonMap(DiagramProductModel.CODE, productCode));

    ServicesUtil.validateIfSingleResult(products, "No saved products with given CODE [" + productCode + "] were found",
            "More than one saved vehicle with given CODE [" + productCode + "] were found");

    DiagramProductModel product = products.get(0);

    return product;
  }

  @Override
  public void updateProduct(DiagramProductModel update) {

    DiagramProductModel productToUpdate = getProductByCode(update.getCode());

    productToUpdate.setName(update.getName());
    productToUpdate.setPrice(update.getPrice());
    productToUpdate.setDescriptionShort(update.getDescriptionShort());
    productToUpdate.setProductStatus(update.getProductStatus());

    modelService.save(productToUpdate);
  }

  @Override
  public void deleteProductByCode(String productCode) {

    DiagramProductModel product = getProductByCode(productCode);

    if (Objects.nonNull(product)) {
      modelService.remove(product);
    }
  }

  @Override
  public List<DiagramProductModel> getAllProducts() {
    return this.find();
  }
}