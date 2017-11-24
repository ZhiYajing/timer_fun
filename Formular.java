import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
public class Formular
{
    private ArrayList<String> formularList = new ArrayList<>();
    private String fileName;
    public Formular()
    {
        this.fileName = "formular.txt";
    }
    public void readFile(){
        File formFile = new File(fileName);
        Scanner inFormFile = null;
        try{
            inFormFile = new Scanner(formFile);
            while(inFormFile.hasNext())
            {
                String formLine = inFormFile.nextLine();
                formularList.add(formLine);
            }
        }
        catch (Exception e)
        {
            System.err.println("Error: " +  e.getMessage());
        }
        finally{
            if(inFormFile != null)
            {
                inFormFile.close();
            }
        }
    }

    private int getNum()
    {
        Random ran = new Random();
        int num = ran.nextInt(formularList.size());
        return num;
    }
    
    public String getText()
    {
        //System.out.println(formularList.get(getNum()));
        return formularList.get(getNum());
    }

}
