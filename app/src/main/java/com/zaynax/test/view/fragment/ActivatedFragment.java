package com.zaynax.test.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.zaynax.test.Network.callback.ActItemClickListener;
import com.zaynax.test.R;
import com.zaynax.test.databinding.FragmentActivatedBinding;
import com.zaynax.test.model.Item;
import com.zaynax.test.repository.ActivatedApiCall;
import com.zaynax.test.view.adapter.ActivationAdapter;
import com.zaynax.test.viewmodel.ActivatedViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class ActivatedFragment extends Fragment implements ActItemClickListener {

    private FragmentActivatedBinding activatedBinding;
    private ActivationAdapter activationAdapter;
    private ActivatedViewModel activatedViewModel;
    private ActivatedApiCall activatedApiCall;
    private ArrayList<Item> activationList = new ArrayList<>();
    private KProgressHUD progressHUD;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activatedBinding = FragmentActivatedBinding.inflate(inflater, container, false);
        initVariables();
        initView();
        initFunction();
        initListener();

        return activatedBinding.getRoot();
    }

    private void initVariables() {
        activatedViewModel = new ViewModelProvider(getActivity()).get(ActivatedViewModel.class);
        activatedApiCall = new ActivatedApiCall();
    }

    private void initView() {

    }

    private void initFunction() {
        // hide key board
        hideKeyboard(getActivity());
        // Load All activation list on RecyclerView
        activatedBinding.rcActivisionList.setLayoutManager(new LinearLayoutManager(getContext()));
        activationAdapter = new ActivationAdapter(getContext(),activationList);
        activatedBinding.rcActivisionList.setAdapter(activationAdapter);
        activationAdapter.setClickListener(this);



        // call api
        activatedViewModel.getActivationList(getContext(),"1",activatedApiCall);
    }

    private void initListener() {

        activatedViewModel.progressLiveData.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    // dialog.show();
                    StartLoading(getActivity());
                } else {
                    //dialog.dismiss();
                    if ((progressHUD != null) && progressHUD.isShowing())
                        progressHUD.dismiss();
                    progressHUD = null;
                }
            }
        });


        activatedViewModel.activationLiveData.observe(getActivity(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                activationList.clear();
                activationList.addAll(items);
                activationAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void onItemClick(View view, int position) {
        showDetailsDialog(view,activationList.get(position).getName(),activationList.get(position).getPhoneNumber(),activationList.get(position).getId());
    }


    // show details popup view
    private void showDetailsDialog(View view,String acName,String acPhone,String acId) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_activation_details, null);
        TextView name = popupView.findViewById(R.id.tv_name);
        TextView phone = popupView.findViewById(R.id.tv_phone);
        TextView id = popupView.findViewById(R.id.tv_id);

        name.setText(acName);
        phone.setText(acPhone);
        id.setText(acId);
        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.setAnimationStyle(R.style.WindowAnimations);

        // show the popup window
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void StartLoading(Activity activity) {

        progressHUD = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                //.setLabel(activity.getString(R.string.Please_wait))
                .setCancellable(true)
                .setWindowColor(activity.getResources().getColor(R.color.transparent))
                .setAnimationSpeed(2)
                .setDimAmount(0.4f)
                .show();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}