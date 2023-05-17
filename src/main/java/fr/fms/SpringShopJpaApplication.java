package fr.fms;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Category smartphone = categoryRepository.save(new Category("Smartphone"));
		// Category tablet = categoryRepository.save(new Category("Tablet"));
		// Category pc = categoryRepository.save(new Category("PC"));

		// articleRepository.save(new Article("S10","Samsung",270, smartphone));
		// articleRepository.save(new Article("Pixel 7","Google",300, smartphone));
		// articleRepository.save(new Article("IPhone 14 pro","Apple",490, smartphone));
		// articleRepository.save(new Article("12t","Xiaomi",380, smartphone));

		// articleRepository.save(new
		// Article("IPAD2","Apple",1000,categoryRepository.findById((long) 2).get()));
		// articleRepository.save(new Article("IPAD","Apple",800, tablet));
		// articleRepository.save(new Article("Android Redmi","Xiaomi",600, tablet));

		// articleRepository.save(new Article("R510","Asus",1000, pc));

		// • Exercice 1.2 : Trouver 2 moyens d’afficher sur la console un article en
		// base.

		System.out.println("Liste d'Article marque Samsung");
		for (Article articleBrand : articleRepository.findByBrand("Samsung")) {
			System.out.println(articleBrand);
		}
		System.out.println("");

		System.out.println("Liste de smartphone");
		for (Article articleSmartphone : articleRepository.findByCategoryId((long) 1)) {
			System.out.println(articleSmartphone);
		}
		System.out.println("");

		// • Exercice 1.3 : Ajouter une méthode qui permet de renvoyer tous les articles
		// contenants telle description et telle marque.

		System.out.println("Article(s) contenant(s) -I-");
		for (Article articledescription : articleRepository.findBydescriptionContains("I")) {
			System.out.println(articledescription);
		}
		System.out.println("");

		System.out.println("Liste d'Article marque Apple");
		for (Article articleBrand : articleRepository.findByBrand("Apple")) {
			System.out.println(articleBrand);
		}
		System.out.println("");

		// • Exercice 1.4 : Ajouter une méthode qui permet de supprimer un article à
		// partir de l’id

		// articleRepository.deleteById((long) 9);

		// • Exercice 1.5 : Ajouter une méthode qui permet de mettre à jour un article à
		// partir de l’id

		// Récupérer l'article à mettre à jour par ID
		Long articleId = 2L; // ID de l'article à mettre à jour
		Optional<Article> optionalArticle = articleRepository.findById(articleId);

		// Vérifier si l'article existe
		if (optionalArticle.isPresent()) {
			Article article = optionalArticle.get();

			// Modifier les propriétés de l'article
			article.setDescription("Gooogle");
			article.setBrand("Pixel 6");
			article.setPrice(999);

			// Enregistrer les modifications en utilisant la méthode save
			articleRepository.save(article);
		} else {
			System.out.println("Article non trouvé !");
		}

		System.out.println("");
		 for (Category categoriesAsc : categoryRepository.findAllByOrderByNameAsc()) {
		System.out.println(categoriesAsc);
		 }

		System.out.println("");
		 for (Category categoriesDesc : categoryRepository.findAllByOrderByNameDesc())
		 {
		 System.out.println(categoriesDesc);
		 }

		Long categoryId = 2L; // ID de la catégorie
		double minPrice = 500; // Prix minimum

		System.out.println("");
		System.out.println("Articles de la catégorie avec ID " + categoryId + " et prix supérieur à " + minPrice + ":");

		for (Article articleByCategoryIdByMinPrice : articleRepository.findByCategoryIdAndPriceGreaterThan(categoryId,
				minPrice)) {
			System.out.println(articleByCategoryIdByMinPrice);
		}
	}

}
