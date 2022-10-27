package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.security;

import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Person;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Roles;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UsernamePwdAuthetificationProvider implements AuthenticationProvider {

   @Autowired
   private PersonRepository personRepository ;


   @Autowired
   private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        Person person = personRepository.readByEmail(email);
        if(null != person && person.getPersonId()>0 &&
                // password Encoder for matching entered hashed pass with db pass
                passwordEncoder.matches(pwd, person.getPwd())){

            return new UsernamePasswordAuthenticationToken(
                        // kod je zodpovedny za zobrazenie getname() v dashboarde napr...
                    email, pwd, getGrantedAuthorities(person.getRoles()));
        }else{
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
