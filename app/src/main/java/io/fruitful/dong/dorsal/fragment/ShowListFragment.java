package io.fruitful.dong.dorsal.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.fruitful.dong.dorsal.R;
import io.fruitful.dong.dorsal.Utils;
import io.fruitful.dong.dorsal.adapter.RecyclerListReportAdapter;
import io.fruitful.dong.dorsal.model.listResportRespone.BodyListReport;

import io.fruitful.dong.dorsal.model.listResportRespone.ResponseDatum;
import io.fruitful.dong.dorsal.model.listResportRespone.ResultList;
import io.fruitful.dong.dorsal.rest.RestAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ares on 8/29/2016.
 */
public class ShowListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private TextView mTextError;
    private boolean mIsLoading;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressDialog mDialog;
    private LinearLayoutManager mLayoutManager;
    private BodyListReport mBodyListReport;
    private List<ResponseDatum> mResultList;
    private String mTocken = "";
    private RecyclerListReportAdapter mRecyclerListReportAdapter;
    private int mTotalItemCount, mLastVisibleItem;
    private Toolbar mToolbar;
    private int mTimeRange = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showlist_layout, container, false);
        mTocken = getArguments().getString(Utils.TAG_BUN_EXTRA);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_report);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabReset);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mTextError = (TextView) view.findViewById(R.id.tvError);

        mToolbar = (Toolbar) view.findViewById(R.id.my_tool_bar_showlist);
        mToolbar.setTitle("List All Report");
        mToolbar.setNavigationIcon(R.drawable.ic_menu);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mSwipeRefreshLayout.setRefreshing(false);
                loadData();
            }
        });

        loadData();


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mSwipeRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
                if (dy > 0) {
                    mTotalItemCount = mLayoutManager.getItemCount();
                    mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                    if (!mIsLoading && mTotalItemCount <= (mLastVisibleItem + 1)) {
                        Log.e("zzz", mTotalItemCount + " - " + mLastVisibleItem + mIsLoading);
                        loadMoreData();
                    }
                }
            }


        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.last_12:
                mTimeRange = 12;
                loadData();
                break;
            case R.id.last_24:
                mTimeRange = 24;
                loadData();
                break;
            case R.id.last_week:
                mTimeRange = 168;
                loadData();
                break;
            case R.id.last_month:
                mTimeRange = 720;
                loadData();
                break;
            case R.id.allTime:
                mTimeRange = 0;
                loadData();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void loadMoreData() {
        mIsLoading = true;
        mResultList.add(null);
        mBodyListReport.setPageIndex(mBodyListReport.getPageIndex() + 1);
        mRecyclerListReportAdapter.notifyItemInserted(mResultList.size() - 1);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.TAG_Base_Api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestAPI service = retrofit.create(RestAPI.class);

        Call<ResultList> call = service.RESULT_LIST_CALL(mTocken, mBodyListReport);

        call.enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {

                mResultList.remove(mResultList.size() - 1);
                mResultList.addAll(response.body().getResponseData());
                mRecyclerListReportAdapter.notifyDataSetChanged();
                mIsLoading = false;

            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                mIsLoading = false;
            }
        });


    }


    private void loadData() {


        mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("Loading ...");
        mDialog.show();
        mResultList = new ArrayList<>();

        mBodyListReport = new BodyListReport(true, "", mTimeRange, 0, 10);


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

        Call<ResultList> call = service.RESULT_LIST_CALL(mTocken, mBodyListReport);

        call.enqueue(new Callback<ResultList>() {
            @Override
            public void onResponse(Call<ResultList> call, Response<ResultList> response) {
                if (mDialog.isShowing())
                    mDialog.dismiss();

                mTextError.setVisibility(View.GONE);
                mResultList = response.body().getResponseData();


                showData();

            }

            @Override
            public void onFailure(Call<ResultList> call, Throwable t) {
                Log.e("bug", t.getMessage());
                if (mDialog.isShowing())
                    mDialog.dismiss();
            }
        });

    }

    private void showData() {
        mRecyclerListReportAdapter = new RecyclerListReportAdapter(getContext(), mResultList);
        mRecyclerView.setAdapter(mRecyclerListReportAdapter);
    }
}
