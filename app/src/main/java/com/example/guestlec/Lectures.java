package com.example.guestlec;

public class Lectures {

    private String professor;
    private String lecture;
    private  String date;
    private String time;
    private String venue;
    private  String area,lecture_details;

    public Lectures(String Professor, String Lecture, String date, String time,String venue,String lecture_details,String area){
        this.professor=Professor;
        this.date=date;
        this.lecture=Lecture;
        this.time=time;
        this.venue=venue;

    }

    public String getProfessor(){
        return professor;
    }
    public String getdate(){
        return date;
    }
    public String gettime(){ return time; }
    public String getLecture(){
        return lecture;
    }
    public String getVenue(){return venue;}
    public String getLecture_details(){return lecture_details;}

    public String getArea(){return area;}

    public void setProfessor(String Professor){
        this.professor=Professor;
    }
    public void setLecture(String lecture){
        this.lecture=lecture;
    }
    public void setTime(String time){
        this.time=time;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setVenue(String venue){this.venue=venue;}
    public void setArea(String venue){this.area=venue;}
    public void setLecture_details(String venue){this.lecture_details=venue;}

}
