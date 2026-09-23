package dev.triacontakaihenagon.testsstudy.repository;

import dev.triacontakaihenagon.testsstudy.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {}