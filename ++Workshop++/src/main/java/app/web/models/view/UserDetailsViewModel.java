package app.web.models.view;

public class UserDetailsViewModel {

    private String username;

    private String email;

    private HeroViewModel hero;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HeroViewModel getHero() {
        return this.hero;
    }

    public void setHero(HeroViewModel hero) {
        this.hero = hero;
    }
}
