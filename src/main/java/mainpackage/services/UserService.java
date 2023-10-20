package mainpackage.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mainpackage.enums.Role;
import mainpackage.models.Image;
import mainpackage.models.User;
import mainpackage.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo uRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public boolean createUser(User user, MultipartFile file){
        if (uRepo.findByEmail(user.getEmail()) != null)//почта уже используется
            return false;
        if (file.getSize() != 0) //если фото загружено
            user.setAvatar(mapper.map(file, Image.class));
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));//шифрование пароля

        log.info("Saving new User with email: {}", user.getEmail());
        uRepo.save(user);
        return true;
    }

    public List<User> getUsers(){
        return uRepo.findAll();
    }

    public void changeStatus(Long id) {
        User user = uRepo.findById(id).orElse(null);
        if (user != null){
            if (user.isActive())//менять бан/разбан
                user.setActive(false);
            else
                user.setActive(true);

            log.info("Change status for user with id = {}; email: {}", user.getId(), user.getEmail());
        }
        uRepo.save(user);//обновить статус в бд
    }

    public void changeRoles(User user, Map<String, String> form){
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());//преобразование ролей в Set строк
        user.getRoles().clear();//заменить роли на пришедшие с формы
        for (String key: form.keySet())
            if (roles.contains(key))
                user.getRoles().add(Role.valueOf(key));
        log.info("Change roles for user with id = {}; email: {}", user.getId(), user.getEmail());

        uRepo.save(user);
    }
}
