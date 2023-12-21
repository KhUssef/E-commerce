import java.util.*;
public class Storage {
    private ArrayList<storedItem> idk;
    Storage(){
        idk = new ArrayList<storedItem>();
    }
    void add_item(storedItem x){
        idk.add(x);
    }
    public boolean existing(storedItem x){
        for(storedItem i : idk){
            if(x.getId()==i.getId()&& Objects.equals(x.getName(), i.getName())){
                return true;
            }
        }
        return false;
    }
    public int position(storedItem x){
        for (int i=0; i<idk.size(); i++){
            if(x.getId()==idk.get(i).getId()&& Objects.equals(x.getName(), idk.get(i).getName())){
                return i;
            }
        }
        return 0;
    }
    void showItem(int i){
        storedItem item = idk.get(i);
        System.out.print("the product "+item.getName()+"\nId number : "+item.getId()+"\ncurrently going for : "+item.getPrice()+"\nthere are currently "+item.getQtity()+" left of this product.\n");
    }
    void showItem(int i, ArrayList<storedItem> kl){
        storedItem item = kl.get(i);
        System.out.print("the product "+item.getName()+"\nId number : "+item.getId()+"\ncurrently going for : "+item.getPrice()+"\nthere are currently "+item.getQtity()+" left of this product.\n");
    }
    void showItemDetails(int i){
        storedItem item = idk.get(i);
        System.out.println("product name : "+item.getName()+"\nId number : "+item.getId()+"\nprice : "+item.getPrice()+"\nqtity left :  "+item.getQtity()+"\nkeywords : ");
        for(String j : item.getKeywords()){
            System.out.print(j+' ');
        }
    }
    public void alterQtity(int x, int y, int z){
        if(z==1)
            idk.get(x).increaseQty(y);
        else{
            if(idk.get(x).getQtity()<=y){
                System.out.println("this product has ran out therefore it'll be removed from the Inventory");
                idk.remove(x);
                return;
            }
            idk.get(x).setQtity(idk.get(x).getQtity()-y);
        }
    }
    void changeName(int x, String j){
        idk.get(x).setName(j);
    }
    void changeId(int x, int j){
        idk.get(x).setId(j);
    }
    void changePrice(int x, float j){
        idk.get(x).setPrice(j);
    }
    void showall(){
        for(int i=0; i<idk.size(); i++){
            System.out.println(i+"--");
            showItem(i);
        }
    }
    void showall(ArrayList<storedItem> lol){
        for(int i=0; i<lol.size(); i++){
            System.out.println(i+"--");
            showItem(i, lol);
        }
    }
    int size(){
        return idk.size();
    }
    ArrayList<storedItem> filtered(float mi, float ma, int asc, String kw) {
        Set<String> kws =new HashSet<>();
        Set<String> temp;
        Collections.addAll(kws, kw.split(" "));
        ArrayList<storedItem> lol = new ArrayList<>();
        for (storedItem i : idk) {
            if ((i.getPrice() >= mi) && (i.getPrice() <= ma)) {
                temp = kws;
                temp.retainAll(i.getKeywords());
                if(!temp.isEmpty()|| kws.isEmpty())
                    lol.add(i);
            }
        }
        lol.sort((a, b) -> (asc == 1) ? (int) (a.getPrice() - b.getPrice()) : (int) (b.getPrice() - a.getPrice()));
        return lol;
    }
    int getQtity(int x){
        return idk.get(x).getQtity();
    }
    String getName(int x){
        return idk.get(x).getName();
    }
    storedItem getItem(int x){
        return idk.get(x);
    }
    void addReview(int x, float i, String n){
        storedItem item = idk.get(x);
        item.addDescription(n, i);
    }
}
