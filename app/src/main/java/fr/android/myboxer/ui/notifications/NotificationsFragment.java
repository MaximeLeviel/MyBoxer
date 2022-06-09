package fr.android.myboxer.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

import fr.android.myboxer.Match;
import fr.android.myboxer.Opposant;
import fr.android.myboxer.R;
import fr.android.myboxer.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {
    private Match match;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        EditText nom1 = view.findViewById(R.id.nom1);
        EditText nom2 = view.findViewById(R.id.nom2);
        EditText age1 = view.findViewById(R.id.age1);
        EditText age2 = view.findViewById(R.id.age2);
        EditText poids1 = view.findViewById(R.id.poids1);
        EditText poids2 = view.findViewById(R.id.poids2);
        EditText jour = view.findViewById(R.id.jour);
        EditText mois = view.findViewById(R.id.mois);
        EditText annee = view.findViewById(R.id.annee);
        Button todayButton = view.findViewById(R.id.today_button);
        final Calendar today = Calendar.getInstance();
        todayButton.setOnClickListener(v -> {
            jour.setText(String.valueOf(today.get(Calendar.DAY_OF_MONTH)));
            mois.setText(String.valueOf(today.get(Calendar.MONTH) + 1));
            annee.setText(String.valueOf(today.get(Calendar.YEAR)));
        });
        Button save = view.findViewById(R.id.save_button);
        save.setOnClickListener(v -> {
            Opposant opp1 = new Opposant(nom1.getText().toString(), Integer.parseInt(age1.getText().toString()), Integer.parseInt(poids1.getText().toString()));
            Opposant opp2 = new Opposant(nom2.getText().toString(), Integer.parseInt(age2.getText().toString()), Integer.parseInt(poids2.getText().toString()));
            Calendar date = Calendar.getInstance();
            date.set(Integer.parseInt(annee.getText().toString()), Integer.parseInt(mois.getText().toString()) - 1, Integer.parseInt(jour.getText().toString()));
            match = new Match(opp1, opp2, date);
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}