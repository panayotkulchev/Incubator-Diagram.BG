package de.hybris.platform.crudtrail.validation;

import de.hybris.platform.crudtrail.model.DiagramProductModel;
import de.hybris.platform.crudtrail.service.DiagramProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Created by Panayot Kulchev on 08.11.16.
 * e-mail: panayotkulchev@gmail.com
 * happy codding ...
 */
public class ProductCodeValidator implements ConstraintValidator<ValidProductCode, String> {

  private static final String PRODUCT_CODE_EXISTS = "There is product with this code";
  private static final String PRODUCT_CODE_IS_EMPTY = "Code can not be empty";

  private DiagramProductService diagramProductService;

  public ProductCodeValidator() {
  }

  @Override
  public void initialize(ValidProductCode validProductCode) {

  }

  @Override
  public boolean isValid(String code, ConstraintValidatorContext context) {

//  PRODUCT CODE IS NULL OR EMPTY
    if (Objects.isNull(code) || code.isEmpty()) {
      flush(context, PRODUCT_CODE_IS_EMPTY);
      return false;
    }

//  THERE IS PRODUCT WITH SAME CODE
    DiagramProductModel product = diagramProductService.getProductByCode(code);

    if (Objects.nonNull(product)) {
      flush(context, PRODUCT_CODE_EXISTS);
      return false;
    }

    return true;
  }

  @Autowired
  public void setDiagramProductService(DiagramProductService diagramProductService) {
    this.diagramProductService = diagramProductService;
  }

  private void flush(ConstraintValidatorContext context, String message) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation();
  }
}