package io.fruitful.dong.dorsal.fragment;

import android.app.AlertDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

import io.fruitful.dong.dorsal.model.loginRespone.BodyLogin;
import io.fruitful.dong.dorsal.model.loginRespone.ResponeLogin;
import io.fruitful.dong.dorsal.model.loginRespone.ResponseData;
import io.fruitful.dong.dorsal.model.registerRespone.Error;
import io.fruitful.dong.dorsal.rest.RestAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ares on 8/29/2016.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText mEdtEmail, mEdtPass;
    private Button mBtnLogin, mBtnSignUp;
    private TextView mTxtForgot, mBtnFbLogin;
    private BodyLogin mBodyLogin;
    private ResponseData mResponseData;
    private AlertDialog mAlertDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_layout, container, false);
        mEdtEmail = (EditText) view.findViewById(R.id.edt_Email_login);
        mEdtPass = (EditText) view.findViewById(R.id.edt_password_login);
        mBtnLogin = (Button) view.findViewById(R.id.btn_login);
        mBtnFbLogin = (TextView) view.findViewById(R.id.txt_login_fb);
        mBtnSignUp = (Button) view.findViewById(R.id.btn_SignUp_login);
        mTxtForgot = (TextView) view.findViewById(R.id.txt_forgot_login);
        mBtnSignUp.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mTxtForgot.setOnClickListener(this);
        mBtnFbLogin.setOnClickListener(this);
        mTxtForgot.setPaintFlags(mTxtForgot.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                onLogin();
                break;
            case R.id.btn_SignUp_login:
                onSignUp();
                break;
            case R.id.txt_login_fb:
                onLoginFb();
                break;
            case R.id.txt_forgot_login:
                onForgotPass();
                break;

        }


    }

    private void onForgotPass() {

    }

    private void onLoginFb() {

    }

    private void onSignUp() {
        Fragment mFragment = new RegisterFragment();
        getActivity()
                .getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.frame, mFragment).addToBackStack(null).commit();


    }

    private void onLogin() {


        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(mEdtEmail.getText().toString().trim())) {
            Toast.makeText(getContext(), "Email can't be empty", Toast.LENGTH_LONG).show();

        } else if (!mEdtEmail.getText().toString().trim().matches(emailPattern)) {
            Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(mEdtPass.getText().toString().trim())) {
            Toast.makeText(getContext(), "Password can't be empty", Toast.LENGTH_LONG).show();
        } else {

            mBodyLogin = new BodyLogin(1, "1.0", "DEVICE PUSH NOTIFICATION TOKEN", "DEVICE ID", "20160829");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.TAG_Base_Api)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RestAPI service = retrofit.create(RestAPI.class);
            Call<ResponeLogin> call = service.postUser(mEdtEmail.getText().toString(), mEdtPass.getText().toString(), mBodyLogin);
            call.enqueue(new Callback<ResponeLogin>() {
                @Override
                public void onResponse(Call<ResponeLogin> call, Response<ResponeLogin> response) {

                    ResponeLogin responeLogin = response.body();
                    if (responeLogin == null) {

                        try {
                            String str = response.errorBody().string();
                            Log.e("errorBody", str);
                            JSONObject object = new JSONObject(str);
                            JSONObject jsonObject = object.getJSONObject("error");
                            String code = jsonObject.getString("code");
                            String message = jsonObject.getString("message");
                            Log.e("errorBody", code + message);


                            Toast.makeText(getContext(), code + " + " + message, Toast.LENGTH_LONG).show();


                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Error error = responeLogin.getError();
                        if (error == null) {
                            mResponseData = responeLogin.getResponseData();

                            Bundle bundle = new Bundle();
                            bundle.putString(Utils.TAG_BUN_EXTRA, mResponseData.getToken());
                            Fragment mFragment = new ShowListFragment();
                            mFragment.setArguments(bundle);
                            getActivity()
                                    .getSupportFragmentManager().beginTransaction()
                                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                                    .replace(R.id.frame, mFragment).addToBackStack(null).commit();
                            mEdtPass.setText("");
                            mEdtEmail.setText("");
                        } else {
                            if (error.getCode() == 1035) {
                                int accountId = responeLogin.getResponseData().getAccount().getId();
                                Log.e("id", accountId + "");
                                VerificationFragment fragment = new VerificationFragment();
                                Bundle bundle = new Bundle();
                                fragment.setArguments(bundle);
                                bundle.putInt(Utils.TAG_BUN_EXTRA_ID_COUNT, accountId);
                                getActivity()
                                        .getSupportFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.frame, fragment).addToBackStack(null).commit();
                            } else
                                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }


                }

                @Override
                public void onFailure(Call<ResponeLogin> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });


        }


    }
}
