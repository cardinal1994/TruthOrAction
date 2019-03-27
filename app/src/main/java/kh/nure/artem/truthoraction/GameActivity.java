package kh.nure.artem.truthoraction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {


    TextView nameOfPlayer;
    TextView truthOrAction;
    Button btnActive;
    Button btnTruth;
    TextView tvTellTheTruth;
    TextView tvDoTheAction;
    TextView text;
    Button btnNext;
    List<String> players;
    List<String> truthes = new ArrayList<>();
    List<String> actions = new ArrayList<>();
    private final static String FILENAME_ACTIONS = "actions.txt";
    private final static String FILENAME_TRUTHES = "truthes.txt";
    int indexPlayer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        nameOfPlayer = findViewById(R.id.TVNameOfPlayer);
        truthOrAction = findViewById(R.id.TVTruthOrActive);
        btnActive = findViewById(R.id.btnAction);
        btnTruth = findViewById(R.id.btnTruth);
        tvTellTheTruth = findViewById(R.id.TVTellTheTruth);
        tvDoTheAction = findViewById(R.id.TVDoTheAction);
        text = findViewById(R.id.text);
        btnNext = findViewById(R.id.btnGameNext);
        players = getIntent().getStringArrayListExtra("ArrayNamesOfPlayers");

        truthes = getTextFromFile(FILENAME_TRUTHES);
        actions = getTextFromFile(FILENAME_ACTIONS);

        nameOfPlayer.setText(players.get(indexPlayer).toUpperCase());
        indexPlayer++;


    }

    @Override
    public void onBackPressed() {

    }

    public void onClickButtonTruth(View view) {
        if (truthes.isEmpty()) {
            Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();
        } else {
            btnActive.setVisibility(View.GONE);
            btnTruth.setVisibility(View.GONE);
            truthOrAction.setVisibility(View.GONE);
            tvTellTheTruth.setVisibility(View.VISIBLE);
            tvDoTheAction.setVisibility(View.GONE);
            text.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            int indexTruth = rnd(truthes.size() - 1);
            text.setText(truthes.get(indexTruth));
            truthes.remove(indexTruth);
        }

    }

    public void onClickButtonActive(View view) {
        if (actions.isEmpty()) {
            Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();
        } else {
            btnActive.setVisibility(View.GONE);
            btnTruth.setVisibility(View.GONE);
            truthOrAction.setVisibility(View.GONE);
            tvTellTheTruth.setVisibility(View.GONE);
            tvDoTheAction.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            int indexActive = rnd(actions.size() - 1);
            text.setText(actions.get(indexActive));
            actions.remove(indexActive);
        }

    }

    public void onClickButtonNext(View view) {
        btnActive.setVisibility(View.VISIBLE);
        btnTruth.setVisibility(View.VISIBLE);
        truthOrAction.setVisibility(View.VISIBLE);
        tvTellTheTruth.setVisibility(View.GONE);
        tvDoTheAction.setVisibility(View.GONE);
        text.setVisibility(View.GONE);
        btnNext.setVisibility(View.GONE);

        if (indexPlayer == players.size()) {
            indexPlayer = 0;
        }
        nameOfPlayer.setText(players.get(indexPlayer).toUpperCase());
        indexPlayer++;

    }

    public static int rnd(int max) {
        return (int) (Math.random() * ++max);
    }

    public List<String> getTextFromFile(String filename) {
        List<String> arrayList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(filename)))) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                arrayList.add(line);
            }
        } catch (IOException exc) {
            System.out.println("IO error!" + exc);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
        return arrayList;
    }


}
