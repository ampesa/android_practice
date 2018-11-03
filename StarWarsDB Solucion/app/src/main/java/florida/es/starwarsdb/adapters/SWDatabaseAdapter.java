package florida.es.starwarsdb.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import florida.es.starwarsdb.R;
import florida.es.starwarsdb.SWDatabaseEntry;
import florida.es.starwarsdb.utils.ImageGetter;

public class SWDatabaseAdapter extends RecyclerView.Adapter<SWDatabaseAdapter.SWEntryViewHolder> {

    protected List<SWDatabaseEntry> mEntries = null;

    public SWDatabaseAdapter(List<SWDatabaseEntry> entries){
        mEntries = new ArrayList<SWDatabaseEntry>(entries);
        Log.w(getClass().getName(),mEntries.size() + " _________________________________________  ES EL TAMAÑO ");
    }

    @NonNull
    @Override
    public SWEntryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.linear_list_entry,viewGroup,false);
        return new SWEntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SWEntryViewHolder swEntryViewHolder, int i) {
        Context context = swEntryViewHolder.itemView.getContext();
        SWDatabaseEntry entry = mEntries.get(i);
        swEntryViewHolder.mName.setText( entry.getName() );
        swEntryViewHolder.mGender.setText(entry.getGender());
        swEntryViewHolder.mMass.setText(entry.getHeight());
        swEntryViewHolder.mIcon.setImageDrawable( ContextCompat.getDrawable(context,ImageGetter.getSWIcon(entry.getName())) );

    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    public class SWEntryViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {

        TextView mName = null;
        ImageView mIcon = null;
        TextView mGender = null;
        TextView mMass = null;
        TextView mHeight = null;


        public SWEntryViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_char_name);
            mIcon= itemView.findViewById(R.id.iv_sw_icon);
            mGender = itemView.findViewById(R.id.tv_char_gender);
            mMass = itemView.findViewById(R.id.tv_char_mass);
            mHeight = itemView.findViewById(R.id.tv_char_height);
            itemView.findViewById(R.id.fl_list_entry).setOnClickListener(this);
            itemView.findViewById(R.id.fl_profile).setOnClickListener(this);
            mIcon.setOnClickListener(this);
            mName.setOnClickListener(this);
            mGender.setOnClickListener(this);
            mMass.setOnClickListener(this);
            mHeight.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            if(mGender.getVisibility() == View.VISIBLE)
                mGender.setVisibility( View.INVISIBLE );
            else
                mGender.setVisibility(View.VISIBLE);

            if(mMass.getVisibility() == View.VISIBLE)
                mMass.setVisibility( View.INVISIBLE );
            else
                mMass.setVisibility(View.VISIBLE);

            if(mHeight.getVisibility() == View.VISIBLE)
                mHeight.setVisibility( View.INVISIBLE );
            else
                mHeight.setVisibility(View.VISIBLE);
        }
    }

}
