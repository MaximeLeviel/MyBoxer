package fr.android.myboxer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.List;
import fr.android.myboxer.Database;
import fr.android.myboxer.Match;
import fr.android.myboxer.MatchAdapter;
import fr.android.myboxer.R;

public class FightsFragment extends Fragment {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fights, container, false);

        Database database = new Database();
        List<Match> matchs = database.getAllMatchs();

        MatchAdapter matchAdapter = new MatchAdapter(this.getContext(), matchs);
        ListView listView = view.findViewById(R.id.list);
        listView.setAdapter(matchAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}