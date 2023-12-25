import java.util.*;
public class AdminDatabase {
    private ArrayList<admin> idk;
    AdminDatabase(){
        idk = new ArrayList<>();
    }
    void addUser(admin a){
        idk.add(a);
    }
    char existing (admin a){
        for(admin i : idk){
            if(a.getUserName().equals(i.getUserName())){
                return 'u';
            }if(a.getMail().equals(i.getMail())){
                return 'm';
            }
        }
        return 'n';
    }
    admin login(String mail, String password){
        for(admin i : idk){
            if(i.getMail().equals(mail)&&i.RightPassword_(password)){
                return i;
            }
        }
        return null;
    }
    void add(admin user){
        idk.add(user);
    }
}

