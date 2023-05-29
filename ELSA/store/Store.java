package store;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; // opens a file
import java.io.IOException; // reports an error reading from a file
import javax.imageio.ImageIO; // loads an image from a file
import javax.swing.JFileChooser;
public class Store {
    public Store(String name) {
        this.name = name;
    }
    public Store (BufferedReader br) throws IOException{
        this.name=br.readLine();
        int size=Integer.parseInt(br.readLine());
        while(size-- > 0) this.customers.add(new Customer(br));
        size=Integer.parseInt(br.readLine());
        while(size-- > 0) this.options.add(new Option(br));
        size=Integer.parseInt(br.readLine());
        while(size-- > 0) this.computers.add(new Computer(br));
        size=Integer.parseInt(br.readLine());
        while(size-- > 0) this.orders.add(new Order(br));

    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + "\n");
        save(bw, customers);
        save(bw, options);
        save(bw, computers);
        save(bw, orders);
    }

    private <T extends Saveable> void save(BufferedWriter bw, Set<T> set) throws IOException {
        bw.write(set.size() + "\n");
        for (T item : set) {
            item.save(bw);
        }
    }
    public String name(){
        return name;
    }
    // ///////////////////////////////////////////////////////////
    // Customers
    
    public void add(Customer customer) {
        if(!customers.contains(customer)) customers.add(customer);
    }
    public Object[] customers() {
        return this.customers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Options
    
    public void add(Option option) {
        if(!options.contains(option)) options.add(option);
    }
    public Object[] options() {
        return this.options.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Computers
    
    public void add(Computer computer) {
        if(!computers.contains(computer)) computers.add(computer);
    }
    public Object[] computers() {
        return this.computers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Orders
    
    public void add(Order order) {
        orders.add(order); //if(!orders.contains(order))
    }
    public Object[] orders() {
        return this.orders.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Fields
    
    private String name;
    private TreeSet<Customer> customers= new TreeSet<>();
    private HashSet<Option> options = new HashSet<>();
    private HashSet<Computer> computers = new HashSet<>();
    private HashSet<Order> orders = new HashSet<>();


}
