import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

abstract class Product {
    protected String name;
    protected float price;
    protected int Id;
    protected feedback feedbacks;
    protected Set<String> keywords;

    Product(String name, float price, int Id){
        this.Id=Id;
        this.name=name;
        this.price  =price;
        feedbacks = new feedback();
        keywords = new HashSet<>() {
        };
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addKeywords(String lol){
        Collections.addAll(keywords, lol.split(" "));
    }
    public Set<String> getKeywords(){
        return keywords;
    }
    public void addDescription(String n, float h) {
        review k = new review(h, n);
        feedbacks.add(k);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

}

class storedItem extends Product{
    protected int Qty;
    storedItem(String name, float price, int Id, int Qty, String kw){
        super(name, price, Id);
        this.Qty = Qty;
        Collections.addAll(this.keywords, kw.split(" "));
    }
    public int getQtity() {
        return Qty;
    }

    public void setQtity(int Qtity) {
        this.Qty = Qtity;
    }
    public void increaseQty(int x){
        this.Qty += x;
    }
    public void showReview(){
        feedbacks.show();
    }
}

class shoppingItem extends storedItem{
    private float combinedPrice;
    shoppingItem(String name, float price, int Id, int Qty) {
        super(name, price, Id, Qty, "");
        combinedPrice = Qty * price;
    }


}

