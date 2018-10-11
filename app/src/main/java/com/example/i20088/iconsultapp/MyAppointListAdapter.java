package com.example.i20088.iconsultapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.i20088.iconsultapp.Model.Appointment;
import com.example.i20088.iconsultapp.Utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;

public class MyAppointListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Appointment> appointmentsList;
    private ArrayList<Object> objectsList;
    private TextView schDate;
    public MyAppointListAdapter(Context context, ArrayList<Object> objectsList) {
        this.objectsList = objectsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return objectsList.size();
    }

    @Override
    public Object getItem(int position) {

        return objectsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = null;
        Object object = objectsList.get(position);
        if (object instanceof Appointment) {
            listItemView = findAppointmentItem(position, (Appointment) object);
        }
        return listItemView;
    }
    private View findAppointmentItem(int position, Appointment aAppointments){
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem = inflater.inflate(R.layout.list_item_appointment, null);
        TextView schDate = listItem.findViewById(R.id.schDate);

        final Appointment eachAppointment = aAppointments;

        String schDateString = eachAppointment.getSchdate();
        Date dateCreatedOrUpdated = null;
        Date scheduleDate = DateUtils.convertStringToDate(schDateString, DateUtils.DATE_FORMAT_JAVA);
        if (scheduleDate != null) {
//            Date createdAtDate = DateUtils.convertStringToDate(createdAt, DateUtils.DATE_FORMAT_JAVA);
            String day = DateUtils.getDay(scheduleDate);
            String daySuffix = DateUtils.getDaySuffix(day);
            String monthName = DateUtils.getMonthName(scheduleDate);
            String shortYear = DateUtils.getShortYear(scheduleDate);
            String hour = DateUtils.getHourIn12HourFormat(scheduleDate);
            String minutes = DateUtils.getMinutes(scheduleDate);
            String ampm = DateUtils.getAmPm(scheduleDate);

           // String dateToShow = day + daySuffix + " " + monthName + "'" + shortYear + " " + hour + ":" + minutes + " " + ampm;
          schDate.setText(eachAppointment.getSchdate());
        }
        return null;
    }
}
