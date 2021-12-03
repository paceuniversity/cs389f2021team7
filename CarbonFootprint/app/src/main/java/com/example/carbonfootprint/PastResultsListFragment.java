package com.example.carbonfootprint;

import static com.example.carbonfootprint.DemoHomeActivity.databaseHelper;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;


public class PastResultsListFragment extends Fragment implements Serializable {

    Button PastResultsbtn1, PastResultsbtn2;
    EditText PastResultseditText, PastResultseditNumber;
    Switch switch1;
    RecyclerView recyclerView;
    public static final String CURRENT_USER_KEY = "CurrentUserKey";
    userInfo currentUser;
    ArrayAdapter pastResultsArrayAdapter;
//    public static DatabaseHelper databaseHelper;
    String householdNumber, homeEnergy, transportation, waste;
    ArrayList CardPastResults;
    RecyclerViewAdapterPastResults recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<String> xmlcountryname;
    ArrayList<String> xmlcountrycode;
    int indexCC;
    String CC;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PastResultsListFragment() {
    }

    public static PastResultsListFragment newInstance(String param1, String param2) {
        PastResultsListFragment fragment = new PastResultsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_results_list, container, false);

        currentUser = (userInfo) getActivity().getIntent().getSerializableExtra(CURRENT_USER_KEY);

        recyclerView = view.findViewById(R.id.recyclerView);



//        databaseHelper = new DatabaseHelper(getActivity());


//        if (currentUser.getPastResultsCheck() == true) {
//            PastResultsData pastResults = new PastResultsData(-1, currentUser.getName(), currentUser.getTimestamp(), currentUser.getCountryCode(), householdNumber, homeEnergy, transportation, waste, currentUser.getDemoTotal());
//            databaseHelper.add(pastResults);
//        }

        ShowOnListView (databaseHelper);

        currentUser.setPastResultsCheck(false);

        return view;
    }

    private void ShowOnListView (DatabaseHelper databaseHelper){

//        Toast.makeText(PastResultsActivity.this, databaseHelper.getEveryone().toString(), Toast.LENGTH_LONG).show();

//        ArrayList<CardViewPastResults> cardViewPastResults = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewAdapter = new RecyclerViewAdapterPastResults(databaseHelper.getEverything());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapterPastResults.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(PastResultsActivity.this, databaseHelper.getEveryone().get(position).getName(), Toast.LENGTH_LONG).show();

                currentUser.setPastResultsName(databaseHelper.getEveryone().get(position).getName());
                currentUser.setPastResultsTime(databaseHelper.getEveryone().get(position).getTime());
                currentUser.setPastResultsLocation(databaseHelper.getEveryone().get(position).getLocation());
                currentUser.setPastResultsHousehold(databaseHelper.getEveryone().get(position).getHousehold());
                currentUser.setPastResultsHomeEnergy(databaseHelper.getEveryone().get(position).getHomeEnergy());
                currentUser.setPastResultsTransportation(databaseHelper.getEveryone().get(position).getTransportation());
                currentUser.setPastResultsWaste(databaseHelper.getEveryone().get(position).getWaste());
                currentUser.setPastResultsTotal(databaseHelper.getEveryone().get(position).getTotal());
                currentUser.setPastResultsLocationAvg(databaseHelper.getEveryone().get(position).getLocationAvg());

                Intent intent = new Intent(getActivity(), PastResultsDetailsActivity.class);
                intent.putExtra(CURRENT_USER_KEY, currentUser);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                databaseHelper.delete(databaseHelper.getEveryone().get(position).getId());
                ShowOnListView(databaseHelper);
//                Toast.makeText(getActivity(), "deleted", Toast.LENGTH_LONG).show();


            }

        });
    }

}