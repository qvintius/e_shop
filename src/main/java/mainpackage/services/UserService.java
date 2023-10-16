package mainpackage.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mainpackage.enums.Role;
import mainpackage.models.User;
import mainpackage.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo uRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        if (uRepo.findByEmail(user.getEmail()) != null)//почта уже используется
            return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));//шифрование пароля
        user.getRoles().add(Role.ROLE_USER);

        log.info("Saving new User with email: {}", user.getEmail());
        uRepo.save(user);
        return true;
    }
}
