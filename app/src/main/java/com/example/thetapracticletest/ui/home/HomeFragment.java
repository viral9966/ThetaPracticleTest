package com.example.thetapracticletest.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thetapracticletest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    // TODO: Rename parameter arguments, choose names that match
    List<Modellist> modellists = new ArrayList<>();
    ListAdapter listAdapter;
    RecyclerView recyclerView;
    public int current_page = 1;
    boolean loadmore = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.rv_list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        listAdapter = new ListAdapter(getActivity(), modellists);
        recyclerView.setAdapter(listAdapter);

        if (Network.isConnectionFast(getActivity())) {
            modellists.clear();
            current_page = 1;
            new Get().execute();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    // super.onScrolled(recyclerView, dx, dy);
                    int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition == recyclerView.getChildCount()) {
                        if (loadmore) {
                            loadmore = false;
                            current_page++;
                            loadmore();
                        }
                    }
                }
            });

            // Adds the scroll listener to RecyclerView

        } else {
            Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT);
        }

        return root;
    }

    public void loadmore() {
        loadmore = true;
        new Get().execute();
    }

    class Get extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            RequestHandler handler = new RequestHandler();
            Log.e("bbvv", current_page + "");
            return handler.sendGetRequest("https://reqres.in/api/users?page=" + current_page);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject parentObject = new JSONObject(s);
                String total = parentObject.getString("total");
                Log.e("vbvvb", total + "");

                JSONArray jArray = new JSONArray(parentObject.getString("data"));
                for (int i = 0; i < jArray.length(); i++) {
                    Modellist k = new Modellist();

                    JSONObject jObject = jArray.getJSONObject(i);
                    Log.e("vbvvb", jObject.getString("first_name"));
                    k.setName(jObject.getString("first_name") + " " + jObject.getString("last_name"));
                    k.setImgurl(jObject.getString("avatar"));
                    modellists.add(k);
                }
                listAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
