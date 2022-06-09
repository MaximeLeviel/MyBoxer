package fr.android.myboxer.ui.credit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import fr.android.myboxer.databinding.FragmentCreditBinding;
import fr.android.myboxer.ui.credit.CreditViewModel;

public class CreditFragment extends Fragment {

    private FragmentCreditBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CreditViewModel CreditViewModel =
                new ViewModelProvider(this).get(CreditViewModel.class);

        binding = FragmentCreditBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
