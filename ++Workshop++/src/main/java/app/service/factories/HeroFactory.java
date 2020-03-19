package app.service.factories;

import app.data.models.Gender;
import app.data.models.Hero;

public interface HeroFactory {
    Hero create(String name, Gender gender);
}
