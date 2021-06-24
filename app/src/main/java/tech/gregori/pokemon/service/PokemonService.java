package tech.gregori.pokemon.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import tech.gregori.pokemon.model.Pokemon;
import tech.gregori.pokemon.model.PokemonList;

public interface PokemonService {
    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") int id);

    @GET("pokemon")
    Call<PokemonList> getPokemons(@Query("limit") int limit);

    @GET
    Call<Pokemon> getPokemon(@Url String url);
}
