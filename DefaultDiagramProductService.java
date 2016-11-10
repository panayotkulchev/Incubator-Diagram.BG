package de.hybris.platform.crudtrail.service.impl;

import de.hybris.platform.crudtrail.dao.DiagramProductDao;
import de.hybris.platform.crudtrail.jalo.DiagramProduct;
import de.hybris.platform.crudtrail.model.DiagramProductModel;
import de.hybris.platform.crudtrail.service.DiagramProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Panayot Kulchev on 04.11.16.
 * e-mail: panayotkulchev@gmail.com
 * happy codding ...
 */
public class DefaultDiagramProductService implements DiagramProductService{

  private DiagramProductDao diagramProductDao;

  @Override
  public void createProduct(DiagramProductModel product) {
    diagramProductDao.createProduct(product);
  }

  @Override
  public DiagramProductModel getProductByCode(String productCode) {
    DiagramProductModel product = diagramProductDao.getProductByCode(productCode);
    return product;
  }

  @Override
  public List<DiagramProductModel> getAllProducts() {
    List<DiagramProductModel> products = diagramProductDao.getAllProducts();
    return products;
  }

  @Override
  public void updateProduct(DiagramProductModel product) {
    diagramProductDao.updateProduct(product);
  }

  @Override
  public void deleteProductByCode(String productCode) {
    diagramProductDao.deleteProductByCode(productCode);
  }

  @Autowired
  public void setDiagramProductDao(DiagramProductDao diagramProductDao) {
    this.diagramProductDao = diagramProductDao;
  }
}