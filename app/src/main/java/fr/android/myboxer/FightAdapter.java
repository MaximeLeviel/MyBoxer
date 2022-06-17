package fr.android.myboxer;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FightAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private final Context context;
    private final List<Fight> fights;

    public FightAdapter(Context aContext, List<Fight> listData) {
        this.context = aContext;
        this.fights = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return fights.size();
    }

    @Override
    public Object getItem(int position) {
        return fights.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        final Fight fight = fights.get(position);

        TextView opposant1 = convertView.findViewById(R.id.opposant1);
        opposant1.setText(context.getResources().getString(R.string.list_item_title, fight.getOpposant1().getNom(), fight.getOpposant1().getAge(), fight.getOpposant1().getPoids()));

        TextView opposant2 = convertView.findViewById(R.id.opposant2);
        opposant2.setText(context.getResources().getString(R.string.list_item_title, fight.getOpposant2().getNom(), fight.getOpposant2().getAge(), fight.getOpposant2().getPoids()));

        TextView date = convertView.findViewById(R.id.date);
        date.setText(context.getResources().getString(R.string.list_item_date, fight.getDate().get(Calendar.DAY_OF_MONTH), fight.getDate().get(Calendar.MONTH), fight.getDate().get(Calendar.YEAR)));

        TextView gagne = convertView.findViewById(R.id.gagne);
        if(fights.get(position).isGagne()) {
            gagne.setText(context.getResources().getString(R.string.gagne));
        } else {
            gagne.setText(context.getResources().getString(R.string.perdu));
        }

        TextView address = convertView.findViewById(R.id.item_address);

        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(fight.getLat(), fight.getLng(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String addressText = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            address.setText(addressText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
