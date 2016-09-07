package io.fruitful.dong.dorsal.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.fruitful.dong.dorsal.R;
import io.fruitful.dong.dorsal.Utils;
import io.fruitful.dong.dorsal.model.activateRespone.BodyActivate;
import io.fruitful.dong.dorsal.model.activateRespone.ResuPassCode;
import io.fruitful.dong.dorsal.model.registerRespone.Error;
import io.fruitful.dong.dorsal.rest.RestAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ares on 8/30/2016.
 */
public class VerificationFragment extends Fragment implements View.OnClickListener {
    private EditText mEdtPassCode;
    private Button mBtnDone;
    private TextView mTxtResend;
    private int mAcountID;
    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verification_layout, container, false);
        mAcountID = getArguments().getInt(Utils.TAG_BUN_EXTRA_ID_COUNT);
        mEdtPassCode = (EditText) view.findViewById(R.id.edt_code_verifi);
        mBtnDone = (Button) view.findViewById(R.id.btn_Done_Verifi);
        mTxtResend = (TextView) view.findViewById(R.id.txt_resend_verifi);
        mTxtResend.setPaintFlags(mTxtResend.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mBtnDone.setOnClickListener(this);
        mTxtResend.setOnClickListener(this);
        mToolbar = (Toolbar) view.findViewById(R.id.my_tool_bar_verification);
        mToolbar.setTitle(Utils.TAG_VERIFI);

        mToolbar.setNavigationIcon(R.drawable.ic_navigation);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Done_Verifi:
                onDone();
                break;
            case R.id.txt_resend_verifi:
                onResend();
                break;


        }
    }

    private void onResend() {


    }

    private void onDone() {
        final String mPasscode = mEdtPassCode.getText().toString();
        BodyActivate bodyActivate = new BodyActivate(mAcountID, mPasscode, 1, "1.0", "PUSH NOTIFICATION DEVICE TOKEN", "DEVICE ID", "20160829");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.TAG_Base_Api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAPI service = retrofit.create(RestAPI.class);
        Call<ResuPassCode> call = service.RESULT_ACTIVATE_CALL("application/json", bodyActivate);


        call.enqueue(new Callback<ResuPassCode>() {
            @Override
            public void onResponse(Call<ResuPassCode> call, Response<ResuPassCode> response) {
                ResuPassCode resuPassCode = response.body();


                if (resuPassCode == null) {

                    try {
                        String str = response.errorBody().string();
                        JSONObject object = new JSONObject(str);
                        JSONObject jsonObject = object.getJSONObject("error");
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");

                        Toast.makeText(getContext(), code + " + " + message, Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (resuPassCode.getError() == null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(Utils.TAG_BUN_EXTRA, resuPassCode.getResponseData().getToken());
                    Fragment mFragment = new ShowListFragment();
                    mFragment.setArguments(bundle);
                    getActivity()
                            .getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                            .replace(R.id.frame, mFragment).addToBackStack(null).commit();
                    mEdtPassCode.setText("");


                } else if (resuPassCode.getError() != null) {
                    mEdtPassCode.setText("");
                    Toast.makeText(getContext(), "Error pass code", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ResuPassCode> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
