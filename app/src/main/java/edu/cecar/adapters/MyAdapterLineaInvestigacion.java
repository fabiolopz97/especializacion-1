package edu.cecar.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.cecar.controladores.LineaInvestigacion;
import edu.cecar.controladores.R;

public class MyAdapterLineaInvestigacion extends RecyclerView.Adapter<MyAdapterLineaInvestigacion.ViewHolder>{
    private List<LineaInvestigacion> lineaInvestigacions;
    private int layout;
    private OnItemClickListener listener;

    public MyAdapterLineaInvestigacion(List<LineaInvestigacion> lineaInvestigacions, int layout, OnItemClickListener listener) {
        this.lineaInvestigacions = lineaInvestigacions;
        this.layout = layout;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(lineaInvestigacions.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return lineaInvestigacions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public ViewHolder(View itemView){
            super(itemView);
            // PENDIENTE MODIFICAR ESTA LINEA DE ABAJO
             this.textViewName = (TextView) itemView.findViewById(R.id.tvLineaInvestigacion);
            //itemView.setOnCreateContextMenuListener((View.OnCreateContextMenuListener) this);
        }

        public void bind(final LineaInvestigacion lineaInvestigacion, final OnItemClickListener listener) {
            this.textViewName.setText(lineaInvestigacion.getNombre());
            //Picasso.with(activity.getContext()).load(catalogo.getImgBackground()).fit().into(this.imageViewGrid);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(lineaInvestigacion, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(LineaInvestigacion lineaInvestigacion, int position);
    }
}

