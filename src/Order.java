import java.util.ArrayList;

public class Order {
    private ArrayList<Storage> orders;
    private Coupons couponsList;

    public Order() {
        orders = new ArrayList<>();
        couponsList = new Coupons();
    }

    public ArrayList<Storage> getOrders() {
        return orders;
    }
    public void setOrders(ArrayList<Storage> orders) {
        this.orders = orders;
    }
    public Coupons getCouponsList() {
        return couponsList;
    }

    public void addOrder(Storage order) {
        orders.add(order);
    }

    public void removeOrder(Storage order) {
        orders.remove(order);
    }
    public void addCoupon(Coupon coupon) {
        couponsList.addCoupon(coupon);
    }
    public void addCoupon(int code, float discount) {
        couponsList.addCoupon(code, discount);
    }
    void show(){
        for(Storage s : orders){
            s.showMinimal();
        }
    }
}


