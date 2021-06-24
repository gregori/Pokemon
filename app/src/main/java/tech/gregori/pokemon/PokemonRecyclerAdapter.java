package tech.gregori.pokemon;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.gregori.pokemon.model.Pokemon;
import tech.gregori.pokemon.model.PokemonList;
import tech.gregori.pokemon.model.PokemonListItem;
import tech.gregori.pokemon.model.Type;
import tech.gregori.pokemon.service.PokemonApiClient;
import tech.gregori.pokemon.service.PokemonService;

public class PokemonRecyclerAdapter extends RecyclerView.Adapter<PokemonRecyclerAdapter.PokemonViewHolder> {
    Context context;
    PokemonList pokemonList;
    Pokemon pokemon;

    private static final String TAG = "PokemonRecyclerAdapter";
    public static final String EXTRA_POKEMON = "EXTRA_POKEMON";

    public PokemonRecyclerAdapter(Context context, PokemonList pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    public void setPokemonList(PokemonList pokemonList) {
        this.pokemonList = pokemonList;
        notifyDataSetChanged();
    }

    private String join(String separator, List<String> input) {
        if (input == null || input.size() <= 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            sb.append(input.get(i));
            // if not the last item
            if (i != input.size() - 1) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_adapter, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonRecyclerAdapter.PokemonViewHolder holder, int position) {
        PokemonListItem pokemonListItem = pokemonList.getResults().get(position);
        PokemonService pokemonService = PokemonApiClient.getClient().create(PokemonService.class);
        Call<Pokemon> call = pokemonService.getPokemon(pokemonListItem.getUrl());
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                pokemon = response.body();
                holder.pokemonName.setText(pokemon.getNameUCFirst());
                holder.pokemonId.setText(Integer.toString(pokemon.getId()));
                List<String> types = new ArrayList<>();
                for (Type type : pokemon.getTypes()) {
                    types.add(type.getType().getName());
                }
                holder.pokemonTypes.setText(join(", ", types));

                Picasso.Builder builder = new Picasso.Builder(context);
                builder.downloader(new OkHttp3Downloader(context))
                        .build().load(pokemon.getSprites().getFrontDefault())
                        .placeholder(R.drawable.baseline_image_black_48dp)
                        .into(holder.pokemonImage);
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(TAG, "onFailure: Erro ao obter pokemon: " + toString(), t);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (pokemonList != null) {
            return pokemonList.getResults().size();
        }

        return 0;
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pokemonName;
        TextView pokemonId;
        TextView pokemonTypes;
        ImageView pokemonImage;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.poke_name);
            pokemonId = itemView.findViewById(R.id.poke_id);
            pokemonTypes = itemView.findViewById(R.id.poke_types);
            pokemonImage = itemView.findViewById(R.id.poke_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, PokemonDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(EXTRA_POKEMON, pokemonList.getResults().get(position));
            context.startActivity(intent);
        }
    }
}
