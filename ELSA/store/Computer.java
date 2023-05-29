package store;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File; // opens a file
import java.io.IOException; // reports an error reading from a file
import javax.imageio.ImageIO; // loads an image from a file
import javax.swing.JFileChooser;
public class Computer implements Saveable{
    private String name;
    private String model;
    private ArrayList<Option> options= new ArrayList<>();
    public Computer(String name,String model){
        this.name=name;
        this.model=model;
        this.options=options;
    }
    public Computer (BufferedReader br) throws IOException{
        this.name=br.readLine();
        this.model=br.readLine();
        int size=Integer.parseInt(br.readLine());
        while(size-- > 0) this.options.add(new Option(br));
    }
    @Override
    public void save(BufferedWriter bw) throws IOException{
        bw.write(     name + '\n');        
        bw.write("" + model + '\n');
        bw.write(""+ options.size() + '\n');
        for(Option option : options) option.save(bw);
    }
    public void addOption(Option option){
        options.add(option);
    }
    public long cost(){
        long sum=0;
        for(Option option :options){
            sum+=option.cost;
        }
        return sum;
    }
    @Override
    public String toString(){
        String result=name+" ("+model+")\n";
        for(Option option:options){
            result+="\t"+option.toString();
        }
        return result;

    }
    @Override
    public boolean equals(Object o){

        if(o == this) return true;             // An object is equal to itself

        if(!(o instanceof Computer)) return false;  // A different type is not equal
        Computer f = (Computer)o;
        if(this.toString().equals(f.toString())){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + ((options == null) ? 0 : options.hashCode());
        return result;
    }

}