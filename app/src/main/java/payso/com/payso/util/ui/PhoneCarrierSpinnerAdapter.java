package payso.com.payso.util.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

import payso.com.payso.R;
import payso.com.payso.model.CarrierItemData;

/**
 * This adapter class provides the phone carrier names along with their country code.
 *
 * Created by mabdira on 1/1/18.
 */

public class PhoneCarrierSpinnerAdapter extends ArrayAdapter<CarrierItemData> {

    private static final String TAG = PhoneCarrierSpinnerAdapter.class.getSimpleName();

    private int mGroupID;
    private Context mContext;
    private ArrayList<CarrierItemData> mList;
    private LayoutInflater mInflater;

    public PhoneCarrierSpinnerAdapter(Context context, int groupID, int id,
                                      ArrayList<CarrierItemData> list){
        super(context, id, list);

        mList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mGroupID = groupID;

        Log.d(TAG, String.format("Created a phone carrier spinner adapter with the following data " +
                "GroudID: %d, Phone Carrier Item List: %s", groupID, list));
    }

    /**
     * Override the adapter view method to create a customized view of each row of the spinner items
     * @param position
     * @param convertView
     * @param parent
     * @return view
     */
    public View getView(int position, View convertView, ViewGroup parent ){
        Log.d(TAG, "getView() method is called...");

        View itemView = mInflater.inflate(mGroupID, parent, false);

        TextView carrierNameTV = itemView.findViewById(R.id.carrierNameTextview);
        carrierNameTV.setText(mList.get(position).getCarrierName());

        TextView countryCodeTV = itemView.findViewById(R.id.countryCodeTextview);
        if(mList.get(position).getCountryCode() == 0) {
            countryCodeTV.setText("");
        } else {
            countryCodeTV.setText(String.format("+%d", mList.get(position).getCountryCode()));
        }

        return itemView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return getView(position, convertView, parent);
    }

}
