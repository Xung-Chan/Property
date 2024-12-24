package Property.Property.configuration;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.experimental.NonFinal;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
	@NonFinal
	@Value("${project.singer-key}")
	String key;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		boolean flag = false;
		return httpSecurity
				.oauth2ResourceServer(oauth -> oauth.jwt(configurer -> configurer.decoder(jwtDecoder())))
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(request -> request
						.anyRequest()
						.permitAll())
//				.formLogin(t -> t.loginPage("/login").successHandler((request, response, authentication) -> {
//					if (flag)
//						request.getRequestDispatcher("/").forward(request, response);
//					else
//						request.getRequestDispatcher("/dashboard").forward(request, response);
//
//				})
//						.failureHandler()
//						.defaultSuccessUrl("/").permitAll())
				.logout(logout -> logout
						.deleteCookies())
				.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(key.getBytes(), MacAlgorithm.HS512.getName()))
				.macAlgorithm(MacAlgorithm.HS512).build();
	}

}
