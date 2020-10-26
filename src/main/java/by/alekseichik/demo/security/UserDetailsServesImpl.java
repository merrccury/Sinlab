package by.alekseichik.demo.security;

import by.alekseichik.demo.model.User;
import by.alekseichik.demo.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServesImpl implements UserDetailsService {

    private final UserRepository userRepository;
    final static Logger logger = Logger.getLogger(UserDetailsServesImpl.class);

    @Autowired
    public UserDetailsServesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
                    logger.info("User doesn't exist");
                    throw new UsernameNotFoundException("User doesn't exist");
                }
        );
        return SecurityUser.fromUser(user);
    }
}
