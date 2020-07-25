package com.example.currency_converter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> {
    public CountryAdapter(Context context, ArrayList<Country> countryArrayList) {
        super(context,0,countryArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null)
        {
            //convertView = createRow(position, (ListView) parent, viewHolder);
            convertView =LayoutInflater.from(this.getContext()).inflate(R.layout.country_layout1, null);
            viewHolder = new ViewHolder();
            viewHolder.flag = (ImageView) convertView.findViewById(R.id.country_flag);
            viewHolder.countryName = (TextView) convertView.findViewById(R.id.country_name);
            viewHolder.countryCurrency = (TextView) convertView.findViewById(R.id.currency_name);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.convertAmount);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Country country = this.getItem(position);
        viewHolder.countryName.setText(country.getName());
        viewHolder.countryCurrency.setText(country.getCurrency());

        int imageId = getMipmapResIdByName(country.getFlagName());
        viewHolder.flag.setImageResource(imageId);

        viewHolder.amount.setText(toString().valueOf(country.getAmount()));

        return convertView;
    }

    public int getMipmapResIdByName(String flagName) {
        String pkgName = getContext().getPackageName();
        // return 0 if not found
        int resID = getContext().getResources().getIdentifier(flagName, "mipmap", pkgName);
        return resID;
    }

    static class ViewHolder {
        ImageView flag;
        TextView countryName;
        TextView countryCurrency;
        TextView amount;
    }

}
