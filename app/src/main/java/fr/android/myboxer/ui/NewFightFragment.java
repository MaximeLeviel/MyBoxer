package fr.android.myboxer.ui;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import fr.android.myboxer.Database;
import fr.android.myboxer.Fight;
import fr.android.myboxer.Opposant;
import fr.android.myboxer.R;

public class NewFightFragment extends Fragment {
    private Fight fight;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new_fight, container, false);
        EditText nom1 = view.findViewById(R.id.nom1);
        EditText nom2 = view.findViewById(R.id.nom2);
        EditText age1 = view.findViewById(R.id.age1);
        EditText age2 = view.findViewById(R.id.age2);
        EditText poids1 = view.findViewById(R.id.poids1);
        EditText poids2 = view.findViewById(R.id.poids2);
        EditText jour = view.findViewById(R.id.jour);
        EditText mois = view.findViewById(R.id.mois);
        EditText annee = view.findViewById(R.id.annee);
        EditText adresse = view.findViewById(R.id.adresse);
        Button todayButton = view.findViewById(R.id.today_button);
        final Calendar today = Calendar.getInstance();
        todayButton.setOnClickListener(v -> {
            jour.setText(String.valueOf(today.get(Calendar.DAY_OF_MONTH)));
            mois.setText(String.valueOf(today.get(Calendar.MONTH) + 1));
            annee.setText(String.valueOf(today.get(Calendar.YEAR)));
        });
        CheckBox gagne = view.findViewById(R.id.gagne);
        Button save = view.findViewById(R.id.save_button);
        save.setOnClickListener(v -> {
            Opposant opp1 = new Opposant(nom1.getText().toString(), Integer.parseInt(age1.getText().toString()), Integer.parseInt(poids1.getText().toString()));
            Opposant opp2 = new Opposant(nom2.getText().toString(), Integer.parseInt(age2.getText().toString()), Integer.parseInt(poids2.getText().toString()));
            Calendar date = Calendar.getInstance();
            date.set(Integer.parseInt(annee.getText().toString()), Integer.parseInt(mois.getText().toString()) - 1, Integer.parseInt(jour.getText().toString()));
            fight = new Fight(opp1, opp2, date, gagne.isChecked());

            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            try {
                List<Address> addresses = geocoder.getFromLocationName(adresse.getText().toString(), 1);
                if(addresses.size() > 0) {
                    fight.setLat(addresses.get(0).getLatitude());
                    fight.setLng(addresses.get(0).getLongitude());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            final Database database = new Database();
            database.save(fight);
            Toast toast = Toast.makeText(getContext(), getContext().getResources().getString(R.string.enregistre), Toast.LENGTH_SHORT);
            toast.show();
            nom1.setText("");
            nom2.setText("");
            age1.setText("");
            age2.setText("");
            poids1.setText("");
            poids2.setText("");
            jour.setText("");
            mois.setText("");
            annee.setText("");
            adresse.setText("");
            gagne.setChecked(false);
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}