package app.data.repositories;

import app.data.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
}
