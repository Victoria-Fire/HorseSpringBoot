package by.academy.it.reunova.horseSpringBoot.service.implementation;

import by.academy.it.reunova.horseSpringBoot.repository.UserRepository;
import by.academy.it.reunova.horseSpringBoot.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user " + username + "!");
        }

        return new UserDetailsImpl(user);
    }
}
