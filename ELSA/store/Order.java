package store;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Order implements Saveable{
    private static long nextOrderNumber=0;
    private long orderNumber=1;
    private Customer customer;
    private ArrayList<Computer> computers= new ArrayList<>();
    public Order(Customer customer){
        this.customer=customer;
        this.orderNumber=orderNumber+nextOrderNumber;
        this.computers=computers;
        nextOrderNumber++;
    }
    public Order (BufferedReader br) throws IOException{
        this.customer=new Customer(br);
        this.orderNumber=Long.parseLong(br.readLine());
        int size=Integer.parseInt(br.readLine());
        while(size-- > 0) this.computers.add(new Computer(br));
    }
    @Override
    public void save(BufferedWriter bw) throws IOException{
        customer.save(bw);        
        bw.write("" + orderNumber + '\n');
        bw.write(""+ computers.size() + '\n');
        for(Computer computer : computers) computer.save(bw);
    }
    public void addComputer(Computer computer){
        computers.add(computer);
    }
   
    @Override
    public String toString(){
        String result="Order #"+orderNumber+" for "+customer.toString();
        for(Computer computer:computers){
            result+="\t"+computer.toString()+"\n";
        }
        Pattern pattern = Pattern.compile("\\$([\\d.]+)");
        Matcher matcher = pattern.matcher(result);
        double total = 0;
        while (matcher.find()) {
            String match = matcher.group(1);
            double value = Double.parseDouble(match);
            total += value;
        }
        result+="Total for Order # "+orderNumber+": $"+total+"\n\n";
        return result;

    }
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;             // An object is equal to itself

        if(!(o instanceof Order)) return false;  // A different type is not equal
        Order f = (Order)o;
        if(this.customer.equals(f.customer)){
            for(Computer computer:f.computers){
                if(!this.computers.contains(computer)){
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + ((customer == null) ? 0 : customer.hashCode());
        result = 31 * result + ((computers == null) ? 0 : computers.hashCode());
        return result;
    }

}