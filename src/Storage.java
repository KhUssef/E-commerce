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
        idk.get(i).showReview();
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
    void alterQtityUser(int x, int y, int z){
        if(y==1)
            idk.get(x).increaseQty(z);
        else{
            idk.get(x).setQtity(Math.max(0, idk.get(x).getQtity()-z));
            if(idk.get(x).getQtity()==0){
                idk.remove(x);
            }
        }
    }

    float checkout(){
        float total=0;
        storedItem nItem;
        for(storedItem item : idk){
            nItem = Main.Inventory.getItem(Main.Inventory.position(item));
            item.setQtity(Math.min(nItem.getQtity(),item.getQtity()));
            total+=item.getPrice()*item.getQtity();
            Main.Inventory.alterQtityUser(Main.Inventory.position(item), 2,item.getQtity());
        }
        return total;
    }
    void delete(){
        idk.clear();
    }
    void showMinimal(){
        for(storedItem si : idk){
            System.out.println(si.getQtity()+" "+si.getName()+"s for "+si.getQtity()*si.getPrice());
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
            System.out.print(i+"--");
            showItem(i, lol);
        }
    }
    float showallShopping(){
        float total = 0;
        for (int i = 0; i<idk.size(); i++) {
            System.out.print(i+"--");
            showItemShopping(i);
            total += idk.get(i).getQtity() * idk.get(i).getPrice();
        }
        return total;
    }
    int size(){
        return idk.size();
    }
    ArrayList<storedItem> filtered(float mi, float ma, int asc, String kw) {
        Set<String> kws = new HashSet<>();
        Set<String> temp;
        Collections.addAll(kws, kw.split(" "));
        ArrayList<storedItem> lol = new ArrayList<>();
        if(kw.isEmpty()){
            for (storedItem i : idk) {
                if ((i.getPrice() >= mi) && (i.getPrice() <= ma)) {
                    lol.add(i);
                }
            }
        }else {
            for (storedItem i : idk) {
                if ((i.getPrice() >= mi) && (i.getPrice() <= ma)) {
                    temp = kws;
                    temp.retainAll(i.getKeywords());
                    if (!temp.isEmpty())
                        lol.add(i);
                }
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
        idk.get(x).addReview(n, i);
    }
    void showItemShopping(int x){
        storedItem item = idk.get(x);
        System.out.print("the product "+item.getName()+"\nId number : "+item.getId()+"\n you have "+item.getQtity()+"\nin your cart.In total thisll cost"+item.getQtity()*item.getPrice());
    }
}
