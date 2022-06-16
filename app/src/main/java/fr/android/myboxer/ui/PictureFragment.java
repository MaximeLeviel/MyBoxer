package fr.android.myboxer.ui;

import static android.app.Activity.RESULT_OK;
import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import fr.android.myboxer.R;

public class PictureFragment extends Fragment {
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_picture, container, false);

        Button pictureButton = view.findViewById(R.id.picture_button);
        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, 100);
                }
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    startActivityForResult(takePictureIntent, 123);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }
}
