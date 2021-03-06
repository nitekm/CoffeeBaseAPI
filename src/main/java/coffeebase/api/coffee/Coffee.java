package coffeebase.api.coffee;

import coffeebase.api.audit.Audit;
import coffeebase.api.coffeegroup.CoffeeGroup;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coffees")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String origin;
    private String roaster;
    private int rating;
    private String imageUrl;
    private boolean favourite;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "coffee_coffee_group",
            joinColumns = { @JoinColumn(name = "coffee_id") },
            inverseJoinColumns = { @JoinColumn(name = "coffee_group_id") }
    )
    private Set<CoffeeGroup> coffeeGroups = new HashSet<>();
    @Embedded
    private Audit audit = new Audit();

    public Coffee() {
    }

    Coffee(final String name, final String origin, final String roaster, final int rating, final String imageUrl) {
        this.name = name;
        this.origin = origin;
        this.roaster = roaster;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getRoaster() {
        return roaster;
    }

    public int getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isFavourite() {
        return favourite;
    }

    void setFavourite(final boolean favourite) {
        this.favourite = favourite;
    }

    Set<CoffeeGroup> getCoffeeGroups() {
        return coffeeGroups;
    }

    void setCoffeeGroups(final Set<CoffeeGroup> coffeeGroups) {
        this.coffeeGroups = coffeeGroups;
    }

    void updateCoffee(CoffeeDTO updatedCoffee) {
        name = updatedCoffee.getName();
        origin = updatedCoffee.getOrigin();
        roaster = updatedCoffee.getRoaster();
        rating = updatedCoffee.getRating();
        imageUrl = updatedCoffee.getImageUrl();
    }
}
