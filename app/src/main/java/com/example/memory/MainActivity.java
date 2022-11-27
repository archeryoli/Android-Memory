package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.memory.Models.Card;
import com.example.memory.Models.Playground;
import com.example.memory.Models.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] pics;
    private Playground field;
    private Position previousCard;
    private ImageButton[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        previousCard = new Position(-1, -1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout tl = findViewById(R.id.table);
        field = new Playground(3, 4);
        generateGrid(3, 4);

        for(ImageButton[] ibr: buttons){
            TableRow.LayoutParams tableRowParams=
                    new TableRow.LayoutParams
                            (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(tableRowParams);
            for(ImageButton ib: ibr){
                tr.addView(ib);
            }
            tl.addView(tr);
        }
    }
    private void generateGrid(int nrRows, int nrCols){
        buttons = new ImageButton[nrRows][nrCols];
        /*
        for(int i = 0; i < 6; i++){
            boolean worked = false;
            long num = 0;
            do {
                num = getPicsArray()[rand.nextInt(20)];
                if(randCards.contains(num)){
                    continue;
                }
                worked = true;
            } while(!worked);
            randCards.add(num);
            randCards.add(num);
        }
        */
         /*
        for(int row = 0; row < nrRows; row++){
            for(int col = 0; col < nrCols; col++){
                int randNum = rand.nextInt(randCards.size());
                buttons[row][col] = generateButton(new Position(row, col));
                buttons[row][col].setImageResource(Math.toIntExact(randCards.get(randNum)));
                this.field.setCard(new Position(row, col), new Card(false, randNum));
                randCards.remove(randNum);
            }
        }

          */
        for(int row = 0; row < nrRows; row++){
            for(int col = 0; col < nrCols; col++){
                buttons[row][col] = generateButton(new Position(row, col));
                //buttons[row][col].setImageResource(getPicsArray()[field.getCard(new Position(row, col)).getValue()]);
                buttons[row][col].setImageResource(R.drawable.back);
                buttons[row][col].setTag(new Position(row, col));
            }
        }
    }
    private void generateAndAddRows(int nrCols, int nrRows){

    }
    private ImageButton generateButton(Position pos){
        ImageButton ib = new ImageButton(this);
        //ib.setTranslationX(pos.y * 50);
        //ib.setTranslationY(pos.x * 50);
        ib.setOnClickListener(this);
        return ib;
    }
    public void onClick(View view){
        ImageButton b = (ImageButton) view;
        Position c = (Position) b.getTag();
        b.setImageResource(getPicsArray()[field.getCard(c).getValue()]);
        if(previousCard.equals(new Position(-1, -1))){
            previousCard = c;
        } else {
            closeCards(previousCard, c);
            previousCard = new Position(-1, -1);
        }
    }
    public static int[] getPicsArray() {
        int[] c = new int[20];

        c[0] = R.drawable.i000;
        c[1] = R.drawable.i001;
        c[2] = R.drawable.i002;
        c[3] = R.drawable.i003;
        c[4] = R.drawable.i004;
        c[5] = R.drawable.i005;
        c[6] = R.drawable.i006;
        c[7] = R.drawable.i007;
        c[8] = R.drawable.i008;
        c[9] = R.drawable.i009;
        c[10] = R.drawable.i010;
        c[11] = R.drawable.i011;
        c[12] = R.drawable.i012;
        c[13] = R.drawable.i013;
        c[14] = R.drawable.i014;
        c[15] = R.drawable.i015;
        c[16] = R.drawable.i016;
        c[17] = R.drawable.i017;
        c[18] = R.drawable.i018;
        c[19] = R.drawable.i019;
        return c;
    }


    private void closeCards(Position pos1, Position pos2)
    {
        class CloseTask extends TimerTask
        {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    buttons[pos1.y][pos1.x].setImageResource(R.drawable.back);
                    buttons[pos2.y][pos2.x].setImageResource(R.drawable.back);
                });
            }
        }

        Timer timer = new Timer();
        timer.schedule(new CloseTask(),1000);
    }

}