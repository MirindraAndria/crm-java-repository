package site.easy.to.build.crm.service.data;
import java.util.List;
import java.util.Set;

import site.easy.to.build.crm.repository.DataRepository;
import org.springframework.stereotype.Service;
@Service
public class DataService {
    private final DataRepository dataRepository ; 
    public DataService (DataRepository dataRepository ){ 
        this.dataRepository = dataRepository ; 
    }

    public void clearDatabase(){ 
       
        Set<String> tablesToExclude = Set.of("users", 
                                            "employee" ,
                                            "oauth_users" , 
                                            "roles" , 
                                            "user_profile" , 
                                            "user_roles" , 
                                            "customer" , 
                                            "customer_login_info");
        dataRepository.clearDatabase(tablesToExclude);
       // dataRepository.clearDatabase(Set.of());
    }
}
