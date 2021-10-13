package com.mg153.RapidWinesListFr;

import java.util.List;
import com.mg153.RapidWinesListFr.R;
import com.mg153.RapidWinesListFr.Vin;

import android.app.Activity; 
import android.content.Context; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ArrayAdapter; 
import android.widget.ImageView; 
import android.widget.TextView;   

public class CustomListViewAdapter extends ArrayAdapter<Vin> {
       Context context;  
     
public CustomListViewAdapter(Context context, int resourceId,
             List<Vin> items) {
         super(context, resourceId, items);
         this.context = context;
     }

	String strnote;

    /*private view holder class*/
    private class ViewHolder {
         ImageView imageView;
         ImageView imageView2;
         TextView txtNom;
         TextView txtRegion;
         TextView txtPays;
         TextView txtPrix;     }

       public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder holder = null;
         Vin rowItem = getItem(position);
           LayoutInflater mInflater = (LayoutInflater) context
                 .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
         if (convertView == null) {
             convertView = mInflater.inflate(R.layout.list_item, null);
             holder = new ViewHolder();
             holder.txtNom = (TextView) convertView.findViewById(R.id.nom);
             holder.txtRegion = (TextView) convertView.findViewById(R.id.region);
             holder.txtPays = (TextView) convertView.findViewById(R.id.pays);
             holder.txtPrix = (TextView) convertView.findViewById(R.id.prix);
             holder.imageView = (ImageView) convertView.findViewById(R.id.favorite);
             holder.imageView2 = (ImageView) convertView.findViewById(R.id.bottle);
             convertView.setTag(holder);

        } else

             holder = (ViewHolder) convertView.getTag();
             holder.txtPrix.setText(rowItem.getprix());
             holder.txtNom.setText(rowItem.getnom());
             holder.txtRegion.setText(rowItem.getregion());
             holder.txtPays.setText(rowItem.getpays());
             strnote = rowItem.getnote();
 
             if (rowItem.getimageid() == 0 ) {
            	/* holder.imageView.setImageResource(R.drawable.vide); */
            	 holder.imageView.setImageResource(R.drawable.down); 
            	 holder.imageView2.setImageResource(R.drawable.bottle);
            	 if (strnote != null && !strnote.equals("")) {
            		 holder.imageView.setImageResource(R.drawable.mention);
            		 if (strnote.trim().equals("1 étoile")) {
            			holder.imageView.setImageResource(R.drawable.up);
            		 	}
            		 if (strnote.trim().equals("2 étoiles")) {
            			holder.imageView.setImageResource(R.drawable.up);
            		 	}
            		 if (strnote.trim().equals("3 étoiles")) {
            			holder.imageView.setImageResource(R.drawable.up);
            		 	}
            		 if (strnote.trim().equals("4 étoiles")) {
            			holder.imageView.setImageResource(R.drawable.up);
            		 	}
            		 if (strnote.trim().equals("5 étoiles")) {
            			holder.imageView.setImageResource(R.drawable.up);
            		 	}
            	 }
             }
             else  {
            	 holder.imageView.setImageResource(R.drawable.star_yellow);
            	 holder.imageView2.setImageResource(R.drawable.bottle);
            	 }
             return convertView;
     }
 }
