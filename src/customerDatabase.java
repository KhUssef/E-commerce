import java.util.*;
public class customerDatabase {
    private ArrayList<customer> idk;
    customerDatabase(){
        idk = new ArrayList<>();
    }
    void addCustomer(customer a){
        idk.add(a);
    }
    char existing (customer a){
        for(customer i : idk){
            if(a.getUserName().equals(i.getUserName())){
                return 'u';
            }if(a.getMail().equals(i.getMail())){
                return 'm';
            }
        }
        return 'n';
    }
    customer login(String mail, String password){
        for(customer i : idk){
            if(i.getMail().equals(mail)&&i.RightPassword_(password)){
                return i;
            }
        }
        return null;
    }
    void add(customer user){
        idk.add(user);
    }
}
