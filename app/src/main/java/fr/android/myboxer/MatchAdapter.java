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
        final Match match = matchs.get(position);

        TextView opposant1 = (TextView) convertView.findViewById(R.id.opposant1);
        opposant1.setText(context.getResources().getString(R.string.list_item_title, match.getOpposant1().getNom(), match.getOpposant1().getAge(), match.getOpposant1().getPoids()));

        TextView opposant2 = (TextView) convertView.findViewById(R.id.opposant2);
        opposant2.setText(context.getResources().getString(R.string.list_item_title, match.getOpposant2().getNom(), match.getOpposant2().getAge(), match.getOpposant2().getPoids()));

        TextView date = (TextView) convertView.findViewById(R.id.date);
        date.setText(context.getResources().getString(R.string.list_item_date, match.getDate().get(Calendar.DAY_OF_MONTH), match.getDate().get(Calendar.MONTH), match.getDate().get(Calendar.YEAR)));

        TextView gagne = (TextView) convertView.findViewById(R.id.gagne);
        if(matchs.get(position).isGagne()) {
            gagne.setText(context.getResources().getString(R.string.gagne));
        } else {
            gagne.setText(context.getResources().getString(R.string.perdu));
        }

        return convertView;
    }
}
