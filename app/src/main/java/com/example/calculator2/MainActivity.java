package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> Nums;
    ArrayList<Integer> SimpMath;
    ArrayList<Integer> DuoMath;
    Set<Integer> SetNums;
    Set<Integer> SetSimpMath;
    MyListener myListener;
    ArrayList<Button> NumBTN;
    Set<Integer> SetDuoMath;
    ArrayList<Button> SimpMathBTN;
    ArrayList<Button> DuoMathBTN;
    ArrayList<String> mathoperators;

    TextView TViwLog;
    TextView TViwRes;
    Button Res;
    Button DelAll;
    Button Point;
    Button DelEl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myListener = new MyListener();
        makeSets();
        fillButtons();
        addTextView();
        makeOtherBTN();
    }
    public void makeSets() {
        Nums = new ArrayList<>();
        makeNums();
        SetNums =new HashSet<>(Nums);
        SimpMath = new ArrayList<>();
        makeSimpMath();
        SetSimpMath =new HashSet<>(SimpMath);
        DuoMath = new ArrayList<>();
        makeDuoMath();
        SetDuoMath = new HashSet<>(DuoMath);
        mathoperators = new ArrayList<>();
        makeMathOper();


    }
    public void makeMathOper(){
        mathoperators.add("GIP");
        mathoperators.add("+");
        mathoperators.add("-");
        mathoperators.add("/");
        mathoperators.add("*");
        mathoperators.add("mid");

    }
    public void makeNums(){
        Nums.add(R.id.zero);
        Nums.add(R.id.one);
        Nums.add(R.id.two);
        Nums.add(R.id.three);
        Nums.add(R.id.four);
        Nums.add(R.id.five);
        Nums.add(R.id.six);
        Nums.add(R.id.seven);
        Nums.add(R.id.eight);
        Nums.add(R.id.nine);
        Nums.add(R.id.point);

    }
    public void makeOtherBTN(){
        Res = makeBTN(R.id.result);
        Point = makeBTN(R.id.point);
        DelEl = makeBTN(R.id.delEl);
        DelAll = makeBTN(R.id.allclear);
    }
    public Button makeBTN(int ide){
        Button button = (Button)findViewById(ide);
        button.setOnClickListener(myListener);
        return button;
    }
    public void makeSimpMath(){
        SimpMath.add(R.id.sqrt);
        SimpMath.add(R.id.oneDelX);
        SimpMath.add(R.id.percant);
    }
    public void makeDuoMath(){
        DuoMath.add(R.id.Gipo);
        DuoMath.add(R.id.plus);
        DuoMath.add(R.id.minus);
        DuoMath.add(R.id.del);
        DuoMath.add(R.id.mult);
        DuoMath.add(R.id.mid);
    }


    public void fillButtons(){
        NumBTN = makeButtons(myListener,Nums);
        SimpMathBTN = makeButtons(myListener,SimpMath);
        DuoMathBTN= makeButtons(myListener,DuoMath);
    }
    public void addTextView(){
        TViwLog = (TextView)findViewById(R.id.Log);
        TViwLog.setText("");
        TViwRes = (TextView)findViewById(R.id.Result);
        TViwRes.setText("");
    }

    public ArrayList<Button> makeButtons(View.OnClickListener listener,ArrayList<Integer> spisok){
        ArrayList<Button> buttons= new ArrayList<>();
        for (Integer ide: spisok){
            Button button = (Button)findViewById(ide);
            button.setOnClickListener(listener);
            buttons.add(button);
        }
        return buttons;
    }


    class MyListener implements View.OnClickListener{
        Integer precommand = null ;
        int command;
        String TXTLOG="";
        String TXTRES="";
        double First;
        Integer mathoper;
        @Override
        public void onClick(View v) {
            command = v.getId();
            if(SetNums.contains(command)){
                if(precommand==null){
                    editText(command);
                    precommand=command;
                }else if(SetNums.contains(precommand)){
                    editText(command);
                    precommand=command;
                }else if(SetDuoMath.contains(precommand)){
                    First=Double.parseDouble(TXTRES);
                    TXTRES="";
                    editText(command);
                    precommand=command;

                }else if(SetSimpMath.contains(precommand)){

                    editText(command);
                    precommand=command;
                }else if(precommand==R.id.result){
                    setNullText();
                    editText(command);
                    precommand=command;
                }
            }else if(SetSimpMath.contains(command)){
                if (Nums.contains(precommand)){

                    First = Double.parseDouble(TXTRES);
                    TXTRES=String.valueOf(countSimp(command,First));
                    TXTLOG=TXTRES;
                    TViwLog.setText(TXTLOG);
                    TViwRes.setText(TXTRES);
                    precommand=command;
                }else if(SetSimpMath.contains(precommand)){

                    First=Double.parseDouble(TXTRES);
                    simpCount(command,First);
                    precommand=command;
                }else if(SetDuoMath.contains(precommand)){

                    First=Double.parseDouble(TXTRES);
                    simpCount(command,First);
                    precommand=command;
                }else if(precommand==null){

                }else if(precommand==R.id.result){
                    First=Double.parseDouble(TXTRES);
                    simpCount(command,First);
                    precommand=command;
                }else if(SetDuoMath.contains(command)){

                }

            }else if(SetDuoMath.contains(command)){
                if (SetNums.contains(precommand) && mathoper==null){
                    mathoper=command;
                    precommand=command;
                    int i=DuoMath.indexOf(command);
                    TXTLOG+=mathoperators.get(i);
                    TViwLog.setText(TXTLOG);
                }else if(SetSimpMath.contains(precommand)){
                    mathoper=command;
                    precommand=command;
                    int i=DuoMath.indexOf(command);
                    TXTLOG+=mathoperators.get(i);
                    TViwLog.setText(TXTLOG);
                }else if(SetDuoMath.contains(precommand)){
                    precommand=command;
                    mathoper=command;
                    char[] logtemp = TXTLOG.toCharArray();
                    TXTLOG=delEl(logtemp);
                    int i=DuoMath.indexOf(command);
                    TXTLOG+=mathoperators.get(i);
                    TViwLog.setText(TXTLOG);

                }else if(precommand==null){

                }else if(precommand==R.id.result){
                    mathoper=command;
                    precommand=command;
                    int i=DuoMath.indexOf(command);
                    TXTLOG+=mathoperators.get(i);
                    TViwLog.setText(TXTLOG);
                }else if(SetNums.contains(precommand)|| mathoper!=0){
                    int i=DuoMath.indexOf(command);
                    TXTLOG+=mathoperators.get(i);
                    TXTRES=String.valueOf(countDuo(mathoper,First,Double.parseDouble(TXTRES)));
                    First=Double.parseDouble(TXTRES);
                    precommand = command;
                    mathoper=command;
                    TViwRes.setText(TXTRES);
                    TViwLog.setText(TXTLOG);
                }
            }
            else if (command==R.id.result){
                if (precommand==null){

                } else if(SetNums.contains(precommand) && mathoper!=null){
                    double two = Double.parseDouble(TXTRES);
                    TXTRES=String.valueOf(countDuo(mathoper,First,two));
                    TXTLOG=TXTRES;
                    TViwRes.setText(TXTRES);
                    TViwLog.setText(TXTRES);
                    precommand=command;
                    mathoper=null;
                }else if (SetDuoMath.contains(precommand) || SetSimpMath.contains(precommand) || precommand==command){

                }else if (SetNums.contains(precommand) && mathoper==null){

                }
            }
            else if (command==R.id.allclear){
                clearAll();
            }
            else if (command == R.id.delEl){
                if(precommand==null || SetDuoMath.contains(precommand)){}
                else if (SetNums.contains(precommand) && !TXTRES.equals("")){
                    char[] temp = TXTRES.toCharArray();
                    char[] logtemp = TXTLOG.toCharArray();
                    TXTRES =delEl(temp);
                    TXTLOG = delEl(logtemp);
                    TViwLog.setText(TXTLOG);
                    TViwRes.setText(TXTRES);
                }
            }
        }
        public String delEl(char[] temp){
            String l ="";
            for (int i =0;i<temp.length-1;i++){
                l+=temp[i];
            }
            return l;
        }
        public void setNullText(){
            TXTLOG="";
            TXTRES="";
        }
        public void clearAll(){
            setNullText();
            TViwRes.setText(TXTRES);
            TViwLog.setText(TXTLOG);
            mathoper=null;
            precommand=null;

        }
        public void editText(int command){
            if (command==R.id.point && !TXTRES.contains(".") && !TXTRES.equals("")){
                TXTLOG+=".";
                TXTRES+=".";
                TViwLog.setText(TXTLOG);
                TViwRes.setText(TXTRES);
            }else if(TXTRES.contains(".") && command==R.id.point){

            }else if(Nums.indexOf(command)<10){
                TXTLOG+=Nums.indexOf(command);
                TXTRES+=Nums.indexOf(command);
                TViwLog.setText(TXTLOG);
                TViwRes.setText(TXTRES);
            }
        }
        public void simpCount(int command,double First){
            TXTRES=String.valueOf(countSimp(command,First));
            TXTLOG=TXTRES;
            TViwLog.setText(TXTLOG);
            TViwRes.setText(TXTRES);
        }
        public double countSimp(int mathoper, double A){
            double resultat=0;
            switch (mathoper){
                case R.id.sqrt:
                    resultat = Math.sqrt(A);
                    break;
                case R.id.oneDelX:
                    resultat =  1/A;
                    break;
                case R.id.percant:
                    resultat=A/100;
            }
            return resultat;
        }
        public double countDuo(int mathoper,double A,double B){
            double resultat =A;
            switch (mathoper){
                case R.id.plus:
                    resultat+=B;
                    break;
                case R.id.minus:
                    resultat-=B;
                    break;
                case R.id.mult:
                    resultat*=B;
                    break;
                case R.id.del:
                    resultat/=B;
                    break;
                case R.id.pow:
                    resultat=Math.pow(A,B);
                    break;
                case R.id.Gipo:
                    resultat=Math.hypot(A,B);
                    break;
                case R.id.mid:
                    resultat=(A+B)/2;
                    break;
            }

            return resultat;
        }
    }
}