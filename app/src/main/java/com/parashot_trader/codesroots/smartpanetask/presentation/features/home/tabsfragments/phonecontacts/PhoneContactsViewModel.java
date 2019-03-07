package com.parashot_trader.codesroots.smartpanetask.presentation.features.home.tabsfragments.phonecontacts;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;
import com.parashot_trader.codesroots.smartpanetask.entities.contact;
import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class PhoneContactsViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    Activity activity;
    private ArrayList<contact> contactsdata = new ArrayList<>();
    public MutableLiveData<List<contact>> contactsdataMutableLiveData = new MutableLiveData<>();
    private contact contact = new contact();
    public PhoneContactsViewModel(Activity activity1) {
        activity = activity1;
        getContactList();
    }

    private void getContactList() {
        ContentResolver cr = activity.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);
                        contactsdata.add(new contact(name,phoneNo));
                    }
                    pCur.close();
                }
            }
            contactsdataMutableLiveData.postValue(contactsdata);
        }
        if(cur!=null){
            cur.close();
        }
    }
}
