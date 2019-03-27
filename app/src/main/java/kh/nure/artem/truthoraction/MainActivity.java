package kh.nure.artem.truthoraction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_newGame;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_newGame = findViewById(R.id.btn_newGame);
    }

    public void startGame(View view){
        intent = new Intent(this, PlayersActivity.class);
        startActivity(intent);
    }
}
