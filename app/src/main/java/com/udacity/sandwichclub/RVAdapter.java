package com.udacity.sandwichclub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter <RVAdapter.ViewHolder> {
    private final Context context;
    private final String[] values;

    RVAdapter(Context parentContext, String[] givenSandwiches) {
        context = parentContext;
        values = givenSandwiches;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.idView.setText(values[position]);
        holder.itemView.setTag(new Integer(position));
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView idView;

        ViewHolder(View view) {
            super(view);
            idView = view.findViewById(R.id.SandwichName);

        }
    }

    final private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int item = (int) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, item);
            context.startActivity(intent);
        }
    };
}