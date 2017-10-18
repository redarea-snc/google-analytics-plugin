package it.rikorda.phototeller.plugins.referrer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.android.gms.analytics.CampaignTrackingReceiver;

/**
 * Created by Rut on 18/10/2017.
 */
public class RedareaInstallReferrerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Si aggancia prima di tutto al tracker di Google
        CampaignTrackingReceiver campaignTrackingReceiver = new CampaignTrackingReceiver();
        campaignTrackingReceiver.onReceive(context, intent);

        // SALVATAGGIO REFERRER NELLE SHARED PREFERENCES
        if(intent != null){
            String installReferrer = intent.getStringExtra("referrer");
            String action = intent.getAction();

            if("com.android.vending.INSTALL_REFERRER".equals(action) && !TextUtils.isEmpty(installReferrer)) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("install_referrer", installReferrer);
                edit.commit();
            }
        }
    }
}
