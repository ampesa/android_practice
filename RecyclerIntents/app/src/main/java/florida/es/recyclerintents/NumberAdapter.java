package florida.es.recyclerintents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder>  {

    List<Integer> randomListNumbers = null;
    Random rand = null;
    
    public NumberAdapter(int n){
        randomListNumbers = new ArrayList<Integer>();
        rand = new Random();
        for(int i=0;i<n;i++){
            randomListNumbers.add(rand.nextInt());
        }
    }
    
    @NonNull
    @Override
    public NumberAdapter.NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_numbers,viewGroup,false);
        return new NumberViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberAdapter.NumberViewHolder integerViewHolder, int i) {
        integerViewHolder.numberTextView.setText( Integer.toString( randomListNumbers.get(i) ) );
    }


    @Override
    public int getItemCount() {
        return randomListNumbers.size();
    }


    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button implicitButton = null;
        Button explicitButton = null;
        TextView numberTextView = null;
        Context mContext = null;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext = itemView.getContext();

            numberTextView = itemView.findViewById(R.id.tv_number);
            implicitButton = itemView.findViewById(R.id.bt_implicit);
            explicitButton = itemView.findViewById(R.id.bt_explicit);


            implicitButton.setOnClickListener(this);
            explicitButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id==R.id.bt_implicit){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "HOLA");
                //ActivityCompat.startActivity();
                //ActivityCompat.startActivityForResult();
                if( intent.resolveActivity(mContext.getPackageManager())!=null ){
                    mContext.startActivity(intent);
                }

            } else if(id==R.id.bt_explicit){
                Intent intent =  new Intent(mContext, Activity2.class);
                MainActivity contextAux = (MainActivity) mContext;
                contextAux.startActivityForResult(intent,200);
            }
        }
    }
}
