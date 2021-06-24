package tech.gregori.pokemon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.gregori.pokemon.model.Pokemon;
import tech.gregori.pokemon.model.PokemonList;
import tech.gregori.pokemon.service.PokemonApiClient;
import tech.gregori.pokemon.service.PokemonService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private PokemonList pokemonList;
    private PokemonRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.poke_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new PokemonRecyclerAdapter(getApplicationContext(), pokemonList);
        recyclerView.setAdapter(recyclerAdapter);

        PokemonService pokemonService = PokemonApiClient.getClient().create(PokemonService.class);
//        Call<Pokemon> call = pokemonService.getPokemon(1); // obter bulbasaur
        Call<PokemonList> call = pokemonService.getPokemons(150);
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                pokemonList = response.body();
                Log.d(TAG, "onResponse: " + pokemonList);
                recyclerAdapter.setPokemonList(pokemonList);
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.e(TAG, "onFailure: Response = " + t.toString(), t);
            }
        });
    }

}