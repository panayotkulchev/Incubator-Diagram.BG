package de.hybris.platform.crudtrail.facades.impl;

import com.google.common.collect.Lists;
import de.hybris.platform.crudtrail.data.ProductData;
import de.hybris.platform.crudtrail.enums.DiagramProductStatus;
import de.hybris.platform.crudtrail.facades.DiagramProductFacade;
import de.hybris.platform.crudtrail.model.DiagramProductModel;
import de.hybris.platform.crudtrail.service.DiagramProductService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Panayot Kulchev on 03.11.16.
 * e-mail: panayotkulchev@gmail.com
 * happy codding ...
 */
public class DefaultDiagramProductFacade implements DiagramProductFacade {

  private DiagramProductService diagramProductService;
  private ModelService modelService;

  @Override
  public void createProduct(ProductData productData) {
    DiagramProductModel model = productModelFrom(productData);
    diagramProductService.createProduct(model);
  }

  @Override
  public ProductData getProductByCode(String code) {
    DiagramProductModel product = diagramProductService.getProductByCode(code);
    return productDataFrom(product);
  }

  @Override
  public void updateProduct(ProductData productData) {
    DiagramProductModel model = productModelFrom(productData);
    diagramProductService.updateProduct(model);
  }

  @Override
  public void deleteProductByCode(String productCode) {
    diagramProductService.deleteProductByCode(productCode);
  }

  @Override
  public List<ProductData> getAllProducts() {
    List<DiagramProductModel> products = diagramProductService.getAllProducts();
    return convert(products);
  }

  private List<ProductData> convert(List<DiagramProductModel> products) {
    List<ProductData> result = Lists.newArrayList();

    products.stream().forEach((productModel) -> {
      result.add(productDataFrom(productModel));
    });

    return result;
  }

  private ProductData productDataFrom(DiagramProductModel model) {
    ProductData product = new ProductData();

    product.setCode(model.getCode());
    product.setName(model.getName());
    product.setPrice(model.getPrice());
    product.setDescription(model.getDescription());
    product.setDescriptionShort(model.getDescriptionShort());
    product.setProductStatus(model.getProductStatus().toString());

    return product;
  }

  private DiagramProductModel productModelFrom(ProductData productData) {
    DiagramProductModel productModel = modelService.create(DiagramProductModel.class);
    productModel.setCode(productData.getCode());
    productModel.setName(productData.getName());
    productModel.setPrice(productData.getPrice());
    productModel.setDescriptionShort(productData.getDescriptionShort());
    productModel.setProductStatus(DiagramProductStatus.valueOf(productData.getProductStatus()));

    return productModel;
  }

  @Autowired
  public void setDiagramProductService(DiagramProductService diagramProductService) {
    this.diagramProductService = diagramProductService;
  }

  @Autowired
  public void setModelService(ModelService modelService) {
    this.modelService = modelService;
  }
}