import java.util.*;
import java.lang.Math;
public class Main {
    static customerDatabase customers = new customerDatabase();
    static AdminDatabase Admins = new AdminDatabase();
    static feedback appFeedback = new feedback();
    static Storage Inventory = new Storage();
    static Coupons couponDatabase = new Coupons();

    public static void mainWindow() {
        int x;
        float p;
        String o;
        System.out.println("would you like to : \nlogin as : \n\t 1--customer \n\t 2--admin \n 3--create new account \n 4-- rate us");
        Scanner Sc = new Scanner(System.in);
        x = Sc.nextInt();
        Sc.nextLine();
        switch (x) {
            case 1:
                customerLogin();
                break;
            case 2 :
                adminLogin();
                break;
            case 3 :
                System.out.println("are you a : \n\t 1--admin \n\t 2--customer :");
                x = Sc.nextInt();
                Sc.nextLine();
                switch(x){
                    case 1 : createAdmin();break;
                    case 2 : createCustomer();break;
                }
                break;
            case 4 :
                System.out.println("how many stars would you like to give us");
                p = Sc.nextFloat();
                Sc.nextLine();
                System.out.println("how can we improve ur user experience : ");
                o = Sc.nextLine();
                System.out.println("we appreciate the feedback and will try to work towards satisfying our customerDatabases");
                review a = new review(p, o);
                appFeedback.add(a);
                break;
        }
    }
    public static void customerLogin(){
        String y, z;
        int u;
        customer user;
        Scanner Sc = new Scanner(System.in);
        while (true) {
            System.out.println("enter mail : ");
            y = Sc.nextLine();
            System.out.println("enter password : ");
            z = Sc.nextLine();
            user =customers.login(y, z);
            if (user !=null){
                customerWindow(user);
                return;
            }
            else{
                System.out.println("wrong account info : \n\t 1--return to main window \n\t 2--try again");
                u = Sc.nextInt();
                Sc.nextLine();
                if (u == 1) {
                    return;
                }
            }
        }
    }
    public static void customerWindow(customer user){
        while (true) {
            System.out.println("hello ," + user.getUserName() + " what would you like to do today : \n\t1--view shopping cart" +
                    "\n\t2--view available products\n\t3--add coupons"+
                    "\n\t4--search for specific product\n\t5--view History" +
                    "\n\t6--logout");
            int x;
            Scanner Sc = new Scanner(System.in);
            x = Sc.nextInt();
            Sc.nextLine();
            switch (x) {
                case 1:
                    viewShoppingCart(user);
                    break;
                case 2:
                    showItems(user);
                    break;
                case 3:
                    addCouponCode(user);
                    break;
                case 4:
                    search(user);
                    break;
                case 6 :
                    return;
                case 5 :
                    viewHistory(user);
                    break;
            }

        }
    }
    static void viewShoppingCart(customer user){
        float total= user.showShopping();
        System.out.println("the total comes up to :"+total);
        total -= user.ShowCoupons();
        System.out.println("after discount your total  is :"+Math.max(0, total));
        System.out.println("\n\t"+user.shoppingCartSize()+"--checkout");
        System.out.println("\n\t"+(user.shoppingCartSize()+1)+"--go back");
        int x;
        Scanner Sc = new Scanner(System.in);
        x=Sc.nextInt();
        Sc.nextLine();
        if(x==user.shoppingCartSize()){
            checkout(user);
            return;
        }
        if(x==user.shoppingCartSize()+1)
            return;
        alterItemCustomer(user, x);
    }
    static void checkout(customer user){
        float total=user.checkout();
        System.out.println("ur total comes out to"+total);
        Scanner Sc = new Scanner(System.in);
        System.out.println("enter credit card number");
        Sc.nextLine();
        System.out.println("enter security number");
        Sc.nextLine();
        System.out.println("congrats u have checked out");
        user.deleteShoppingCart();
    }
    static void alterItemCustomer(customer user, int x){
        user.showItem(x);
        System.out.println("would you like to\n\t1--increase qtity\n\t2--decrease qtity");
        int z, y;
        Scanner Sc = new Scanner(System.in);
        y =Sc.nextInt();
        Sc.nextLine();
        System.out.println("by how much");
        z = Sc.nextInt();
        Sc.nextLine();
        user.alterItem(x, y, z);
        user.showItem(x);
    }
    static void showItems(customer user){
        Scanner Sc = new Scanner(System.in);
        boolean idk = true;
        int x, y=1;
        float a=0, b=2147483647;
        StringBuilder z = new StringBuilder();
        while(idk) {
            System.out.println("would you like to add filters?\n\t1--add maximum price\n\t2--add minimum price\n\t3--add keywords\n\t4--order the result" +
                    "\n\t5--show result");
            x = Sc.nextInt();
            Sc.nextLine();
            switch(x){
                case 1 :
                    System.out.println("input price : ");
                    b = Sc.nextInt();
                    Sc.nextLine();
                    break;
                case 2 :
                    System.out.println("input price : ");
                    a = Sc.nextInt();
                    Sc.nextLine();
                    break;
                case 3 :
                    System.out.println("enter keywords (if u want to enter a keyword consisting of multiple words use _) :");
                    z.append(Sc.nextLine());
                    break;
                case 4 :
                    System.out.println("would you like the price to be \n\t1--ascending\n\t2--descending");
                    y = Sc.nextInt();
                    Sc.nextLine();
                    break;
                case 5 :
                    idk=false;
            }
        }
        while(true) {
            ArrayList<storedItem> k = Inventory.filtered(a, b, y, z.toString());
            Inventory.showall(k);
            System.out.println(k.size() + "--go back to main window\n");
            x = Sc.nextInt();
            Sc.nextLine();
            if (x == k.size()) {
                return;
            }
            x = Inventory.position(k.get(x));
            Inventory.showItemDetails(x);
            System.out.println("\t1--add to shopping cart\n\t2--add review of product\n\t3--return to last window");
            y=Sc.nextInt();
            Sc.nextLine();
            switch (y){
                case 1 :
                    System.out.println("how many would you like to add?");
                    y = Sc.nextInt();
                    Sc.nextLine();
                    y = Math.min(y, Inventory.getQtity(x));
                    System.out.println("successfully added "+y+" "+Inventory.getName(x)+"s to the shopping cart");
                    user.addShopingCart(Inventory.getItem(x), y);
                    break;
                case 2 :
                    addReview(x);
                    break;
                case 3 :
                    continue;
            }

        }
    }
    static void addCouponCode(customer user){
        Scanner Sc = new Scanner(System.in);
        System.out.println("enter coupon code");
        int x = Sc.nextInt();
        Sc.nextLine();
        Coupon c = couponDatabase.searchCoupon(x);
        if(c!=null){
            user.addCoupon(x);
            System.out.println("Coupon code added successfully.\ncoupon code: "+x+"\ndiscount: "+c.getDiscount());
        }
    }
    static void search(customer user){
        Scanner Sc = new Scanner(System.in);
        int x, u;
        String y;
        System.out.println("enter the product name:");
        y = Sc.nextLine();
        System.out.println("enter the product Id:");
        x = Sc.nextInt();
        Sc.nextLine();
        storedItem si = new storedItem(y, 0, x, 1, "");
        if(!Inventory.existing(si)){
            System.out.println("the product doesnt exist");
        }else {
            x = Inventory.position(si);
            Inventory.showItem(x);
            System.out.println("what would you like to do exactly\n\t1--add review\n\t2--add to basket");
            u = Sc.nextInt();
            Sc.nextLine();
            if(u==1){
                addReview(x);
                return;
            }
            System.out.println("how many would you like to add : ");
            u = Sc.nextInt();
            Sc.nextLine();
            si.setQtity(u);
            user.addShopingCart(si, u);
            System.out.println("items added successfully");
        }
    }
    static void viewHistory(customer user){
        user.showOrder();
    }
    public static void adminLogin(){
        String y , z;
        int u;
        admin user;
        Scanner Sc = new Scanner(System.in);
        while (true) {
            y = null;z=null;
            System.out.println("enter mail : ");
            y = Sc.nextLine();
            System.out.println("enter password : ");
            z = Sc.nextLine();
            user =Admins.login(y, z);
            if (user !=null){
                adminWindow(user);
                return;
            }
            else{
                System.out.println("wrong account info : \n\t 1--return to main window \n\t 2--try again");
                u = Sc.nextInt();
                Sc.nextLine();
                if (u == 1) {
                    return;
                }
            }
        }
    }
    public static void adminWindow(admin user) {
        while (true) {
            System.out.println("hello ," + user.getUserName() + " what would you like to do today : \n\t1--add a new Product" +
                    "\n\t2--increase the qtity of an already existing product\n\t3--view existing products" +
                    "\n\t4--add coupon\n\t5--logout");
            int x;
            Scanner Sc = new Scanner(System.in);
            x = Sc.nextInt();
                Sc.nextLine();
            switch (x) {
                case 1:
                    addingItem();
                    break;
                case 2:
                    alterItem();
                    break;
                case 3:
                    showItemsAdmin();
                    break;
                case 5 :
                    return;
                case 4:
                    addCouponAdmin();
            }

        }
    }

