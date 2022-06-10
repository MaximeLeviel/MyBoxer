package fr.android.myboxer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class MatchAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private final Context context;
    private List<Match> matchs;

    public MatchAdapter(Context aContext,  List<Match> listData) {
        this.context = aContext;
        this.matchs = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return matchs.size();
    }

    @Override
    public Object getItem(int position) {
        return matchs.get(position);
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

        TextView opposant1 = (TextView) convertView.findViewById(R.id.opposant1);
        opposant1.setText(matchs.get(position).getOpposant1().getNom() + " (" + matchs.get(position).getOpposant1().getAge() + ")" + " " + matchs.get(position).getOpposant1().getPoids() + "kg");

        TextView opposant2 = (TextView) convertView.findViewById(R.id.opposant2);
        opposant2.setText(matchs.get(position).getOpposant2().getNom() + " (" + matchs.get(position).getOpposant2().getAge() + ")" + " " + matchs.get(position).getOpposant2().getPoids() + "kg");

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(matchs.get(position).getDate().get(Calendar.DAY_OF_MONTH) + "/" + (matchs.get(position).getDate().get(Calendar.MONTH) + 1) + "/" + matchs.get(position).getDate().get(Calendar.YEAR));

        TextView gagne = (TextView) convertView.findViewById(R.id.gagne);
        if(matchs.get(position).isGagne()) {
            gagne.setText("Gagné");
        } else {
            gagne.setText("Perdu");
        }

        return convertView;
    }
}
