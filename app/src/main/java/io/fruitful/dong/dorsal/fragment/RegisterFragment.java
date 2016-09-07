package io.fruitful.dong.dorsal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import io.fruitful.dong.dorsal.R;
import io.fruitful.dong.dorsal.Utils;
import io.fruitful.dong.dorsal.model.registerRespone.BodyRegister;
import io.fruitful.dong.dorsal.model.registerRespone.Error;
import io.fruitful.dong.dorsal.model.registerRespone.ResultRegister;
import io.fruitful.dong.dorsal.rest.RestAPI;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ares on 8/29/2016.
 */
public class RegisterFragment extends Fragment {
    private EditText mEdtName, mEdtEmail, mEdtPassword, mEdtRetypePassword;
    private Button mBtnSignUp;
    private ImageView mAvataUser;
    private Toolbar mToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_layout, container, false);
        mEdtName = (EditText) view.findViewById(R.id.edt_YourName_SignUp);
        mEdtEmail = (EditText) view.findViewById(R.id.edt_YourEmail_SignUp);
        mEdtPassword = (EditText) view.findViewById(R.id.edt_Password_SignUp);
        mEdtRetypePassword = (EditText) view.findViewById(R.id.edt_RetypePassword_SignUp);
        mBtnSignUp = (Button) view.findViewById(R.id.btn_SignUp_Register);
        mAvataUser = (ImageView) view.findViewById(R.id.imgv_Avata);
        mToolbar = (Toolbar) view.findViewById(R.id.my_tool_bar);
        mToolbar.setTitle(Utils.TAG_CREATE_USER);
        mToolbar.setNavigationIcon(R.drawable.ic_navigation);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mAvataUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChooseAvata();
            }
        });


        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();
            }
        });


        return view;
    }


    private void onChooseAvata() {

    }

    private void onRegister() {
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(mEdtName.getText().toString().trim())) {
            Toast.makeText(getContext(), "Username can't be empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(mEdtPassword.getText().toString().trim())) {
            Toast.makeText(getContext(), "Password can't be empty", Toast.LENGTH_LONG).show();

        } else if (
                TextUtils.isEmpty(mEdtRetypePassword.getText().toString().trim())) {
            Toast.makeText(getContext(), "Passwords can't be empty", Toast.LENGTH_LONG).show();

        } else if (TextUtils.isEmpty(mEdtEmail.getText().toString().trim())) {
            Toast.makeText(getContext(), "Email can't be empty", Toast.LENGTH_LONG).show();

        } else if (!mEdtEmail.getText().toString().trim().matches(emailPattern)) {
            Toast.makeText(getContext(), "Invalid email address", Toast.LENGTH_LONG).show();

        } else if (!mEdtPassword.getText().toString().trim().equals(mEdtRetypePassword.getText().toString().trim())) {
            Toast.makeText(getContext(), "These passwords don't match. Try again?", Toast.LENGTH_LONG).show();
        } else {


            String name = mEdtName.getText().toString();
            String email = mEdtEmail.getText().toString();
            String password = mEdtPassword.getText().toString();

            BodyRegister bodyRegister = new BodyRegister(
                    name, password, "04343435", email, 1,
                    "1.0", "PUSH NOTIFICATION DEVICE TOKEN",
                    "DEVICE ID", "20160829");


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.TAG_Base_Api)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            RestAPI service = retrofit.create(RestAPI.class);
            String json = new Gson().toJson(bodyRegister);
            RequestBody jsonBody = RequestBody.create(MediaType.parse("application/json"), json);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new byte[0]);
            MultipartBody.Part partFile = MultipartBody.Part.createFormData("file", "filename", requestFile);
            Call<ResultRegister> call = service.register(jsonBody, partFile);
            call.enqueue(new Callback<ResultRegister>() {
                @Override
                public void onResponse(Call<ResultRegister> call, Response<ResultRegister> response) {
                    ResultRegister resultRegister = response.body();
                    Error error = resultRegister.getError();

                    if (error != null && error.getCode() == 1035) {
                        int accountId = resultRegister.getResponseData().getAccount().getId();
                        Log.e("id", accountId + "");
                        VerificationFragment fragment = new VerificationFragment();
                        Bundle bundle = new Bundle();
                        fragment.setArguments(bundle);
                        bundle.putInt(Utils.TAG_BUN_EXTRA_ID_COUNT, accountId);
                        getActivity()
                                .getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                                .replace(R.id.frame, fragment).addToBackStack(null).commit();

                        mEdtName.setText("");
                        mEdtPassword.setText("");
                        mEdtRetypePassword.setText("");
                        mEdtEmail.setText("");

                    } else {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(Call<ResultRegister> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("error", t.getMessage());
                }
            });
        }


    }


}
