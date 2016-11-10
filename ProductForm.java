package de.hybris.platform.crudtrail.facades.forms;

import de.hybris.platform.crudtrail.validation.ValidProductCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Panayot Kulchev on 07.11.16.
 * e-mail: panayotkulchev@gmail.com
 * happy codding ...
 */
public class ProductForm {

  @ValidProductCode
  private String code;
  @NotNull(message = "Name can not be empty")
  @Size(min = 5, message = "Name must be at least 5 symbols")
  private String name;
  @NotNull (message = "Price can not be empty")
  private Double price;
  private String description;
  private String descriptionShort;
  private String productStatus;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescriptionShort() {
    return descriptionShort;
  }

  public void setDescriptionShort(String descriptionShort) {
    this.descriptionShort = descriptionShort;
  }

  public String getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(String productStatus) {
    this.productStatus = productStatus;
  }

  @Override
  public String toString() {
    return "ProductForm{" +
            "code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", description='" + description + '\'' +
            ", descriptionShort='" + descriptionShort + '\'' +
            ", productStatus='" + productStatus + '\'' +
            '}';
  }
}