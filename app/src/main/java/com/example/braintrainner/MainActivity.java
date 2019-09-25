package com.example.braintrainner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
        Button startbutton,playagainbutton;
         TextView resulttextview,sumview,PointsTextView,timetextView;
          Button b1,b2,b3,b4;

            int score;
            int NumberOfQuestion;
            int locationofcorrectAnswer;

            public void PlayAgain(View view){
                genrateQuestions();
                score = 0;
                NumberOfQuestion =0;
                timetextView.setText("30sec");
                PointsTextView.setText("0/0");
                resulttextview.setText("");
                playagainbutton.setVisibility(View.INVISIBLE);
                new CountDownTimer(5000,1000) {
                    @Override
                    public void onTick(long l) {

                        timetextView.setText(String.valueOf(l/1000)+"s");
                    }
                    @Override
                    public void onFinish() {
                        timetextView.setText("0s");
                        resulttextview.setText("your score is "+score+NumberOfQuestion);
                        playagainbutton.setVisibility(View.VISIBLE);
                    }
                }.start();
            }

        public void genrateQuestions(){
            Random random = new Random();

               int a = random.nextInt(21);

            int b =random.nextInt(21);
               sumview.setText(Integer.toString(a)+"+"+Integer.toString(b));
               locationofcorrectAnswer = random.nextInt(4);
                answers.clear();
               for(int i =0; i<4; i++){
                   if(i==locationofcorrectAnswer){
                       answers.add(a+b);
                   }
                   else {int incorrectanswer;

                       incorrectanswer =random.nextInt(41);
                       while(incorrectanswer == a+b ){
                           incorrectanswer =random.nextInt(41);
                       }//while body
                       answers.add(incorrectanswer);
                   }//else Body
               }//for body
               b1.setText(Integer.toString(answers.get(0)));
               b2.setText(Integer.toString(answers.get(1)));
               b3.setText(Integer.toString(answers.get(2)));
               b4.setText(Integer.toString(answers.get(3)));

           }
        public void start(View view){
            startbutton.setVisibility(View.INVISIBLE);
        }
            public void chooseAnswer(View view){
                //Log.i("Tag", (String) view.getTag());
                    if(view.getTag().toString().equals( Integer.toString(locationofcorrectAnswer))){
                        Log.i("Correct","Correct");
                           score++;
                            resulttextview.setText("Correct");
                            NumberOfQuestion++;
                    }
                    else{
                        resulttextview.setText("Wrong!");
                            NumberOfQuestion++;
                    }
                PointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(NumberOfQuestion));
                            genrateQuestions();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         startbutton =findViewById(R.id.startbutton);
         sumview =findViewById(R.id.sumTextView);

            b1 =findViewById(R.id.button1);
             b2 =findViewById(R.id.button2);
              b3 =findViewById(R.id.button3);
               b4 =findViewById(R.id.button4);
         resulttextview = findViewById(R.id.Result);
            PointsTextView =findViewById(R.id.pointesTextView);
                timetextView = findViewById(R.id.timerTextView);
                    playagainbutton = findViewById(R.id.playagain);

            PlayAgain(findViewById(R.id.playagain));
        }
}
