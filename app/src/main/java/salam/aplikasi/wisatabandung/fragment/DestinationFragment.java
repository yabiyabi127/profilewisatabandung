package salam.aplikasi.wisatabandung.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import salam.aplikasi.wisatabandung.R;
import salam.aplikasi.wisatabandung.adapter.DestinationAdapter;
import salam.aplikasi.wisatabandung.model.Destination;

public class DestinationFragment extends Fragment {

    ArrayList<Destination> Contacts;
    private RecyclerView recyclerView;
    private DestinationAdapter destinationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_wisata, container, false);
        loadDestination();

        recyclerView = vi.findViewById(R.id.recyclerView);
        destinationAdapter = new DestinationAdapter(Contacts, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(destinationAdapter);

        return vi;
    }

    private void loadDestination() {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray array = obj.getJSONArray("destination");

            Contacts = new ArrayList<Destination>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String location = object.getString("location");
                String description = object.getString("description");
                String image = object.getString("image");
                String lat = object.getString("latitude");
                String longitude = object.getString("longitude");
                Destination objt = new Destination(name, location, description, image, lat, longitude);
                Contacts.add(objt);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("destination.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}