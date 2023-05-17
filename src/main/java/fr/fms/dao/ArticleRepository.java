package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	
public List<Article> findByBrand(String brand);
public List<Article> findByCategoryId(Long categoryId);
public List<Article> findBydescriptionContains(String description);
public List<Article> findByBrandContains(String brand);
public List<Article> findByCategoryIdAndPriceGreaterThan(Long categoryId,double minPrice);
void deleteById(Long articleId);
Article save(Article article);

public List<Article> findAllByOrderByBrandAsc();

}
