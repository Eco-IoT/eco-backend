package de.robinmucha.ecobackend.security;

import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Configuration
@RestController
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    private UserRepository userRepository;

    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureAuthenticatedUsers(AuthenticationManagerBuilder auth) {
        List<User> users = getUsers();
        allowUsers(auth, users);
        removeUserRepository();
    }

    private List<User> getUsers() {
        return userRepository.findAll();
    }

    private void allowUsers(AuthenticationManagerBuilder auth, List<User> users) {
        log.info("Setting up authentication for user(s): " + getUserNamesAsCsv(users));

        for (User user : users) {
            try {
                allowUser(auth, user);
            } catch (Exception e) {
                log.error("Couldn't add authentication for user: " + user.getName());
            }
        }
    }

    private String getUserNamesAsCsv(List<User> users) {
        StringBuilder userNames = new StringBuilder();
        users.forEach(user -> userNames.append(user.getName()).append(", "));
        userNames.delete(userNames.length() - 2, userNames.length() - 1);
        return userNames.toString();
    }

    private void allowUser(AuthenticationManagerBuilder auth, User user) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user.getName())
                .password("{noop}" + user.getPassword())
                .roles("USER");
    }

    private void removeUserRepository() {
        userRepository = null;
    }

}
