import java.util.*;
class User {
    protected String mail;
    protected String password;
    protected String UserName;

    public User(String mail, String password, String UserName) {
        this.mail = mail;
        this.password = password;
        this.UserName = UserName;

    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean RightPassword(User a) {
        return a.RightPassword_(password);
    }
    public boolean RightPassword_(String password){
        return Objects.equals(this.password, password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
class customer extends User{
    private feedback feedbacks;
    private Storage shoppingCart;
    private ArrayList<Integer> couponCodes;
    customer(String mail, String password, String UserName) {
        super(mail, password, UserName);
        feedbacks = new feedback();
        shoppingCart = new Storage();
        couponCodes = new ArrayList<>();

    }
    void addShopingCart(storedItem item, int x){
        storedItem nItem = new storedItem(item.getName(), item.getPrice(), item.getId(), x);
        shoppingCart.add_item(nItem);
    }
    float showShopping(){
        return shoppingCart.showallShopping();
    }
    void addCoupon(int x){
        couponCodes.add(x);
    }
    int shoppingCartSize(){
        return shoppingCart.size();
    }
    float ShowCoupons(){
        float total=0;
        Coupon idk ;
        for(int i : couponCodes){
            idk = Main.coupondatabase.searchCoupon(i);
            if(idk==null){
                couponCodes.remove(i);
            }else{
                System.out.println("Coupon code : "+idk.getCode()+"\n discount : "+idk.getDiscount());
                total+=idk.getDiscount();
            }
        }
        return total;
    }
    void showItem(int x){
        shoppingCart.showItem(x);
    }
    void alterItem(int x, int y, int z){
            shoppingCart.alterQtity(x, y, z);
    }
}
class admin extends User{
    admin(String mail, String password, String UserName){
        super(mail, password, UserName);
    }
}