    static void addCouponAdmin(){
        Scanner Sc = new Scanner(System.in);
        int c ;
        float k;
        System.out.println("enter coupon code");
        c = Sc.nextInt();
        Sc.nextLine();
        System.out.println("enter discount amount ");
        k = Sc.nextFloat();
        Sc.nextLine();
        couponDatabase.addCoupon(c, k);
    }

    static void addingItem(){
        Scanner Sc = new Scanner(System.in);
        int x, t;
        float p;
        String y, u;
        System.out.println("enter the product name:");
        y = Sc.nextLine();
        System.out.println("enter the product Id:");
        x = Sc.nextInt();
        Sc.nextLine();
        System.out.println("enter the product price:");
        p = Sc.nextFloat();
        Sc.nextLine();
        System.out.println("enter the product Qtity:");
        t = Sc.nextInt();
        Sc.nextLine();
        System.out.println("enter the keywords");
        u = Sc.nextLine();
        storedItem si = new storedItem(y, p, x, t, u);
        if(Inventory.existing(si)){
            System.out.println("this product already exists in the inventory, there are "+si.getQtity()+" left of it and its price is "+si.getPrice()+"\n");
        }else{
            Inventory.add_item(si);
        }
    }
    static void alterItem(){
        Scanner Sc = new Scanner(System.in);
        int x, u;
        String y;
        float op;
        System.out.println("enter the product name:");
        y = Sc.nextLine();
        System.out.println("enter the product Id:");
        x = Sc.nextInt();
                Sc.nextLine();
        storedItem si = new storedItem(y, 0, x, 1, "");
        if(!Inventory.existing(si)){
            System.out.println("the product doesnt exist you might wanna create it alltogether\n");
        }else{
            x = Inventory.position(si);
            Inventory.showItem(x);
            System.out.println("what would you like to do exactly\n\t1--change its name\n\t2--change its Id\n\t3--change the price" +
                    "\n\t4--increase/decrease product qtity");
            u = Sc.nextInt();
                Sc.nextLine();
            switch(u) {
                case 1:
                    while (true) {
                        System.out.println("enter the new name");
                        y = Sc.nextLine();
                        si.setName(y);
                        if (Inventory.existing(si)) {
                            System.out.println("a product with the same characteristics already exists please enter a valid name");
                        } else {
                            break;
                        }
                    }
                    Inventory.changeName(x, y);
                    System.out.println("product Name changed successfully");
                    break;
                case 2:
                    while (true) {
                        System.out.println("enter the new Id");
                        u = Sc.nextInt();
                Sc.nextLine();
                        si.setId(u);
                        if (Inventory.existing(si)) {
                            System.out.println("a product with the same characteristics already exists please enter a valid Id");
                        } else {
                            break;
                        }
                    }
                    Inventory.changeId(x, u);
                    System.out.println("product Id changed successfully");
                    break;
                case 3 :
                    System.out.println("enter the new Price ");
                    op = Sc.nextFloat();
                    Sc.nextLine();
                    Inventory.changePrice(x, op);
                    System.out.println("product price changed successfully");
                    break;
                case 4 :
                    System.out.println("would u like to:\n\t1--increase qtity\n\t2--decrease qtity");
                    u = Sc.nextInt();
                    Sc.nextLine();
                    System.out.println("and by how much :");
                    op = Sc.nextInt();
                    Sc.nextLine();
                    Inventory.alterQtity(x, (int) op, u);
                    break;
            }
        }
    }
    static void showItemsAdmin(){
        Scanner Sc = new Scanner(System.in);
        boolean idk = true;
        int x, y=1;
        float a=0, b=2147483647;
        StringBuilder z = new StringBuilder();
        while(idk) {
            System.out.println("would you like to add filters?\n\t1--add maximum price\n\t2--add minimum price\n\t3--add keywords\n\t4--order the result" +
                    "\n\t5--show result");
            x = Sc.nextInt();
                Sc.nextLine();
            switch(x){
                case 1 :
                    System.out.println("input price : ");
                    a = Sc.nextInt();
                Sc.nextLine();
                    break;
                case 2 :
                    System.out.println("input price : ");
                    b = Sc.nextInt();
                Sc.nextLine();
                    break;
                case 3 :
                    System.out.println("enter keywords (if u want to enter a keyword consisting of multiple words use _) :");
                    z.append(" ");
                    z.append(Sc.nextLine());
                    break;
                case 4 :
                    System.out.println("would you like the price to be \n\t1--ascending\n\t2--descending");
                    y = Sc.nextInt();
                Sc.nextLine();
                    break;
                case 5 :
                    idk=false;
            }
        }
        ArrayList<storedItem> k = Inventory.filtered(a, b, y, z.toString());
        Inventory.showall(k);

    }
    static void addReview(int x){
        Scanner Sc = new Scanner(System.in);
        String s;
        float idk;
        System.out.println("how many stars would you like to give : ");
        idk = Sc.nextFloat();
        Sc.nextLine();
        System.out.println("any additional comment to add :");
        s= Sc.nextLine();
        idk = Math.min(idk, 5);
        Inventory.addReview(x, idk, s);
    }
    public static void createAdmin() {
        String y, z, t;
        int x;
        Scanner Sc = new Scanner(System.in);
        while (true) {
            System.out.println("enter mail : ");
            y = Sc.nextLine();
            System.out.println("enter username : ");
            z = Sc.nextLine();
            System.out.println("enter password : ");
            t = Sc.nextLine();
            admin a = new admin(y, t, z);
            switch (Admins.existing(a)) {
                case 'm' :
                    System.out.println("mail already in use : would you like to \n\t 1--retry \n\t 2--return to main window");
                    x = Sc.nextInt();
                Sc.nextLine();
                    switch (x){
                        case 1 : continue;
                        case 2 : return;
                    }
                case 'u' :
                    System.out.println("username already in use : would you like to \n\t 1--retry \n\t 2--return to main window");
                    x = Sc.nextInt();
                Sc.nextLine();
                    switch (x){
                        case 1 : continue;
                        case 2 : return;
                    }
                case 'n' :
                    System.out.println("congrats "+z+", u have successfully created your account");
                    Admins.add(a);
                    return;
            }
        }
    }

    public static void createCustomer() {
        String y, z, t;
        int x;
        Scanner Sc = new Scanner(System.in);
        while (true) {
            System.out.println("enter mail : ");
            y = Sc.nextLine();
            System.out.println("enter username : ");
            z = Sc.nextLine();
            System.out.println("enter password : ");
            t = Sc.nextLine();
            customer a = new customer(y, t, z);
            switch (customers.existing(a)) {
                case 'm' :
                    System.out.println("mail already in use : would you like to \n\t 1--retry \n\t 2--return to main window");
                    x = Sc.nextInt();
                Sc.nextLine();
                    switch (x){
                        case 1 : continue;
                        case 2 : return;
                    }
                case 'u' :
                    System.out.println("username already in use : would you like to \n\t 1--retry \n\t 2--return to main window");
                    x = Sc.nextInt();
                Sc.nextLine();
                    switch (x){
                        case 1 : continue;
                        case 2 : return;
                    }
                case 'n' :
                    System.out.println("congrats "+z+", u have successfully created your account");
                    customers.add(a);
                    return;
            }
        }
    }

    public static void main(String[] args) {
        while(true)
            mainWindow();

    }
}