package fr.spring.datajpa.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.spring.datajpa.model.AbstractUser;
import fr.spring.datajpa.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		AbstractUser user = userRepository.findByMail(mail)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with mail: " + mail));

		return UserDetailsImpl.build(user);
	}

}
