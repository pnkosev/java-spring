package pn.app.web.models.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OfferFindBindingModel {

    @NotNull
    @Min(0)
    private BigDecimal familyBudget;

    @NotNull
    @Length(min = 2, max = 30)
    private String familyApartmentType;

    @NotNull
    @Length(min = 3, max = 30)
    private String familyName;

    public BigDecimal getFamilyBudget() {
        return familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    public String getFamilyApartmentType() {
        return familyApartmentType;
    }

    public void setFamilyApartmentType(String familyApartmentType) {
        this.familyApartmentType = familyApartmentType;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
