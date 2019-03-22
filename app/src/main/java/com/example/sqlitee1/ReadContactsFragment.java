package com.example.sqlitee1;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadContactsFragment extends Fragment {

    private TextView Txt_Display;
    public ReadContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_read_contacts, container, false);
        Txt_Display=view.findViewById(R.id.txt_display);
        raedcontacts();
        return view;
    }

    private void raedcontacts()
    {
        ContactDbHelper contactDbHelper=new ContactDbHelper(getActivity());
        SQLiteDatabase database=contactDbHelper.getReadableDatabase();

        Cursor cursor=contactDbHelper.readContacts(database);

        String info = "";
        while (cursor.moveToNext())
        {
            String id=Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactCobtract.ContactEntry.CONTACT_ID)));

            String name=cursor.getString(cursor.getColumnIndex(ContactCobtract.ContactEntry.NAME));
            String email=cursor.getString(cursor.getColumnIndex(ContactCobtract.ContactEntry.EMAIL));
            info = info + "\n\n" + "ID : " + id + " \n Name : " + name + "\n Email : " + email;

        }
        Txt_Display.setText(info);

        contactDbHelper.close();
    }
}
