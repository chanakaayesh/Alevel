package com.chanaka.alevel.DB_Model;

 public   class  section_model {


    public  static String name;
    public  static String coresubject;
    public static  int year;
    public  static  String paper_type;

     public static String getPaper_type() {
         return paper_type;
     }

     public static void setPaper_type(String paper_type) {
         section_model.paper_type = paper_type;
     }

     public static int getYear() {
         return year;
     }

     public static void setYear(int year) {
         section_model.year = year;
     }

     public static String getCoresubject() {
         return coresubject;
     }

     public static void setCoresubject(String coresubject) {
         section_model.coresubject = coresubject;
     }

     public static String getName() {
         return name;
     }

     public static void setName(String name) {
         section_model.name = name;
     }


 }
