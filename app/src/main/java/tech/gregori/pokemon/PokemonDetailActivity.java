package tech.gregori.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import tech.gregori.pokemon.model.PokemonListItem;

public class PokemonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        Intent intent = getIntent();

        PokemonListItem pokemonListItem =
                (PokemonListItem) intent.getSerializableExtra(PokemonRecyclerAdapter.EXTRA_POKEMON);


        TextView pokemonDetailName = findViewById(R.id.detail_poke_name);
        pokemonDetailName.setText(pokemonListItem.getName());
    }
}