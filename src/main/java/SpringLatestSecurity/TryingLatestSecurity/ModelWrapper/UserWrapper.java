package SpringLatestSecurity.TryingLatestSecurity.ModelWrapper;

import SpringLatestSecurity.TryingLatestSecurity.Model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserWrapper {

    @NotNull
    private String userName;
    @NotNull
    private String password;

    private String email;
    private String authorities;
public User to(){
    return User.builder().userName(userName).password(new BCryptPasswordEncoder().encode(password)).email(email).authorities(authorities).build();
}

}

