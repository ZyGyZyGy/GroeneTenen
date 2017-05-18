package be.vdab.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private static final String CEO = "ceo";
    private static final String MANAGER = "manager";
    private static final String HELPDESKMEDEWERKER = "helpdeskmedewerker";
    private static final String MAGAZIJNIER = "magazijnier";
    
    private static final String USERS_BY_USERNAME = 
	    "select naam as username, paswoord as password, actief as enabled " +  
	    "from gebruikers where naam = ?";
    private static final String AUTHORITIES_BY_USERNAME = 
	    "select gebruikers.naam as username, rollen.naam as authorities " + 
	    "from gebruikers inner join gebruikersrollen " +  
	    "on gebruikers.id = gebruikersrollen.gebruikerid " + 
	    "inner join rollen " + 
	    "on rollen.id = gebruikersrollen.rolid " + 
	    "where gebruikers.naam= ?"; 
    
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
	this.dataSource = dataSource;
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(USERS_BY_USERNAME)
		.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME)
		.passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring()
		.antMatchers("/images/**")
		.antMatchers("/styles/**")
		.antMatchers("/scripts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.formLogin().loginPage("/login")
		.and().logout().logoutSuccessUrl("/")
    		.and().authorizeRequests()
    		.antMatchers("/filialen/toevoegen", "/filialen/*/wijzigen", "/filialen/*/verwijderen").hasAnyAuthority(MANAGER, CEO)
    		.antMatchers(HttpMethod.POST, "/filialen").hasAnyAuthority(MANAGER, CEO)
    		.antMatchers("/werknemers").hasAnyAuthority(MAGAZIJNIER, HELPDESKMEDEWERKER, CEO)
    		.antMatchers("/", "/login").permitAll()  
    		.antMatchers("/**").authenticated()
    		.and().exceptionHandling().accessDeniedPage("/WEB-INF/JSP/forbidden.jsp");
    }
    
    
    
}
