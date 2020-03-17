package app.web.models;

import app.data.models.Capital;
import app.data.models.Creator;
import app.data.models.Magnitude;
import app.data.models.Mutation;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class VirusFormViewModel {

    @NotEmpty(message = "This field is mandatory!")
    @Length(min = 3, max = 10, message = "Name should be between 3 and 10 symbols!")
    private String name;

    @NotEmpty(message = "This field is mandatory!")
    @Length(min = 5, max = 100, message = "Description should be between 5 and 100 symbols!")
    private String description;

    @Max(value = 50, message = "Maximum side effects are 50!")
    private Integer sideEffects;

    @Enumerated(EnumType.STRING)
    private Creator creator;

    private boolean isDeadly;

    private boolean isCurable;

    @Enumerated(EnumType.STRING)
    private Mutation mutation;

    @NotNull(message = "This field is mandatory!")
    @Range(min = 0, max = 100, message = "Turn over rate should be between 0 and 100!")
    private Integer turnOverRate;

    @NotNull(message = "This field is mandatory!")
    @Range(min = 1, max = 12, message = "Hours until turn should be between 1 and 12!")
    private Integer hoursUntilTurn;

    @Enumerated(EnumType.STRING)
    private Magnitude magnitude;

    private Date releasedOn;

    private List<Capital> capitals;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(Integer sideEffects) {
        this.sideEffects = sideEffects;
    }

    public Creator getCreator() {
        return this.creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public boolean isDeadly() {
        return this.isDeadly;
    }

    public void setDeadly(boolean deadly) {
        isDeadly = deadly;
    }

    public boolean isCurable() {
        return this.isCurable;
    }

    public void setCurable(boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnOverRate() {
        return this.turnOverRate;
    }

    public void setTurnOverRate(Integer turnOverRate) {
        this.turnOverRate = turnOverRate;
    }

    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<Capital> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<Capital> capitals) {
        this.capitals = capitals;
    }
}
