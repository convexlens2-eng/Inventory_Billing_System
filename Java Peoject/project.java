import java.util.*;

class Product {
    private int key;
    private String label;
    private double value;
    private int count;

    Product(int key, String label, double value, int count){
        this.key = key;
        this.label = label;
        this.value = value;
        this.count = count;
    }

    public int getKey(){ return key; }
    public String getLabel(){ return label; }
    public double getValue(){ return value; }
    public int getCount(){ return count; }

    public void update(String label, double value){
        this.label = label;
        this.value = value;
    }

    public void inc(int value){
        count += value;
    }

    public boolean dec(int value){
        if(value <= count && value >= 0){
            count -= value;
            return true;
        }
        return false;
    }

    public void show(){
        System.out.printf("%-5d %-12s %-10.2f %-5d", key, label, value, count);
        int t = 3 + new Random().nextInt(3);
        if(count < t){
            System.out.print("  (running low)");
        }
        System.out.println();
    }
}

class Inventory {
    HashMap<Integer, Product> map;
    Random r = new Random();

    Inventory(){
        map = new HashMap<>();
    }

    void add(Product p){
        if(map.containsKey(p.getKey())){
            System.out.println("Hmm, that product ID already exists.");
            return;
        }
        map.put(p.getKey(), p);
        String[] bound = {"Product added successfully.", "Nice, it's now in the inventory.", "All set, product stored."};
        System.out.println(bound[r.nextInt(bound.length)]);
    }

    void remove(int kdx){
        if(map.containsKey(kdx)){
            map.remove(kdx);
            String[] bound = {"Product removed.", "It's gone from the list.", "Removed successfully."};
            System.out.println(bound[r.nextInt(bound.length)]);
        } else {
            System.out.println("Couldn't find that product.");
        }
    }

    Product get(int kdx){
        return map.get(kdx);
    }

    void search(int kdx){
        if(map.containsKey(kdx)){
            String[] msgs = {"Here’s what I found:", "Got it:", "Found this for you:"};
            System.out.println(msgs[r.nextInt(msgs.length)]);
            System.out.println("ID   Name         Price      Stock");
            map.get(kdx).show();
        } else {
            System.out.println("No product with that ID.");
        }
    }

    void filterRange(double a, double b){
        System.out.println("Showing products in that price range:");
        System.out.println("ID   Name         Price      Stock");
        for(Product p : map.values()){
            if(p.getValue() >= a && p.getValue() <= b){
                p.show();
            }
        }
    }

    void low(){
        System.out.println("These items might run out soon:");
        System.out.println("ID   Name         Price      Stock");
        for(Product p : map.values()){
            if(p.getCount() < 5){
                p.show();
            }
        }
    }

    void update(int kdx, String s, double v){
        if(map.containsKey(kdx)){
            map.get(kdx).update(s, v);
            String[] msgs = {"Updated successfully.", "Changes saved.", "All updated."};
            System.out.println(msgs[r.nextInt(msgs.length)]);
        } else {
            System.out.println("Couldn't update — product not found.");
        }
    }

    void addQty(int kdx, int q){
        if(map.containsKey(kdx)){
            map.get(kdx).inc(q);
            System.out.println("Stock updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    boolean sell(int kdx, int q){
        if(map.containsKey(kdx)){
            return map.get(kdx).dec(q);
        }
        return false;
    }

    void showAll(){
        System.out.println("\nAvailable Products:");
        System.out.println("ID   Name         Price      Stock");
        for(Product p : map.values()){
            p.show();
        }
    }
}

class CartItem{
    private Product product;
    private int quantity;

    CartItem(Product p, int qty){
        product = p;
        quantity = qty;
    }

    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getTotalPrice(){
        return product.getValue() * quantity;
    }
}

class Bill{
    List<CartItem> items;
    double total;

    Bill(){
        items = new ArrayList<>();
        total = 0;
    }

    public void addItem(CartItem item){
        items.add(item);
    }

    public double calculateTotal(){
        total = 0;
        for(CartItem item:items){
            total += item.getTotalPrice();
        }
        return total;
    }

    public void printBill() {
        System.out.println("size------ Your Bill ------");
        for (CartItem item : items) {
            System.out.println(
                item.getProduct().getLabel() +
                " | Qty: " + item.getQuantity() +
                " | Price: " + item.getTotalPrice()
            );
        }
        System.out.println("-----------------------");
        System.out.println("Total: " + calculateTotal());
    }
}

public class Project_1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Inventory inventory = new Inventory();
        Bill bill = new Bill();

        inventory.add(new Product(1, "Mouse", 500, 10));
        inventory.add(new Product(2, "Keyboard", 800, 5));
        inventory.add(new Product(3, "Monitor", 5000, 3));

        int choice;

        do {
            System.out.println("size====== Inventory System ======");
            System.out.println("1. View Products");
            System.out.println("2. Add Stock");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Bill");
            System.out.println("5. Search Product");
            System.out.println("6. Filter by Price");
            System.out.println("7. Low Stock Items");
            System.out.println("8. Update Product");
            System.out.println("9. Add Product");
            System.out.println("10. Remove Product");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    inventory.showAll();
                    break;

                case 2:
                    System.out.print("Enter product ID and quantity: ");
                    inventory.addQty(sc.nextInt(), sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter product ID: ");
                    int id = sc.nextInt();
                    Product p = inventory.get(id);

                    if (p ==null) {
                        System.out.println("That product doesn’t exist.");
                        break;
                    }

                    System.out.print("Enter quantity: ");
                    int q = sc.nextInt();

                    if (inventory.sell(id, q)) {
                        bill.addItem(new CartItem(p, q));
                        System.out.println("Added to your cart.");
                    } else {
                        System.out.println("Not enough stock.");
                    }
                    break;

                case 4:
                    bill.printBill();
                    break;

                case 5:
                    System.out.print("Enter product ID: ");
                    inventory.search(sc.nextInt());
                    break;

                case 6:
                    System.out.print("Enter least and largest price: ");
                    inventory.filterRange(sc.nextDouble(), sc.nextDouble());
                    break;

                case 7:
                    inventory.low();
                    break;

                case 8:
                    System.out.print("Enter product ID: ");
                    int u = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String s = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double v = sc.nextDouble();
                    inventory.update(u, s, v);
                    break;

                case 9:
                    System.out.print("Enter ID, name, price, stock: ");
                    int kdx = sc.nextInt();
                    sc.nextLine();
                    String name = sc.nextLine();
                    double price = sc.nextDouble();
                    int stock = sc.nextInt();
                    inventory.add(new Product(kdx, name, price, stock));
                    break;

                case 10:
                    System.out.print("Enter product ID to remove: ");
                    inventory.remove(sc.nextInt());
                    break;

                case 11:
                    System.out.println("Alright, shutting things down. Bye!");
                    break;
            }

        } while (choice != 11);

        sc.close();
    }
}