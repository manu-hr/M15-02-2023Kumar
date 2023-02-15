package com.challege.javatask;

import com.challege.javatask.domain.Review;
import com.challege.javatask.domain.User;
import com.challege.javatask.filter.JwtFilter;
import com.challege.javatask.repository.ReviewRepository;
import com.challege.javatask.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
public class JavaTaskApplication {

	@Bean
	public FilterRegistrationBean filterRegistrationBean()
	{
		final CorsConfiguration config= new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",config);
		FilterRegistrationBean bean=new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaTaskApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(UserRepository userRepository, ReviewRepository reviewRepository) {
		return args -> {
			User user = new User();
			user.setUsername("user");
			user.setPassword("password");

			User userResp = userRepository.save(user);
			System.out.println(userResp);

			Review review = new Review();
			review.setRating(4);
			review.setReviewTitle("First Review");
			review.setReviewDescription("Some Description");
			reviewRepository.save(review);

		};
	}

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/service/v1/review/*");
		return filterRegistrationBean;

	}



}
