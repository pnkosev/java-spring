package app.web.views;

import app.annotations.CapitalLetter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonFormViewModel {

    @NotEmpty(message = "Name is mandatory!")
    @CapitalLetter
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters!")
    private String name;

    @NotNull(message = "Age is mandatory!")
    @Min(value = 18, message = "You must be at least 18 years old!")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Person(Name: %s, Age: %d)", this.name, this.age);
    }
}
