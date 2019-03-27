package kh.nure.artem.truthoraction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlayersActivity extends AppCompatActivity {

    EditText editNameOfPlayers;
    ArrayList<String> players;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        editNameOfPlayers = findViewById(R.id.enterNameOfPlayers);
        ListView listView = (ListView)findViewById(R.id.listviePlayers);
        players = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, players);
        listView.setAdapter(adapter);

    }

    public void onClickButtonAdd(View view){
        String player = editNameOfPlayers.getText().toString();
        if (player.length() != 0){
            firstUpperCase(player);
            players.add(player);
            adapter.notifyDataSetChanged();
            editNameOfPlayers.setText("");
            Log.d("ADD", player);
        } else Toast.makeText(this, "Enter player`s name correctly", Toast.LENGTH_SHORT).show();

        }


    public String firstUpperCase(String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public void onPressClearAll(View view){
        players.clear();
        adapter.notifyDataSetChanged();
    }

    public void onPressStartGame (View view){
        if (players.isEmpty()){
            Toast.makeText(this, "Enter names of players", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("ArrayNamesOfPlayers", players);
            startActivity(intent);
        }
    }


}
