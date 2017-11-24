import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.*;
import java.util.*;
class Window extends JFrame
{
    private ArrayList <String> rowResult =new ArrayList<>();
    private ArrayList <String> timeResult =new ArrayList<>();
    private ArrayList <Integer> minute = new ArrayList<>();
    private ArrayList <Integer> second = new ArrayList<>();
    private ArrayList <Integer> millinsecond = new ArrayList<>();
    private int ON = 0,i=0,j=0,k=0, count =0,num=1, R=0;
    private JButton button0, button1,button2,button3,button4,button5,button6;
    private JTextField file0,file1,file2,file3;
    private JTextArea file;
    private JTextArea stat;
    private FlowLayout flow;
    private String a,b,c;
    //private Font font = new Font("TimesRoman",Font.BOLD,20);
    private Font font1 = new Font("TimesRoman",Font.BOLD,30);
    private Formular temp = new Formular();
    private static int size_width =950;
    private static int size_height=1000;
    private static int h_gap =20;
    private static int v_gap =40; 
    private int flag = 0;
    private int dnf = 0;
    private String maxTime = "0";
    private String minTime = "0";
    private String aveTime = "0";
    private String result = "";
    public Window(String name)
    {
        temp.readFile();
        file1 = new JTextField(2);
        file2 = new JTextField(2);
        file3 = new JTextField(2);
        file0 = new JTextField(35);
        file0.setHorizontalAlignment(JTextField.CENTER);
        file0.setEditable(false);
        file1.setEditable(false);
        file2.setEditable(false);
        file3.setEditable(false);
        file = new JTextArea(13,13);
        file.setEditable(false);
        file.setFont(font1);
        file.append("NO.\tTime\n");
        stat = new JTextArea(13,13);
        stat.setEditable(false);
        stat.setFont(font1);
        stat.append("Average:"+aveTime+"\n\n"+"Minimum:"+minTime+"\n\n"+"Maxmun:"+maxTime);

        button0 = new JButton("Test Start & Stop");
        button1 = new JButton ("Start");
        button2 = new JButton ("Stop");
        button3 = new JButton ("Reset");
        button4 = new JButton ("Record");
        button5 = new JButton ("Clear Record");
        button6 = new JButton ("DNF");
        button0.setFont(font1);
        button1.setFont(font1);
        button2.setFont(font1);
        button3.setFont(font1);
        button4.setFont(font1);
        button5.setFont(font1);
        button6.setFont(font1);
        flow = new FlowLayout();
        flow.setAlignment(FlowLayout.CENTER);
        flow.setHgap(h_gap);
        flow.setVgap(v_gap);
        setTitle(name);
        setSize(size_width,size_height);
        setLayout(flow);

        add(file1);
        add(new JLabel(":"));
        add(file2);
        add(new JLabel("."));
        add(file3);
        add(file0);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(file);
        add(stat);
        add(button0);
        add(button6);
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        file0.setFont(font1);
        file1.setFont(font1);
        file2.setFont(font1);
        file3.setFont(font1);
        file0.setText(temp.getText()); 
        file1.setText("0");
        file2.setText("0");
        file3.setText("0");
        validate();
        button0.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    if(flag == 0){
                        ON = 1;
                        flag =1;
                    }else if(flag == 1){
                        ON = 0;
                        count = 1;
                        a = String.valueOf(i);
                        b = String.valueOf(j);
                        c = String.valueOf(k);
                        R=1;
                        flag =0;
                    }
                }
            }); 
        button1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    ON = 1;
                }
            }); //start
        button2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    ON = 0;
                }
            }); //stop
        button3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    R = 1;
                }
            }); //RESET
        button4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    count = 1;
                    a = String.valueOf(i);
                    b = String.valueOf(j);
                    c = String.valueOf(k);
                }
            }); //record
        button5.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    file.setText("");
                    num = 1;
                }
            }); //clear
        button6.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    dnf =1;
                }
            });
        while(true){
            validate();
            if(ON == 1)
            {
                file1.setText(String.valueOf(i));
                file2.setText(String.valueOf(j));
                file3.setText(String.valueOf(k));
                if(k == 99)
                {
                    k=-1;
                    j++;
                }
                if(j==60)
                {
                    k=0;
                    j=0;
                    i++;
                }
                if(i ==24)
                {
                    i=0;
                    j=0;
                    k=0;
                }   
                try{
                    Thread.sleep(10);
                }
                catch(Exception e){
                }
                k++;
            }
            if(count == 1)
            {
                a = String.valueOf(i);
                b = String.valueOf(j);
                c = String.valueOf(k);
                result =a+": "+b+". "+c;
                timeResult.add(result);
                rowResult.add(result);
                file.append(String.valueOf(num)+"\t");
                file.append(result+"\n");
                minute.add(Integer.parseInt(a));
                second.add(Integer.parseInt(b));
                millinsecond.add(Integer.parseInt(c));
                maxTime = getMax();
                minTime = getMin();
                aveTime = getAver();
                stat.setText("");
                stat.append("Average: "+aveTime+"\n\n"+"Minimum: "+minTime+"\n\n"+"Maxmun: "+maxTime);
                num++;
                count = 0;
                FileWriter fwriter = null;
                PrintWriter outputFile = null;
                String outFileName = "result.txt";
                try{
                    fwriter = new FileWriter(outFileName,true);
                    outputFile = new PrintWriter(fwriter);
                    outputFile.println(String.valueOf(num)+"\t"+result+"\t"+temp.getText());
                }
                catch(Exception e){
                    System.err.println(e.getMessage());
                }
                finally{
                    if(outputFile != null)
                    {
                        outputFile.close();
                    }
                }
            }

            if(R == 1)
            {
                i=k=j=0;
                file1.setText(String.valueOf(i));
                file2.setText(String.valueOf(j));
                file3.setText(String.valueOf(k));
                R = 0;            
                temp.readFile();
                file0.setText(temp.getText()); 
            }
            if(dnf == 1)
            {
                for(int i = 0;i<timeResult.size();i++){
                    if(timeResult.get(i).equals(rowResult.get(rowResult.size()-1))){
                        timeResult.set(i,a+": "+String.valueOf(Integer.parseInt(b)+2)+". "+c);
                        break;
                    }
                }
                rowResult.set(rowResult.size()-1,a+": "+String.valueOf(Integer.parseInt(b)+2)+". "+c);
                file.setText("");
                file.append("NO."+"\t"+"Time\n");
                for(int i = 0;i<rowResult.size();i++){
                    file.append((i+1)+"\t"+rowResult.get(i)+"\n");
                }
                int x = minute.get(minute.size()-1);
                int y = second.get(second.size()-1)+2;
                if(y>=60)
                {
                    y=y-60;
                    x++;
                }
                minute.set(minute.size()-1,x);
                second.set(second.size()-1,y);
                maxTime = getMax();
                minTime = getMin();
                aveTime = getAver();
                stat.setText("");
                stat.append("Average: "+aveTime+"\n\n"+"Minimum: "+minTime+"\n\n"+"Maxmun: "+maxTime);
                dnf = 0;
            }
        }
    }

    private String getMin()
    {
        Compare order = new Compare();
        Collections.sort(timeResult, order);
        return timeResult.get(0);
    }

    private String getMax()
    {
        //ArrayList <String> timeResult =rowResult;
        Compare order = new Compare();
        Collections.sort(timeResult, order);
        if(timeResult.size()>0)
            return timeResult.get(timeResult.size()-1);
        else 
            return "0";
    }

    private String getAver()
    {
        int minSum = 0;
        int secSum = 0;
        int misecSum = 0;
        for(int i=0;i<minute.size();i++){
            minSum += minute.get(i);
            secSum += second.get(i);
            misecSum += millinsecond.get(i);
        }
        if(minute.size()>0)
            return minSum/minute.size()+":"+secSum/second.size()+"."+misecSum/millinsecond.size();
        else
            return "0";
    }
}

    