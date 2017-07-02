package application.project.mhm.survey_controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import application.project.mhm.mental_healthcare_manaing.R;

/**
 * Created by Seungho Han on 2017-05-12.
 */

public class SurveyListViewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<SurveyListViewItem> itemLIst;
    private int layout;
    private RadioButton selectedRB;
    private int selectedRBPostion = -1;

    public SurveyListViewAdapter(int layout) {
        this.itemLIst = new ArrayList<>();
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return itemLIst.size();
    }

    @Override
    public Object getItem(int position) {
        return itemLIst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        final ViewHolder holder;
        if (convertView == null) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);

            holder = new ViewHolder();

            holder.SurveyListViewItemTv = (TextView) convertView.findViewById(R.id.survey_item_tv);
            holder.SurveyListViewItemRb = (RadioButton) convertView.findViewById(R.id.survey_item_rb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.survey_item_rl);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.SurveyListViewItemRb.setChecked(true);
                checkSelectedRB(position, holder);
                selectedRB = holder.SurveyListViewItemRb;
            }
        });

        holder.SurveyListViewItemRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSelectedRB(position, holder);
                selectedRB = (RadioButton) v;
            }
        });

        if(selectedRBPostion != position){
            holder.SurveyListViewItemRb.setChecked(false);
        }else{
            holder.SurveyListViewItemRb.setChecked(true);
            if(selectedRB != null && holder.SurveyListViewItemRb != selectedRB){
                selectedRB = holder.SurveyListViewItemRb;
            }
        }
        holder.SurveyListViewItemTv.setText(itemLIst.get(position).getItem());
        return convertView;
    }

    private void checkSelectedRB (int position, ViewHolder holder) {
        if (position != selectedRBPostion && selectedRB != null) {
            selectedRB.setChecked(false);
        }

        selectedRBPostion = position;
    }


    public void addItem(String text) {
        SurveyListViewItem item = new SurveyListViewItem();
        item.setItem(text);

        itemLIst.add(item);
    }

    static class ViewHolder {
        TextView SurveyListViewItemTv;
        RadioButton SurveyListViewItemRb;
        RelativeLayout relativeLayout;
    }
}
