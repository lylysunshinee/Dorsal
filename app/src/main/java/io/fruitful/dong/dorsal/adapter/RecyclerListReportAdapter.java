package io.fruitful.dong.dorsal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import io.fruitful.dong.dorsal.R;


import io.fruitful.dong.dorsal.model.listResportRespone.ResponseDatum;

/**
 * Created by Ares on 8/31/2016.
 */
public class RecyclerListReportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private final int VIEW_TYPE_HEADER = 2;
    private Context mContext;
    private List<ResponseDatum> mResponseDataLists;


    public RecyclerListReportAdapter(Context mContext, List<ResponseDatum> responseDataLists) {
        this.mContext = mContext;
        this.mResponseDataLists = responseDataLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.card_row_report_layout, parent, false);
            ListReport listReport = new ListReport(view);
            return listReport;
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.card_row_loadmore_layout, parent, false);
            return new LoadingViewHolder(view);

        } else if (viewType == VIEW_TYPE_HEADER) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_transparent_layout, parent, false);
            return new TransparentViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListReport) {
            ListReport reportHolder = (ListReport) holder;
            reportHolder.mLocation.setText(mResponseDataLists.get(position).getLocation());
            reportHolder.mState.setText(mResponseDataLists.get(position).getState() + "-");
            reportHolder.mZone.setText(mResponseDataLists.get(position).getZone() + "-");
            reportHolder.mTime.setText(mResponseDataLists.get(position).getFormattedReportTime());


        } else if (holder instanceof LoadingViewHolder) {

            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.mProgressBar.setIndeterminate(true);
        }


    }


    @Override
    public int getItemCount() {
        return mResponseDataLists.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position==0){
            return VIEW_TYPE_HEADER;

        }else if (mResponseDataLists.get(position)==null){

            return VIEW_TYPE_LOADING;

        }else if (mResponseDataLists.get(position) !=null){

            return VIEW_TYPE_ITEM;
        }
          else return -1;


//        return mResponseDataLists.get(position) == null ? VIEW_TYPE_LOADING  : VIEW_TYPE_ITEM  ;




    }


    public class ListReport extends RecyclerView.ViewHolder {
        public TextView mState, mZone, mLocation, mTime;


        public ListReport(View itemView) {
            super(itemView);
            mState = (TextView) itemView.findViewById(R.id.txt_state_row);
            mZone = (TextView) itemView.findViewById(R.id.txt_zone);
            mLocation = (TextView) itemView.findViewById(R.id.txt_location_row);
            mTime = (TextView) itemView.findViewById(R.id.txt_timeReport_row);


        }
    }


    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar mProgressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }

    public class TransparentViewHolder extends RecyclerView.ViewHolder {


        public TransparentViewHolder(View itemView) {
            super(itemView);
        }
    }

}
