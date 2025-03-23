package site.easy.to.build.crm.controller.restController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.easy.to.build.crm.repository.UserRepository;
import site.easy.to.build.crm.service.UtilService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.entity.User;

@RestController
public class LoginRestController {
    @Autowired
    private UserService userService ; 
    @Autowired 
    private UtilService utileService ; 
    // @PostMapping("/loginCheck")
    // public ResponseEntity<?> checkLogin(@RequestBody Map<String, String> loginRequest) {
    //     System.out.println("Méthode checkLogin appelée");
    //     try { 
    //         System.out.println("Requête reçue : " + loginRequest); // Debug
    
    //         String email = loginRequest.get("email");
    //         String password = loginRequest.get("password");
    
    //         User user = userService.findByEmail(email);
    
    //         if (user != null) {
    //             System.out.println("Password en base : " + user.getPassword());
    //             if (user.getPassword().equals(password)) { 
    //                 return ResponseEntity.ok(user); // Status 200 avec l'utilisateur en réponse
    //             }
    //         }
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé ou mot de passe incorrect");
    //     } catch (Exception e) { 
    //         e.printStackTrace(); 
    //         System.out.println(e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne");
    //     }
    // }
    @GetMapping("/loginCheck")
    public ResponseEntity<?> checkLogin(@RequestParam String email, @RequestParam String password) {
        System.out.println("Méthode checkLogin appelée");
        try {
            System.out.println("Email: " + email + ", Mot de passe: " + password); // Debug
            User user = userService.findByEmail(email);
            if (user != null) {
                System.out.println("Password en base : " + user.getPassword());
                String hashedPassword = utileService.md5Hash(password);
                if( hashedPassword.equals(user.getPassword())){       
                        return ResponseEntity.ok(user); // Status 200 avec l'utilisateur en réponse
                };
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé ou mot de passe incorrect");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne");
        }
    }
    
}
