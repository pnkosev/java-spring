package app.web.models.binding;

import app.data.models.Slot;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ItemCreateBindingModel {

    @NotEmpty(message = "Item's name is mandatory!")
    @Length(min = 3, max = 30, message = "Item name should be between 3 and 30 symbols!")
    private String name;

    @NotNull(message = "Item's slot is mandatory!")
    private Slot slot;

    @Range(min = 0, max = 100, message = "Stamina should be between 0 and 100!")
    private int stamina;

    @Range(min = 0, max = 100, message = "Strength should between 0 and 100!")
    private int strength;

    @Range(min = 0, max = 100, message = "Attack should between 0 and 100!")
    private int attack;

    @Range(min = 0, max = 100, message = "Defense should between 0 and 100!")
    private int defense;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Slot getSlot() {
        return this.slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public int getStamina() {
        return this.stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
