package app.data.repositories;

import app.data.models.Capital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, String> {
    List<Capital> findAllByOrderByName();
}
