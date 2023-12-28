import java.util.ArrayList;

class Coupon {
    private int code;
    private float discount;

    public Coupon(int code, float discount) {
        this.code = code;
        this.discount = discount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}

public class Coupons{
    private ArrayList<Coupon> idk;
    Coupons(){
        idk = new ArrayList<>();
    }
    void addCoupon(int code, float discount){
        for (Coupon i : idk){
            if(i.getCode()==code){
                i.setDiscount(discount);
                return;
            }
        }
        Coupon l = new Coupon(code, discount);
        idk.add(l);
    }
    void addCoupon(Coupon c){
        int code = c.getCode();
        float discount = c.getDiscount();
        for (Coupon i : idk){
            if(i.getCode()==code){
                i.setDiscount(discount);
                return;
            }
        }
        idk.add(c);
    }
    Coupon searchCoupon(int x){
        for(Coupon i : idk){
            if(i.getCode()==x){
                return i;
            }
        }
        return null;
    }

    void delete(int i){
        idk.remove(this.searchCoupon(i));
    }
}
