package SpringLatestSecurity.TryingLatestSecurity.Controller;

import SpringLatestSecurity.TryingLatestSecurity.Model.User;
import SpringLatestSecurity.TryingLatestSecurity.ModelWrapper.UserWrapper;
import SpringLatestSecurity.TryingLatestSecurity.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    public class UserController {
        @Value("${user.authority.developer}")
        private String DEVELOPER_AUTHORITY;


        @Value("${user.authority.devops}")
        private String DEVOPS_AUTHORITY;
        @Autowired
        private UserService userService;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @PostMapping("/signup")
        public String signup(@Valid @RequestBody UserWrapper userWrapper){

//            User myUser = User.builder()
//                    .userName(userWrapper.getUserName())
//                    .password(passwordEncoder.encode(userWrapper.getPassword()))
//                    .authorities(userWrapper.getAuthorities()).email(userWrapper.getEmail()).build();
            User myUser=userWrapper.to();

        userService.signup(myUser);

//            userService.signup(userWrapper.to());
            return "success";
        }
        @GetMapping("/")
        public String home(){
            return "Welcome";
        }
        @GetMapping("/developer")
        public ResponseEntity<String> getDeveloper(){
            return new ResponseEntity<>("Hi Developer", HttpStatusCode.valueOf(200));
        }
        @GetMapping("/devops")
        public ResponseEntity<String>getDevops(){
            return new ResponseEntity<>("Hi Devops",HttpStatusCode.valueOf(200));
        }
        @GetMapping("/both")
        public ResponseEntity<String>getBoth(){
            return new ResponseEntity<>("Hi Both",HttpStatusCode.valueOf(200));
        }
    }


