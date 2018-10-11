package com.example.i20088.iconsultapp.Model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable{

   // private String appointmentId = null;
    private String schdate = null;
    private String created_at = null;
    private String _id = null;
    private Integer patientId = 0;
    private Integer gpId = 0;
    private String roomname = null;
    private String status = null;
    private Integer scheduleId = 0;
    private Integer __v = 0;

    public Appointment(Integer patientId, String schdate) {
        this.patientId = patientId;
        this.schdate = schdate;
    }

    public Appointment() {
    }

    /*public String getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
        }
    */
    public String getSchdate() {
        return schdate;
    }

    public void setSchdate(String schdate) {
        this.schdate = schdate;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getGpId() {
        return gpId;
    }

    public void setGpId(Integer gpId) {
        this.gpId = gpId;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    @Override
    public boolean equals(Object obj) {
        return this.patientId==((Appointment)obj).patientId;
    }
}
